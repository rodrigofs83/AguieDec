package br.edu.ifpb.pweb2.aguiamaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;

@Component
public interface EstudanteRepository extends JpaRepository<Estudante, Integer>{

    

}