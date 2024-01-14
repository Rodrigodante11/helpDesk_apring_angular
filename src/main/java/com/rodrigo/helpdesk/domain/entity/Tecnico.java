package com.rodrigo.helpdesk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity(name = "TB_TECNICO")
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID =1L;

    @JsonIgnore // no rest ignora esse campo pra mim/ nao precisa trazer esse campo
    @OneToMany(mappedBy = "tecnico",fetch = FetchType.EAGER) // em chamados ele esta sendo mapeado pelo atributo private tecnico
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(Integer id, String nome, @CPF String cpf, String email, String senha, Set<Perfil> perfils) {
        super(id, nome, cpf, email, senha);
        this.perfils = perfils.stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        this.perfils.add(3);

    }
    public Tecnico() {}

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
