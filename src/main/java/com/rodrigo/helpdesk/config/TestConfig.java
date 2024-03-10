package com.rodrigo.helpdesk.config;

import com.rodrigo.helpdesk.services.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class TestConfig {

    @Autowired
    private DBServices dbServices;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String value;
    @Bean  // quando test config for instanciado o metodo com Bean vai rodar automaticamente
    public boolean instanciaDB(){
        if(value.equals("create")){
            this.dbServices.instanciaDB();
        }

        return false;
    }
}
