package com.rodrigo.helpdesk.domain.repository;

import com.rodrigo.helpdesk.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
