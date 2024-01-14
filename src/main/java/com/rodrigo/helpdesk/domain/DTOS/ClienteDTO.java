package com.rodrigo.helpdesk.domain.DTOS;

import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID =1L;

    protected Integer id;
    @NotNull(message = "O campo Nome é Obrigatorio ")
    protected String nome;
    @NotNull(message = "O campo CPF é Obrigatorio ")
    protected String cpf;
    @NotNull(message = "O campo EMAIL é Obrigatorio ")
    protected String email;
    @NotNull(message = "O campo senha é Obrigatorio ")
    protected String senha;
    protected Set<Integer> perfils = new HashSet<>();  //Set nao permite dois valores igual / clientes iguais
    protected LocalDate dataCriacao = LocalDate.now();

    public Set<Perfil> getPerfils(){
        return perfils.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfils(Perfil perfil){
        this.perfils.add(perfil.getCodigo());
    }
}
