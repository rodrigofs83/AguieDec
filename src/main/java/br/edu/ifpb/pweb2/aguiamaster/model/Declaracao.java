package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_declaracao")
public class Declaracao implements Serializable {

    @Id
    @Column(name="declaracao_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRecebimento;

    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Data deve ser futura")
    private Date dataVencimento;

    private String observacao;

    @OneToOne
    @JoinColumn(name = "documento_id")
    @ToString.Exclude
    private Documento documento;


    @ManyToOne(fetch = FetchType.LAZY) //relacionamento
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    @ManyToOne(fetch = FetchType.LAZY)//relacionamento
    @JoinColumn(name = "periodo_id")
    private PeriodoLetivo periodoLetivo;
    

}
