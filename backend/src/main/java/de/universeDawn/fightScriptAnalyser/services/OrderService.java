package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.order.OrderRequest;
import de.universeDawn.fightscriptanalyser.api.order.OrderUserRequest;
import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.user.SrOrder;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SrUserService srUserService;

    public List<SrOrder> getAll(){
        return orderRepository.findAll();
    }

    public List<SrOrder> getAllNotDelivered(){
        return orderRepository.getAllNotDeliveredOrders();
    }

    public List<SrOrder> getOpenOrders(){
        return orderRepository.getAllOpenOrders();
    }

    public List<SrOrder> getOpenOrdersFromUser(OrderUserRequest orderUserRequest){
        return orderRepository.getOpenOrdersFromUser(orderUserRequest.srUser());

    }

    public SrOrder assignOrder(OrderRequest orderRequest){
        SrUser userByName = srUserService.getUserByName(orderRequest.name());
        orderRequest.srOrder().setDeliveryBoy(userByName);

        return orderRepository.save(orderRequest.srOrder());
    }

    public SrOrder createOrder(SrOrder order){
        order.setCreationDate(new Date());
        return orderRepository.save(order);
    }

}
