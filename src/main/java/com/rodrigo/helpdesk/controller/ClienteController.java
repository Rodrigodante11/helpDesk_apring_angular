package com.rodrigo.helpdesk.controller;

import com.rodrigo.helpdesk.Utils.Converter;
import com.rodrigo.helpdesk.domain.DTOS.ClienteDTO;
import com.rodrigo.helpdesk.domain.entity.Cliente;
import com.rodrigo.helpdesk.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value= "/clientes")
public class ClienteController {

    @Autowired
    private ClienteServices services;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id ) {
        Cliente cliente = services.findById(id);
        ClienteDTO clienteDTO= Converter.cliente(cliente);
        return ResponseEntity.ok().body(clienteDTO);

    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> list= services.findAll();
        List<ClienteDTO> listDTO = list.stream().map(Converter::cliente).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){

        Cliente cliente = Converter.cliente(clienteDTO);
        Cliente novoCliente= services.create(cliente);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novoCliente.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable  Integer id,@Valid @RequestBody ClienteDTO clienteDTO){

        Cliente cliente = Converter.cliente(clienteDTO);

        Cliente clienteUpdate = services.update(id,cliente);
        ClienteDTO clienteDTOUpdate = Converter.cliente(clienteUpdate);

        return ResponseEntity.ok().body(clienteDTOUpdate);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable  Integer id){
        services.delete(id);
        return ResponseEntity.noContent().build();
    }

}
