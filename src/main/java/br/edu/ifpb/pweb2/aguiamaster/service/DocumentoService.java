package br.edu.ifpb.pweb2.aguiamaster.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.aguiamaster.model.Documento;
import br.edu.ifpb.pweb2.aguiamaster.repository.DeclaracaoRepository;
import br.edu.ifpb.pweb2.aguiamaster.repository.DocumentoRepository;
@Service
public class DocumentoService {
    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    DeclaracaoRepository declaracaoRepository;

    public Documento grave(String nomeArquivo,byte[] bytes)throws  IOException{
        Documento documento = new Documento(nomeArquivo, bytes);
        documentoRepository.save(documento);
        return documento;
    }
    public Documento getDocumento(Integer id) {
        return documentoRepository.findById(id).get();
    }

    public Optional<Documento> getDocumentoOf(Integer idDeclaracao) {
        return Optional.ofNullable(declaracaoRepository.findDocumentoById(idDeclaracao));
    }

}
