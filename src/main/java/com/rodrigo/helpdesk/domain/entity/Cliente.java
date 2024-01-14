package com.rodrigo.helpdesk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rodrigo.helpdesk.domain.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_CLIENTE")
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID =1L;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER) // em chamados ele esta sendo mapeado pelo atributo private cliente
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(Integer id, String nome, String cpf, String email, String senha, Set<Perfil> perfils) {
        super(id, nome, cpf, email, senha);
        this.perfils = perfils.stream().map(Perfil::getCodigo).collect(Collectors.toSet());
        addPerfiel(Perfil.CLIENTE);
    }
}
