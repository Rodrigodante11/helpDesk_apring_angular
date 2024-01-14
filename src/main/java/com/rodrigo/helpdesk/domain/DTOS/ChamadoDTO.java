package com.rodrigo.helpdesk.domain.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID =1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura= LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private Integer prioridade;
    private Integer status;
    private String titulo;
    private String observacao;
    private Integer tecnico;
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;

}
