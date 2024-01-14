package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Chamado;
import com.rodrigo.helpdesk.domain.repository.ChamadoRepository;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id){
        Optional<Chamado> chamadoOptional = chamadoRepository.findById(id);
        return chamadoOptional.orElseThrow(() -> new ObjectNotFoundException("Chamado nao encontrado no sistema"));

    }

    public List<Chamado> findAll() {
        List<Chamado> chamados = chamadoRepository.findAll();
        return chamados;
    }

    public Chamado create(Chamado chamado) {
        return chamadoRepository.save(chamado);
    }


    public Chamado update(Integer id, Chamado chamadoUpdate) {
        chamadoUpdate.setId(id);
        Chamado chamadoAntigo = findById(id);
        chamadoAntigo.setDataAbertura(chamadoAntigo.getDataAbertura());
        return chamadoRepository.save(chamadoUpdate);

    }
}
