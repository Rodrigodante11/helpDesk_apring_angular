package com.rodrigo.helpdesk.controller;

import com.rodrigo.helpdesk.Utils.Converter;
import com.rodrigo.helpdesk.domain.DTOS.TecnicoDTO;
import com.rodrigo.helpdesk.domain.entity.Tecnico;
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
@RequestMapping(value= "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id ) {
        Tecnico tecnico = services.findById(id);
        TecnicoDTO tecnicoDTO= Converter.tecnico(tecnico);
        return ResponseEntity.ok().body(tecnicoDTO);

    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list= services.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(Converter::tecnico).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO){

        Tecnico tecnico = Converter.tecnico(tecnicoDTO);
        Tecnico novoTecnico= services.create(tecnico);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoTecnico.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable  Integer id,@Valid @RequestBody TecnicoDTO tecnicoDTO){

        Tecnico tecnico = Converter.tecnico(tecnicoDTO);

        Tecnico tecnicoUpdate = services.update(id,tecnico);
        TecnicoDTO tecnicoDTOUpdate = Converter.tecnico(tecnicoUpdate);

        return ResponseEntity.ok().body(tecnicoDTOUpdate);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable  Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
