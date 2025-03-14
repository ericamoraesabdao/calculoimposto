package com.desafio.calculoimposto.repository;

import com.desafio.calculoimposto.model.TipoImposto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoImpostoRepository extends JpaRepository <TipoImposto, Long> {

}
