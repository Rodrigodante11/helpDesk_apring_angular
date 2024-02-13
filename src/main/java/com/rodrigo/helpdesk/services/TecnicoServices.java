package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Pessoa;
import com.rodrigo.helpdesk.domain.entity.Tecnico;
import com.rodrigo.helpdesk.domain.repository.PessoaRepository;
import com.rodrigo.helpdesk.domain.repository.TecnicoRepository;
import com.rodrigo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Tecnico nao encontrado no sistema!"));

    }

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();

    }

    public Tecnico create(Tecnico tecnico) {
        tecnico.setId(null);
        tecnico.setSenha(encoder.encode(tecnico.getSenha()));
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

    public Tecnico update(Integer id, Tecnico tecnico) {
        tecnico.setId(id);
        validaPorCpfEemail(tecnico);   // valida CPF e Email
        Tecnico oldTecnico = findById(id);  //verifica se existe na base de dados
        return tecnicoRepository.save(tecnico);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if(!tecnico.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Tecnico possui ordens de servicos e nao pode ser Deletado");
        }else {
            tecnicoRepository.deleteById(id);
        }

    }
}
