package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.api.user.UserCreateRequest;
import de.universeDawn.fightscriptanalyser.config.BasicAuthWebSecurityConfiguration;
import de.universeDawn.fightscriptanalyser.repo.LoginRepository;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.*;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SrUserService {

    @Autowired
    private SrUserRepository srUserRepository;

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private LoginRepository loginRepository;

    public List<SrUser> getAllUsers() {
        return srUserRepository.findAll();
    }

    public SrUser updateUser(SrUser user) {
        planetRepository.saveAllAndFlush(user.getPlanets());

        return srUserRepository.saveAndFlush(user);
    }

    public Optional<SrUser> validUser(LoginRequest loginRequest) {
        LoginInformation loginInformation = loginRepository.findByValids(loginRequest.getUsername(), loginRequest.getPassword());
        SrUser srUser = null;
        if (loginInformation != null && loginInformation.getSrUser() != null) {
            srUser = loginInformation.getSrUser();
            if (inMemoryUserDetailsManager.userExists(srUser.getName())) {
                return Optional.of(srUser);
            }
            inMemoryUserDetailsManager.createUser(mapUserToCustomUserDetails(srUser, Collections.singletonList(new SimpleGrantedAuthority(srUser.getAuthorities()))));
        }
        if (srUser == null) {
            return Optional.of(null);
        }
        return Optional.of(srUser);
    }

    private AuthUser mapUserToCustomUserDetails(SrUser user, List<SimpleGrantedAuthority> authorities) {
        AuthUser customUserDetails = new AuthUser();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getName());
        customUserDetails.setPassword(passwordEncoder.encode(user.getLoginInformation().getPassword()));
        customUserDetails.setName(user.getName());
        customUserDetails.setAuthorities(authorities);
        return customUserDetails;
    }

    public SrUser getUserByName(String name) {
        return srUserRepository.findUserByName(name);
    }

    public void deletePlanet(long planet) {
        planetRepository.deleteById(planet);
    }


    public void createUser(UserCreateRequest userCreateRequest) {
        LoginInformation loginInformation = loginRepository.findByName(userCreateRequest.username());
        if (loginInformation != null) {
            loginInformation.setName(userCreateRequest.username());
            loginInformation.setPassword(userCreateRequest.password());
            loginInformation.setUser(!(userCreateRequest.role().equals(BasicAuthWebSecurityConfiguration.NONE)));
            loginRepository.saveAndFlush(loginInformation);
        }else{
            loginInformation = new LoginInformation();
            loginInformation.setName(userCreateRequest.username());
            loginInformation.setPassword(userCreateRequest.password());
            loginInformation.setUser(!(userCreateRequest.role().equals(BasicAuthWebSecurityConfiguration.NONE)));
        }

        SrUser srUser = srUserRepository.findUserByName(loginInformation.getName());
        if (srUser == null) {
            srUser = new SrUser();
            srUser.setName(userCreateRequest.username());
            srUser.setRace(Race.ozoid);
            srUser.setRole(Role.freelancer);

        }
        srUser.setAuthorities(userCreateRequest.role());
        loginInformation.setSrUser(srUser);
        srUser.setLoginInformation(loginInformation);


        loginRepository.saveAndFlush(loginInformation);
        srUserRepository.saveAndFlush(srUser);
    }

    public void deleteUser(Long id) {
        Optional<SrUser> byId = srUserRepository.findById(id);
        if (!byId.isPresent()) {
            return;
        }
        LoginInformation loginInformation = byId.get().getLoginInformation();
        loginInformation.setUser(false);
        loginRepository.saveAndFlush(loginInformation);
        byId.get().setAuthorities(BasicAuthWebSecurityConfiguration.NONE);
        srUserRepository.saveAndFlush(byId.get());
        inMemoryUserDetailsManager.deleteUser(byId.get().getName());
    }
}
