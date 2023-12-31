package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.config.BasicAuthWebSecurityConfiguration;
import de.universeDawn.fightscriptanalyser.repo.LoginRepository;
import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TestService {
    @Autowired
    private SrUserRepository srUserRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PlanetRepository planetRepository;

    @Autowired
    private LoginRepository loginRepository;

    public void generateTestData(){
        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setName("Happy");
        loginInformation.setPassword("133");
        loginInformation.setUser(true);
        SrUser srUser = new SrUser();
        srUser.setName(loginInformation.getName());
        srUser.setRole(Role.freelancer);
        srUser.setRace(Race.ozoid);
        srUser.setAuthorities(BasicAuthWebSecurityConfiguration.ADMIN);
        srUser.setLoginInformation(loginInformation);
        loginInformation.setSrUser(srUser);
        loginRepository.saveAndFlush(loginInformation);
        Planet p = new Planet();
        p.setPlanetName("Utopia");
        p.setX(342);
        p.setY(101);
        p.setZ(309);

        Planet p2 = new Planet();
        p2.setPlanetName("Dystopia");
        p2.setX(331);
        p2.setY(105);
        p2.setZ(328);
         srUser = srUserRepository.saveAndFlush(srUser);

        p.setSrUser(srUser);
        p2.setSrUser(srUser);
        planetRepository.saveAndFlush(p);
        planetRepository.saveAndFlush(p2);
        srUser.setPlanets(List.of(p,p2));
        SrUser test2 = new SrUser();
        test2.setName("Test");
        test2.setAuthorities(Authority.NONE.name());
        srUserRepository.saveAndFlush(test2);
        srUserRepository.saveAndFlush(srUser);
        Random r = new Random();
        int min = 0;
        int max = 1000000;
        for(int i = 0; i < 4; i++){
            SrOrder order = new SrOrder();
            order.setAlu(r.nextInt(max-min) + min);
            order.setBaux(r.nextInt(max-min) + min);
            order.setFood(r.nextInt(max-min) + min);
            order.setHelium(r.nextInt(max-min) + min);
            order.setHydro(r.nextInt(max-min) + min);
            order.setCredits(r.nextInt(max-min) + min);
            order.setTitan(r.nextInt(max-min) + min);
            order.setSilicon(r.nextInt(max-min) + min);
            order.setPluto(r.nextInt(max-min) + min);
            order.setWater(r.nextInt(max-min) + min);
            order.setOrderedBy(srUser);
            order.setTarget(p);
            order.setCreationDate(new Date());
            if((i&1)==0){
                order.setStatus(true);
            }
            order.setDeliveryDate(new Date());

            orderRepository.saveAndFlush(order);
        }

    }
}
