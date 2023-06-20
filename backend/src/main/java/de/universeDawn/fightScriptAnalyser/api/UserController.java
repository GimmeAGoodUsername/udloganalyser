package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.services.SrUserService;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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
