package com.rodrigo.helpdesk.domain;

import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Entity(name = "TB_TECNICO")
public class Tecnico extends Pessoa implements Serializable {
    private static final long serialVersionUID =1L;

    @OneToMany(mappedBy = "cliente") // em chamados ele esta sendo mapeado pelo atributo private tecnico
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfiel(Perfil.CLIENTE);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfiel(Perfil.CLIENTE);
    }
}
