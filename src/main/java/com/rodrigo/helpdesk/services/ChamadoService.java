package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Chamado;
import com.rodrigo.helpdesk.domain.repository.ChamadoRepository;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamadoOptional = chamadoRepository.findById(id);
        return chamadoOptional.orElseThrow(() -> new ObjectNotFoundException("Chamado nao encontdado no sistema"));

    }
}
