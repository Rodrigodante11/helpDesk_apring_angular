package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Pessoa;
import com.rodrigo.helpdesk.domain.entity.Tecnico;
import com.rodrigo.helpdesk.domain.repository.PessoaRepository;
import com.rodrigo.helpdesk.domain.repository.TecnicoRepository;
import com.rodrigo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoServices {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado!"));

    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();

    }

    public Tecnico create(Tecnico tecnico) {
        tecnico.setId(null);
        validaPorCpfEemail(tecnico);
        return tecnicoRepository.save(tecnico);
    }

    private void validaPorCpfEemail(Tecnico tecnico) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnico.getCpf());

        // se existe um pessoa com esse ID e ele for diferente do que foi enviado atualmenter
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnico.getId())){
            throw new DataIntegrityViolationException("CPF ja cadastrado no Sistema!");
        }

        pessoa = pessoaRepository.findByEmail(tecnico.getEmail());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnico.getId())){
            throw new DataIntegrityViolationException("Email ja cadastrado no Sistema!");
        }

    }
}
