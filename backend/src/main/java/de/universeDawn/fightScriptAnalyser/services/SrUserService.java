package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.Planet;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

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
            UserDetails user = User
                    .withUsername(srUser.get().getName())
                    .password(passwordEncoder.encode(srUser.get().getPassword()))
                    .roles("USER_ROLE")
                    .build();
            inMemoryUserDetailsManager.createUser(user);
            UserDetails userDetails = inMemoryUserDetailsManager.loadUserByUsername("Happy");
        }

        return srUser;
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
