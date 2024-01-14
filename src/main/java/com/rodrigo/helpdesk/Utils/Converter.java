package com.rodrigo.helpdesk.Utils;

import com.rodrigo.helpdesk.domain.DTOS.ClienteDTO;
import com.rodrigo.helpdesk.domain.DTOS.TecnicoDTO;
import com.rodrigo.helpdesk.domain.entity.Cliente;
import com.rodrigo.helpdesk.domain.entity.Tecnico;
import com.rodrigo.helpdesk.domain.enums.Perfil;

import java.util.stream.Collectors;

public class Converter {

    public static TecnicoDTO tecnico(Tecnico tecnico){
        return TecnicoDTO.builder()
                .id(tecnico.getId())
                .nome((tecnico.getNome()))
                .cpf(tecnico.getCpf())
                .email(tecnico.getEmail())
                .senha(tecnico.getSenha())
                .dataCriacao(tecnico.getDataCriacao())
                .perfils(
                        tecnico.getPerfils().stream().map(Perfil::getCodigo).collect(Collectors.toSet())
                )
                .dataCriacao(tecnico.getDataCriacao())
                .build();
    }
    public static ClienteDTO cliente(Cliente cliente){
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .email(cliente.getEmail())
                .senha(cliente.getSenha())
                .dataCriacao(cliente.getDataCriacao())
                .perfils(
                        cliente.getPerfils().stream().map(Perfil::getCodigo).collect(Collectors.toSet())
                )
                .dataCriacao(cliente.getDataCriacao())
                .build();
    }

    public static Tecnico tecnico(TecnicoDTO tecnicoDTO) {
        return new Tecnico(tecnicoDTO.getId(),
                tecnicoDTO.getNome(),
                tecnicoDTO.getCpf(),
                tecnicoDTO.getEmail(),
                tecnicoDTO.getSenha(),
                tecnicoDTO.getPerfils());
    };

    public static Cliente cliente(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId(),
                clienteDTO.getNome(),
                clienteDTO.getCpf(),
                clienteDTO.getEmail(),
                clienteDTO.getSenha(),
                clienteDTO.getPerfils());
    };
}
