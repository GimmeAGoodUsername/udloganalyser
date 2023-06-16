package de.universeDawn.fightScriptAnalyser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@SpringBootApplication
public class FightScriptAnalyserApplication  {

    public static void main(String[] args) {
        SpringApplication.run(FightScriptAnalyserApplication.class, args);
    }


}
