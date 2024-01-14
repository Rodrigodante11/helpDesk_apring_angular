package com.rodrigo.helpdesk.controller;

import com.rodrigo.helpdesk.Utils.Converter;
import com.rodrigo.helpdesk.domain.DTOS.ChamadoDTO;
import com.rodrigo.helpdesk.domain.entity.Chamado;
import com.rodrigo.helpdesk.services.ChamadoService;
import com.rodrigo.helpdesk.services.ClienteServices;
import com.rodrigo.helpdesk.services.TecnicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private TecnicoServices tecnicoServices;

    @Autowired
    private ClienteServices clienteServices;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        Chamado chamado = chamadoService.findById(id);
        ChamadoDTO chamadoDTO = Converter.chamado(chamado);

        return ResponseEntity.ok().body(chamadoDTO);

    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado>  chamados = chamadoService.findAll();
        List<ChamadoDTO> listDTO = chamados.stream().map(Converter::chamado).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO chamadoDTO){
        Chamado chamado = Converter.chamado(chamadoDTO);
        chamado.setCliente(clienteServices.findById(chamadoDTO.getCliente()));
        chamado.setTecnico(tecnicoServices.findById(chamadoDTO.getTecnico()));

        Chamado chamadoSalvo = chamadoService.create(chamado);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(chamadoSalvo.getId())
                .toUri();
        return ResponseEntity.created(uri).build();

    }
}
