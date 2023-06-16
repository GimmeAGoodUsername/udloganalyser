package de.universeDawn.fightScriptAnalyser.api;

import de.universeDawn.fightScriptAnalyser.services.SrUserService;
import de.universeDawn.fightScriptAnalyser.user.SrOrder;
import de.universeDawn.fightScriptAnalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/userApi")
public class UserController {

    @Autowired
    private SrUserService srUserService;

    @GetMapping(value = "/getAllUsers", produces = "application/json")
    ResponseEntity<List<SrUser>> getAllUsers() {
        return new ResponseEntity<List<SrUser>>(srUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser" , produces = "application/json")
    ResponseEntity<SrUser> updateUser(SrUser user) {
        return new ResponseEntity<>(srUserService.updateUser(user),HttpStatus.OK);
    }

}
