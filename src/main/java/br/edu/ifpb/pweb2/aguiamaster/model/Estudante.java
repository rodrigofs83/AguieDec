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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data //lombok
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_estudante")
public class Estudante implements Serializable {
    @Id
    @Column(name="estudante_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Campo obrigatório!")
    private String nome;

    @NotBlank(message="Campo obrigatório!")
    private String matricula;

    private String senha;

    private boolean admin = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "instituicao_id")
     @ToString.Exclude
    private Instituicao instituicao;
 
    @OneToMany(mappedBy = "estudante" ,fetch = FetchType.LAZY,
    targetEntity=Declaracao.class,
    cascade=CascadeType.ALL) //relacionamento
    private List<Declaracao> declaracoes;
    
}
