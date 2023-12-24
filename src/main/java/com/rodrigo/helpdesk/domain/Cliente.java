package com.rodrigo.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Entity(name = "TB_CLIENTE")
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID =1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente") // em chamados ele esta sendo mapeado pelo atributo private cliente
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(){
        super();
        addPerfiel(Perfil.CLIENTE);
    }

    public Cliente(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfiel(Perfil.CLIENTE);
    }
}
