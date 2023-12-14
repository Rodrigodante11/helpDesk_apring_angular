package com.rodrigo.helpdesk.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class Pessoa {

    protected Integer id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfils = new HashSet<>();  //Set nao permite dois valores igual / clientes iguais
    protected LocalDate dataCriacao = LocalDate.now();

    public Pessoa( ) {
        super();
    }

    public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfiel(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfiels(){
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // coleta tudo para o tipo Set<>
    }

    public void addPerfiel(Perfil perfil){
       this.perfils.add(perfil.getCodigo());
    }

}
