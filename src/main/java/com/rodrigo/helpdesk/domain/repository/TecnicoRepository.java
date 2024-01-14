package com.rodrigo.helpdesk.domain.repository;

import com.rodrigo.helpdesk.domain.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
