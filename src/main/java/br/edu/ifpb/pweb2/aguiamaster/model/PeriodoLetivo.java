package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.ifpb.pweb2.aguiamaster.Enuns.Periodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_periodo")
public class PeriodoLetivo  implements Serializable{
    @Id
    @Column(name="periodo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    private int ano ;

    
    @Enumerated(EnumType.STRING)
    private Periodo periodo;

   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inicio;
    
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  Date fim;

    @OneToMany(mappedBy = "periodoLetivo",fetch = FetchType.LAZY,
    targetEntity=Declaracao.class,
    cascade=CascadeType.ALL)//relacionamento
    private List<Declaracao> declaracoes;

    @ManyToOne(fetch = FetchType.LAZY) //relacionamento
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
    
}
