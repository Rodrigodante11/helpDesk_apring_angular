package com.rodrigo.helpdesk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "TB_TECNICO")
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID =1L;

    @JsonIgnore // no rest ignora esse campo pra mim/ nao precisa trazer esse campo
    @OneToMany(mappedBy = "tecnico") // em chamados ele esta sendo mapeado pelo atributo private tecnico
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfiel(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfiel(Perfil.TECNICO);
    }
}
