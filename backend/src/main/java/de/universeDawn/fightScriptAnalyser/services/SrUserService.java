package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.repo.LoginRepository;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.AuthUser;
import de.universeDawn.fightscriptanalyser.user.LoginInformation;
import de.universeDawn.fightscriptanalyser.user.Planet;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
        if (loginInformation.getSrUser()!=null) {
             srUser = loginInformation.getSrUser();
            if (inMemoryUserDetailsManager.userExists(srUser.getName())) {
                return Optional.of(srUser);
            }
            inMemoryUserDetailsManager.createUser(mapUserToCustomUserDetails(srUser, Collections.singletonList(new SimpleGrantedAuthority(srUser.getAuthorities()))));
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
    public void logout(LoginRequest loginRequest) {
    }

    public SrUser getUserByName(String name) {
        return srUserRepository.findUserByName(name);
    }

    public void deletePlanet(long planet) {
        planetRepository.deleteById(planet);
    }
}
