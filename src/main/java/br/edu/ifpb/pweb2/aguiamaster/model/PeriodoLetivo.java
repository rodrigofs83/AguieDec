package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
    private Long id;
    
    @NotBlank
    private int ano ;

    @NotBlank
    private  int periodo;

    @NotBlank
    private Date inicio;

    private  Date fim;

    @OneToMany(mappedBy = "periodoLetivo",
    targetEntity=Declaracao.class,
    cascade=CascadeType.ALL)//relacionamento
    private List<Declaracao> declaracoes;

    @ManyToOne //relacionamento
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;
    
}
