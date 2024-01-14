package com.rodrigo.helpdesk.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@Entity(name = "TB_PESSOA")
public abstract class Pessoa implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nome;

    @Column(unique = true)
    @CPF
    protected String cpf;

    @Column(unique = true)
    protected String email;
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)  // quando der um get nesse pefiels o usuario tem que vir junto
    @CollectionTable(name = "TB_PERFIS")  // tera uma tabela no BD
    protected Set<Integer> perfils = new HashSet<>();  //Set nao permite dois valores igual / clientes iguais

    @JsonFormat(pattern = "dd/MM/yyyy")
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
    }

    public Set<Perfil> getPerfils(){
        return perfils.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet()); // coleta tudo para o tipo Set<>
    }

    public void addPerfiel(Perfil perfil){
       this.perfils.add(perfil.getCodigo());
    }

}
