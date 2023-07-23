package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.AuthUser;
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

    public List<SrUser> getAllUsers() {
        return srUserRepository.findAll();
    }

    public SrUser updateUser(SrUser user) {
        planetRepository.saveAllAndFlush(user.getPlanets());
        return srUserRepository.saveAndFlush(user);
    }

    public Optional<SrUser> validUser(LoginRequest loginRequest) {
        Optional<SrUser> srUser = Optional.of(srUserRepository.findByValids(loginRequest.getUsername(), loginRequest.getPassword()));

        if (srUser.isPresent()) {
            if (inMemoryUserDetailsManager.userExists(srUser.get().getName())) {
                return srUser;
            }
            inMemoryUserDetailsManager.createUser(mapUserToCustomUserDetails(srUser.get(), Collections.singletonList(srUser.get().getAuthorities())));
        }

        return srUser;
    }
    private AuthUser mapUserToCustomUserDetails(SrUser user, List<SimpleGrantedAuthority> authorities) {
        AuthUser customUserDetails = new AuthUser();
        customUserDetails.setId(user.getId());
        customUserDetails.setUsername(user.getName());
        customUserDetails.setPassword(passwordEncoder.encode(user.getPassword()));
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
