package br.lumis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.lumis.domain.Candidato;
@Repository
public interface ICandidatoRepository extends JpaRepository<Candidato, Long> {

}
