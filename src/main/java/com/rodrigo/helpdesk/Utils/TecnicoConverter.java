package com.rodrigo.helpdesk.Utils;

import com.rodrigo.helpdesk.domain.DTOS.TecnicoDTO;
import com.rodrigo.helpdesk.domain.Tecnico;
import com.rodrigo.helpdesk.domain.enums.Perfil;

import java.util.Set;
import java.util.stream.Collectors;

public class TecnicoConverter {

    public static TecnicoDTO ConverterDTO(Tecnico tecnico){
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
}
