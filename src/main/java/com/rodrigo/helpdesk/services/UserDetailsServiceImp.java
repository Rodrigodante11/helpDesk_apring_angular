package com.rodrigo.helpdesk.services;

import com.rodrigo.helpdesk.domain.entity.Pessoa;
import com.rodrigo.helpdesk.domain.repository.PessoaRepository;
import com.rodrigo.helpdesk.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Pessoa> user = pessoaRepository.findByEmail(email);

        if(user.isPresent()){
            return new UserSS(user.get().getId(), user.get().getEmail(), user.get().getSenha(), user.get().getPerfils());
        }

        throw new UsernameNotFoundException(email);
    }
}
