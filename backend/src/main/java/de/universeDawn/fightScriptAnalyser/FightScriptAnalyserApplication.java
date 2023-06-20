package de.universeDawn.fightscriptanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@SpringBootApplication
public class FightScriptAnalyserApplication  {

    public static void main(String[] args) {
        SpringApplication.run(FightScriptAnalyserApplication.class, args);
    }

}
