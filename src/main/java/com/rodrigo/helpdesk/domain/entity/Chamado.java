package com.rodrigo.helpdesk.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rodrigo.helpdesk.domain.enums.Prioridade;
import com.rodrigo.helpdesk.domain.enums.Status;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity(name = "TB_CHAMADO")
public class Chamado implements Serializable {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(updatable = false)
    private LocalDate dataAbertura= LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;


    private Prioridade prioridade;

    private Status status;
    private String titulo;
    private String observacao;

    @ManyToOne
    @JoinColumn(name= "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name= "cliente_id")
    private Cliente cliente;

    public Chamado() {}

    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo, String observacao,
                   Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacao = observacao;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    @PrePersist
    public void prePersisist() {
        setDataAbertura(LocalDate.now());
    }
}
