package com.rodrigo.helpdesk.config;

import com.rodrigo.helpdesk.services.DBServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBServices dbServices;

    @Bean  // quando test config for instanciado o metodo com Bean vai rodar automaticamente
    public void instanciaDB(){
        this.dbServices.instanciaDB();
    }
}
