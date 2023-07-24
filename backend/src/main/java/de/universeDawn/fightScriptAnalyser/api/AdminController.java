package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.user.UserCreateRequest;
import de.universeDawn.fightscriptanalyser.services.SrUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/adminApi")
public class AdminController {

    @Autowired
    private SrUserService srUserService;

    @PutMapping(value = "/createUser")
    public void createUser(@RequestBody UserCreateRequest userCreateRequest){
        srUserService.createUser(userCreateRequest);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id){
        srUserService.deleteUser(id);
    }
}
