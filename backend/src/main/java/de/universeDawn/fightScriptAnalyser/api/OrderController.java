package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.order.OrderCompleteRequest;
import de.universeDawn.fightscriptanalyser.api.order.OrderCreateRequest;
import de.universeDawn.fightscriptanalyser.api.order.OrderRequest;
import de.universeDawn.fightscriptanalyser.api.order.OrderUserRequest;
import de.universeDawn.fightscriptanalyser.services.OrderService;
import de.universeDawn.fightscriptanalyser.user.SrOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orderApi")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/all", produces = "application/json")
    ResponseEntity<List<SrOrder>> getAllOrders() {
        return new ResponseEntity<List<SrOrder>>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/allNotDelivered", produces = "application/json")
    ResponseEntity<List<SrOrder>> getAllNotDeliveredOrders() {
        return new ResponseEntity<List<SrOrder>>(orderService.getAllNotDelivered(), HttpStatus.OK);
    }

    @GetMapping(value = "/openOrders", produces = "application/json")
    ResponseEntity<List<SrOrder>> getOpenOrders() {
        return new ResponseEntity<List<SrOrder>>(orderService.getOpenOrders(), HttpStatus.OK);
    }

    @PostMapping(value = "/userOrders", produces = "application/json")
    ResponseEntity<List<SrOrder>> getOrdersForUser(@RequestBody OrderUserRequest orderUserRequest) {
        return new ResponseEntity<List<SrOrder>>(orderService.getOpenOrdersFromUser(orderUserRequest), HttpStatus.OK);
    }

    @PostMapping(value = "/assign", produces = "application/json")
    ResponseEntity<SrOrder> assignOrders(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.srOrder() == null || orderRequest.name() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.assignOrder(orderRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/create", produces = "application/json", consumes = "application/json")
    ResponseEntity<SrOrder> createOrder(@RequestBody OrderCreateRequest orderRequest) {
        if (orderRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.createOrder(orderRequest.srOrder()), HttpStatus.OK);
    }
    @PutMapping(value = "/deliver", produces = "application/json", consumes = "application/json")
    ResponseEntity<Void> deliverOrder(@RequestBody OrderCompleteRequest orderRequest) {
        if (orderRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        orderService.completeOrder(orderRequest.order());
        return new ResponseEntity<Void>( HttpStatus.OK);
    }
}
