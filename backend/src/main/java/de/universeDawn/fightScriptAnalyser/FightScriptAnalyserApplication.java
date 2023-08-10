package de.universeDawn.fightscriptanalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@SpringBootApplication
@EnableAutoConfiguration
public class FightScriptAnalyserApplication  {

    public static void main(String[] args) {
        SpringApplication.run(FightScriptAnalyserApplication.class, args);
    }

}
