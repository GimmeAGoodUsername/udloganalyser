package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.services.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/public")
public class PublicApi {

    @Autowired
    private PublicService publicService;

    @GetMapping(value = "/oxi", produces = "application/json")
    public long getOxiKillDate(){
        return publicService.getCurrentOxiDate();
    }

    @GetMapping(value = "/setOxi")
    public void setOxiKillDate(){
        publicService.setLastOxi();
    }
}
