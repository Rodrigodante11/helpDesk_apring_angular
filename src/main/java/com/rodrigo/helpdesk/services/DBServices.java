package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.Chamado;
import com.rodrigo.helpdesk.domain.Cliente;
import com.rodrigo.helpdesk.domain.Tecnico;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import com.rodrigo.helpdesk.domain.enums.Prioridade;
import com.rodrigo.helpdesk.domain.enums.Status;
import com.rodrigo.helpdesk.domain.repository.ChamadoRepository;
import com.rodrigo.helpdesk.domain.repository.ClienteRepository;
import com.rodrigo.helpdesk.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public void instanciaDB(){

        Tecnico tec1= new Tecnico(null,"Valdir cesar","63653230268", "valdir@email.com", "123");
        tec1.addPerfiel(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus Torvals", "80527954780","linus!gmail.com",
                "123");

        Chamado c1 = new Chamado(null,
                Prioridade.MEDIA,
                Status.ANDAMENTO,
                "Chamado 1",
                " Primeiro chamado",
                tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));

    }
}
