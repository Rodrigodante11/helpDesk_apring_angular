package com.rodrigo.helpdesk.domain.repository;

import com.rodrigo.helpdesk.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
