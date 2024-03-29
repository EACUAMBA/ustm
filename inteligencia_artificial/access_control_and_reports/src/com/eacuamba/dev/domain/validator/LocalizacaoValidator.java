package com.eacuamba.dev.domain.validator;


import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.Localizacao;

public class LocalizacaoValidator {
    public static void valida(Localizacao localizacao) throws ValorInvalidoException {
        if(localizacao.getDesignacao().trim().length() <= 2){
            throw new ValorInvalidoException("O nome da localização não deve ser inferior a 3 \"nome >= 3\"");
        }
    }
}
