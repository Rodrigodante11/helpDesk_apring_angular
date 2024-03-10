package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Chamado;
import com.rodrigo.helpdesk.domain.entity.Cliente;
import com.rodrigo.helpdesk.domain.entity.Tecnico;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import com.rodrigo.helpdesk.domain.enums.Prioridade;
import com.rodrigo.helpdesk.domain.enums.Status;
import com.rodrigo.helpdesk.domain.repository.ChamadoRepository;
import com.rodrigo.helpdesk.domain.repository.ClienteRepository;
import com.rodrigo.helpdesk.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class DBServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){


        Tecnico tec1= new Tecnico(null,"Valdir cesar","636.532.302-68", "valdir@email.com",
                encoder.encode("123"), Collections.singleton(Perfil.ADMIN));


        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.TECNICO));
        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.TECNICO));
        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.TECNICO));
        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.TECNICO));

        Cliente cli1 = new Cliente(null, "Linus Torvals", "805.279.547-80","linus!gmail.com",
                encoder.encode("123"), Collections.singleton(Perfil.CLIENTE));
        Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.CLIENTE));
        Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.CLIENTE));
        Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.CLIENTE));
        Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com",
                encoder.encode("123"), Collections.singleton(Perfil.CLIENTE));

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
        Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
        Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
        Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
        Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));



    }
}
