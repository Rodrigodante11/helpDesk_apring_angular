package com.rodrigo.helpdesk.controller;

import com.rodrigo.helpdesk.Utils.Converter;
import com.rodrigo.helpdesk.domain.DTOS.ChamadoDTO;
import com.rodrigo.helpdesk.domain.entity.Chamado;
import com.rodrigo.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = chamadoService.findById(id);
        ChamadoDTO chamadoDTO = Converter.chamado(chamado);

        return ResponseEntity.ok().body(chamadoDTO);

    }
}
