package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.Tecnico;
import com.rodrigo.helpdesk.domain.repository.TecnicoRepository;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoServices {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));

    }

    public List<Tecnico> findAll(){
        return repository.findAll();

    }
}
