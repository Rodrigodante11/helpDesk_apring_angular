package com.rodrigo.helpdesk.domain;

import com.rodrigo.helpdesk.domain.enums.Prioridade;
import com.rodrigo.helpdesk.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chamado {

    private Integer id;
    private LocalDate dataAbertura= LocalDate.now();
    private LocalDate dataFechamento;
    private Prioridade prioridade;

    private Status status;
    private String titulo;
    private String observacao;

    private Tecnico tecnico;
    private Cliente cliente;

}
