package br.edu.ifpb.pweb2.aguiamaster.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tb_declaracao")
public class Declaracao implements Serializable {

    @Id
    @Column(name="declaracao_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataRecebimento;

    private String observacao;

    @ManyToOne //relacionamento
    @JoinColumn(name = "estudante_id")
    private Estudante estudante;

    @ManyToOne//relacionamento
    @JoinColumn(name = "periodo_id")
    private PeriodoLetivo periodoLetivo;
    

}
