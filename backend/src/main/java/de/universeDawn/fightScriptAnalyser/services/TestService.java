package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.repo.SrUserRepository;
import de.universeDawn.fightscriptanalyser.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestService {
    @Autowired
    private SrUserRepository srUserRepository;

    @Autowired
    private OrderRepository orderRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void generateTestData(){
        SrUser srUser = new SrUser();
        srUser.setName("Happy");
        srUser.setPassword("1");
        srUser.setRole(Role.freelancer);
        srUser.setRace(Race.ozoid);
        Planet p = new Planet();
        p.setPlanetName("TestPlanet");
        p.setX(1);
        p.setY(2);
        p.setZ(3);
        p.setSrUser(srUser);

        srUserRepository.saveAndFlush(srUser);

        SrOrder order = new SrOrder();
        order.setAlu(100);
        order.setBaux(100);
        order.setOrderedBy(srUser);
        order.setDeliveryDate(new Date());

        orderRepository.saveAndFlush(order);
    }
}
