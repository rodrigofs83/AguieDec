package br.edu.ifpb.pweb2.aguiamaster.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Instituicao;
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
        PeriodoLetivo pl = this.getPeriodoLetivoById(periodoLetivo_id);
        Optional<Instituicao> ins = Optional.ofNullable(pl.getInstituicao());
        if(ins.isPresent()){
            pl.getInstituicao().setPeriodoAtual(null);
            pl.setInstituicao(null);
        }
        
        
        this.periodoLetivoRepository.deleteById(periodoLetivo_id);


	}

    public void editaperiodoLetivoById(Integer id) {
    }
    
}
