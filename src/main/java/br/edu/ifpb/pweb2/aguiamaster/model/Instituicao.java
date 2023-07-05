package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    private Integer id;

    @NotBlank(message="Campo obrigatório!")
    private String nome;

    @NotBlank(message="Campo obrigatório!")
    private String sigla;

    
    @Pattern(regexp = "^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$",message ="numero invalido")
    private String fone;

    @OneToOne
    private PeriodoLetivo  periodoAtual;
    
    @OneToMany( mappedBy = "instituicao",fetch = FetchType.LAZY,
                targetEntity=Estudante.class,
                cascade={CascadeType.MERGE,CascadeType.PERSIST}) //relacionamento
    private List<Estudante> estudantes;

    @OneToMany (mappedBy = "instituicao" ,fetch = FetchType.LAZY,
                targetEntity=PeriodoLetivo.class,
                cascade= {CascadeType.MERGE,CascadeType.PERSIST})
   private List<PeriodoLetivo> periodos;
    
}
