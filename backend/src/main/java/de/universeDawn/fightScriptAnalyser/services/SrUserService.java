package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.auth.LoginRequest;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SrUserService {

    @Autowired
    private SrUserRepository srUserRepository;

    public List<SrUser> getAllUsers(){
        return srUserRepository.findAll();
    }

    public SrUser updateUser(SrUser user){
        return srUserRepository.saveAndFlush(user);
    }

    public Optional<SrUser> validUser(LoginRequest loginRequest){
        return Optional.of(srUserRepository.findByValids(loginRequest.getUsername(),loginRequest.getPassword()));
    }

    public void registerPlanet(){

    }
}
