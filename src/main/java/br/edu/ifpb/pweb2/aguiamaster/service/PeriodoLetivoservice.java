package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.PeriodoLetivo;
import br.edu.ifpb.pweb2.aguiamaster.repository.PeriodoLetivoRepository;

@Service
public class PeriodoLetivoService {
    @Autowired
    PeriodoLetivoRepository periodoLetivoRepository;

    public PeriodoLetivo savePeriodo(PeriodoLetivo novoPeriodoLetivo){
        PeriodoLetivo periodoLetivo = this.periodoLetivoRepository.save(novoPeriodoLetivo);
            
        return periodoLetivo;
            

    }

    public List<PeriodoLetivo> getPeriodoLetivos() {
        return this.periodoLetivoRepository.findAll();
    }

    public PeriodoLetivo getPeriodoLetivoById(Integer periodoLetivo_id){
            return this.periodoLetivoRepository.findById(periodoLetivo_id).orElse(null);

    }

	

	public void deletePeriodoLetivoById(Integer periodoLetivo_id) {
        PeriodoLetivo p = this.getPeriodoLetivoById(periodoLetivo_id);
        p.getInstituicao().setPeriodoAtual(null);
        p.setInstituicao(null);
        
        this.periodoLetivoRepository.deleteById(periodoLetivo_id);


	}

    public void editaperiodoLetivoById(Integer id) {
    }
    
}
