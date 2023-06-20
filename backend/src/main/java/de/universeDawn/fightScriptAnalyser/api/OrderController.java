package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.services.OrderService;
import de.universeDawn.fightscriptanalyser.user.SrOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orderApi")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/openOrders", produces = "application/json")
    ResponseEntity<List<SrOrder>> mapFightResult() {
        return new ResponseEntity<List<SrOrder>>(orderService.getOpenOrders(), HttpStatus.OK);
    }
}
