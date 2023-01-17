package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import br.edu.ifpb.pweb2.aguiamaster.model.Estudante;
import br.edu.ifpb.pweb2.aguiamaster.repository.EstudanteRepository;

@Service
public class EstudanteService {
    @Autowired
    private  EstudanteRepository estudanteRepository;

    public Estudante saveEstudate(Estudante novoEstudante){
    
            Estudante estudante = this.estudanteRepository.save(novoEstudante);
            return estudante;
            

    }

    public List<Estudante> getEstudantes() {
        return this.estudanteRepository.findAll();
    } 

    public Estudante getEstudanteById(Integer estudante_id){
            return this.estudanteRepository.findById(estudante_id).orElse(null);

    }

	

	public void deleteEstudanteById(Integer estudante_id) {

        
		this.estudanteRepository.deleteById(estudante_id);
	}
}
