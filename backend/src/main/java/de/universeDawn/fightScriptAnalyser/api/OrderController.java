package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.order.OrderRequest;
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

    @GetMapping(value = "/openOrders", produces = "application/json")
    ResponseEntity<List<SrOrder>> getOpenOrders() {
        return new ResponseEntity<List<SrOrder>>(orderService.getOpenOrders(), HttpStatus.OK);
    }

    @PostMapping(value = "/assign", produces = "application/json")
    ResponseEntity<SrOrder> assignOrders(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.srOrder() == null || orderRequest.name() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.assignOrder(orderRequest), HttpStatus.OK);
    }

    @PutMapping(value = "/create", produces = "application/json")
    ResponseEntity<SrOrder> createOrder(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.srOrder() == null || orderRequest.name() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderService.assignOrder(orderRequest), HttpStatus.OK);
    }
}
