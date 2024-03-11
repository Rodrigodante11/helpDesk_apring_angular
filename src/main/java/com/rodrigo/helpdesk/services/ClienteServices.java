package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Pessoa;
import com.rodrigo.helpdesk.domain.entity.Cliente;
import com.rodrigo.helpdesk.domain.repository.PessoaRepository;
import com.rodrigo.helpdesk.domain.repository.ClienteRepository;
import com.rodrigo.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.rodrigo.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente nao encontrado no sistema!"));

    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();

    }

    public Cliente create(Cliente cliente) {
        cliente.setId(null);
        cliente.setSenha(encoder.encode(cliente.getSenha()));
        validaPorCpfEemail(cliente);
        return clienteRepository.save(cliente);
    }

    private void validaPorCpfEemail(Cliente cliente) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(cliente.getCpf());

        // se existe um pessoa com esse ID e ele for diferente do que foi enviado atualmenter
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), cliente.getId())){
            throw new DataIntegrityViolationException("CPF ja cadastrado no Sistema!");
        }

        pessoa = pessoaRepository.findByEmail(cliente.getEmail());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), cliente.getId())){
            throw new DataIntegrityViolationException("Email ja cadastrado no Sistema!");
        }

    }

    public Cliente update(Integer id, Cliente cliente) {
        cliente.setId(id);
        validaPorCpfEemail(cliente);   // valida CPF e Email
        Cliente oldCliente = findById(id);  //verifica se existe na base de dados

        if(!cliente.getSenha().equals(oldCliente.getSenha())){ // ve se alterou a senha
            cliente.setSenha(encoder.encode(cliente.getSenha()));
        }

        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if(!cliente.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui ordens de servicos e nao pode ser Deletado");
        }else {
            clienteRepository.deleteById(id);
        }

    }
}
