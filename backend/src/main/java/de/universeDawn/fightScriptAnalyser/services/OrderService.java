package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.user.SrOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<SrOrder> getOpenOrders(){
        return orderRepository.getAllOpenOrders();
    }
}
