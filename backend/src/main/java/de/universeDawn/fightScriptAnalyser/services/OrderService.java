package de.universeDawn.fightscriptanalyser.services;

import de.universeDawn.fightscriptanalyser.api.order.OrderRequest;
import de.universeDawn.fightscriptanalyser.repo.OrderRepository;
import de.universeDawn.fightscriptanalyser.user.SrOrder;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SrUserService srUserService;

    public List<SrOrder> getOpenOrders(){
        return orderRepository.getAllOpenOrders();
    }

    public SrOrder assignOrder(OrderRequest orderRequest){
        SrUser userByName = srUserService.getUserByName(orderRequest.name());
        orderRequest.srOrder().setDeliveryBoy(userByName);

        return orderRepository.save(orderRequest.srOrder());
    }

    public SrOrder createOrder(SrOrder order){
        return orderRepository.save(order);
    }
}
