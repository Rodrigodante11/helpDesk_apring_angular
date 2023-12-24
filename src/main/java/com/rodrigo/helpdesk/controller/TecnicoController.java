package com.rodrigo.helpdesk.controller;

import com.rodrigo.helpdesk.Utils.TecnicoConverter;
import com.rodrigo.helpdesk.domain.DTOS.TecnicoDTO;
import com.rodrigo.helpdesk.domain.Tecnico;
import com.rodrigo.helpdesk.services.TecnicoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value= "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id ) {
        Tecnico tecnico = services.findById(id);
        TecnicoDTO tecnicoDTO= TecnicoConverter.ConverterDTO(tecnico);
        return ResponseEntity.ok().body(tecnicoDTO);

    }


}
