package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="tb_instituicao")
public class Instituicao implements Serializable{
    @Id
    @Column(name="instituicao_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String sigla;

    @NotBlank
    private String fone;

    @OneToMany( mappedBy = "instituicao",
                targetEntity=Estudante.class,
                cascade=CascadeType.ALL) //relacionamento

    private List<Estudante> estudantes;

    @OneToMany (mappedBy = "instituicao" ,
                targetEntity=PeriodoLetivo.class,
                cascade=CascadeType.ALL)
   
    private List<PeriodoLetivo> periodos;
    
}
