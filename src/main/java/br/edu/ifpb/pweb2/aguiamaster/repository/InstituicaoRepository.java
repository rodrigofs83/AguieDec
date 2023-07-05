package br.edu.ifpb.pweb2.aguiamaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;

@Component
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer>{

    @Query("SELECT p FROM PeriodoLetivo p WHERE p.instituicao = :instituicao")
        List<PeriodoLetivo> findPeriodosByInstituicao(Instituicao instituicao);
    
}
