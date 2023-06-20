package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void registerPlanet(){

    }
}
