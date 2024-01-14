package com.rodrigo.helpdesk.domain.DTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Builder
@Getter
@Setter
public class ChamadoDTO implements Serializable {

    private static final long serialVersionUID =1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    @NotNull(message = "O campo Prioridade é Obrigatorio ")
    private Integer prioridade;
    @NotNull(message = "O campo Status é Obrigatorio ")
    private Integer status;
    @NotNull(message = "O campo Titulo é Obrigatorio ")
    private String titulo;
    @NotNull(message = "O campo Observacoes é Obrigatorio ")
    private String observacao;
    @NotNull(message = "O campo Tecnico é Obrigatorio ")
    private Integer tecnico;
    @NotNull(message = "O campo Cliente é Obrigatorio ")
    private Integer cliente;

    private String nomeTecnico;
    private String nomeCliente;

    @PrePersist
    public void prePersisist() {
        setDataAbertura(LocalDate.now());
    }

}
