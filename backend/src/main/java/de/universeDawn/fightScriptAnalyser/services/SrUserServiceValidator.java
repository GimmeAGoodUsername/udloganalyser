package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class SrUserServiceValidator {

    @Autowired
    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    @Autowired
    private SrUserRepository srUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean doesUserExist(String username){
        return inMemoryUserDetailsManager.userExists(username);
    }
    public SrUser getPlayer(String username){
        if(!doesUserExist(username)){
            return null;
        }
        return srUserRepository.findUserByName(username);
    }

    public void addUserToUDM(SrUser srUser){
        UserDetails ad = User
                .withUsername(srUser.getName())
                .password(passwordEncoder.encode(srUser.getPassword()))
                .roles("USER_ROLE")
                .build();
    }
}
