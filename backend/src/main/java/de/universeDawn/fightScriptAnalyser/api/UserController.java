package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.user.UserUpdateRequest;
import de.universeDawn.fightscriptanalyser.services.SrUserService;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/userApi")
public class UserController {

    @Autowired
    private SrUserService srUserService;

    @GetMapping(value = "/getAllUsers", produces = "application/json")
    ResponseEntity<List<SrUser>> getAllUsers() {
        return new ResponseEntity<List<SrUser>>(srUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/updateUser", produces = "application/json", consumes = "application/json")
    ResponseEntity<SrUser> updateUser(@RequestBody UserUpdateRequest srUser) {
        SrUser updateUser = srUserService.updateUser(srUser.srUser());
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @GetMapping(value = "/getUser/{username}", produces = "application/json")
    @ResponseBody
    ResponseEntity<SrUser> getUser(@PathVariable("username") String username) {
        SrUser userByName = srUserService.getUserByName(username);
        return new ResponseEntity<>(userByName, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deletePlanet/{id}", produces = "application/json")
    void deletePlanet(@PathVariable("id") long planetId) {
        srUserService.deletePlanet(planetId);
    }
}
