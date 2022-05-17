package com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades.domain.validator;

import com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.ii_semestre.programacao_i.plataforma_de_gestao_de_propriedades.domain.model.Localizacao;

public class LocalizacaoValidator {
    public static void valida(Localizacao localizacao) throws ValorInvalidoException {
        if(localizacao.getDesignacao().trim().length() <= 2){
            throw new ValorInvalidoException("O nome da localização não deve ser inferior a 3 \"nome >= 3\"");
        }
    }
}
