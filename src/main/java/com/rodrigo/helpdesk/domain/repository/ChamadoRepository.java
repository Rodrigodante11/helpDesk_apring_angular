package com.rodrigo.helpdesk.domain.repository;

import com.rodrigo.helpdesk.domain.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
