package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.repository;


import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Localizacao;
import com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.domain.validator.LocalizacaoValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LocalizacaoRepositoryFAKE {
    private static LocalizacaoRepositoryFAKE localizacaoRepositoryFAKE;

    private List<Localizacao> localizacaoList = new ArrayList<>();

     {
        try {
            Localizacao triunfo = new Localizacao();
            triunfo.setId(proximoId());
            triunfo.setDesignacao("Triunfo");
            save(triunfo);

            Localizacao sommerchield = new Localizacao();
            sommerchield.setId(proximoId());
            sommerchield.setDesignacao("Sommerchield");
            save(sommerchield);
        }catch (ValorInvalidoException valorInvalidoException){
            System.err.println(valorInvalidoException.getMessage());
            System.err.flush();
            System.err.close();
        }
    }

    private Long proximoId(){
        localizacaoList.sort(Comparator.comparing(Localizacao::getId));
        return localizacaoList.size() > 0 ? Math.addExact(localizacaoList.get(localizacaoList.size()-1).getId(), 1) : 1;
    }

    public List<Localizacao> findAll(){
        return localizacaoList;
    }

    public Optional<Localizacao> findById(Long id){
        return localizacaoList.stream().filter((l-> l.getId().equals(id))).findFirst();
    }

    public Optional<Localizacao> save(Localizacao localizacao) throws ValorInvalidoException {
        localizacao.setId(proximoId());

        LocalizacaoValidator.valida(localizacao);

        localizacaoList.add(localizacao);
        return Optional.of(localizacao);
    }

    public static LocalizacaoRepositoryFAKE getInstance(){
        if(localizacaoRepositoryFAKE == null){
            localizacaoRepositoryFAKE = new LocalizacaoRepositoryFAKE();
        }
        return localizacaoRepositoryFAKE;
    }
}
