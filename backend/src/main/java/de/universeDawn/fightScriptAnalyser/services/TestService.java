package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.repo.PlanetRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
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

    @EventListener(ApplicationReadyEvent.class)
    public void generateTestData(){
        SrUser srUser = new SrUser();
        srUser.setName("Happy");
        srUser.setPassword("133");
        srUser.setRole(Role.freelancer);
        srUser.setRace(Race.ozoid);
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
        srUserRepository.saveAndFlush(srUser);
        Random r = new Random();
        int min = 0;
        int max = 1000000;
        for(int i = 0; i < 100; i++){
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
            order.setDeliveryDate(new Date());

            orderRepository.saveAndFlush(order);
        }

    }
}
