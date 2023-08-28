package com.eacuamba.dev.domain.repository;


import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.Propriedade;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PropriedadeRepositoryFAKE {
    private static  PropriedadeRepositoryFAKE propriedadeRepositoryFAKE;
    private static List<Propriedade> propriedadeList = new ArrayList<>();

    private  Long proximoId(){
        propriedadeList.sort(Comparator.comparing(Propriedade::getId));
        return propriedadeList.size() > 0 ? Math.addExact(propriedadeList.get(propriedadeList.size()-1).getId(), 1) : 1;
    }

    public  List<Propriedade> findAll(){
        return propriedadeList;
    }

    public Optional<Propriedade> findById(Long id){
        return propriedadeList.stream().filter((l-> l.getId().equals(id))).findFirst();
    }

    public Optional<Propriedade> save(Propriedade propriedade) throws ValorInvalidoException {
        propriedade.setId(proximoId());
        propriedadeList.add(propriedade);
        return Optional.of(propriedade);
    }

    public static PropriedadeRepositoryFAKE getInstance(){
        if(propriedadeRepositoryFAKE == null){
            propriedadeRepositoryFAKE = new PropriedadeRepositoryFAKE();
        }
        return propriedadeRepositoryFAKE;
    }


}
