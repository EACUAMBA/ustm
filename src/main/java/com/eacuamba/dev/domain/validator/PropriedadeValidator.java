package com.eacuamba.dev.domain.validator;

import com.eacuamba.dev.domain.exception.ValorInvalidoException;
import com.eacuamba.dev.domain.model.Propriedade;
import com.eacuamba.dev.domain.model.TipoPropriedade;

import java.math.BigDecimal;

public class PropriedadeValidator {
    public static  void valida(Propriedade propriedade) throws ValorInvalidoException {


        if(propriedade.getTipoPropriedade().equals(TipoPropriedade.VIVENDA)){
            if(propriedade.getNumeroQuartos() == 1){
                throw new ValorInvalidoException("O numero de quartos para Vivendas deve ser igual ou superior a dois (2) \"2 <= numero de quartos de vivenda\".");
            }
        }

        if(propriedade.getNumeroQuartos() > 3){
                throw new ValorInvalidoException("O numero de quartos não deve ser superior a 3, deve ser igual ou inferior \"numero de quartos <= 3\".");
        }
    }

    public static void validaNumeroQuartos(Propriedade propriedade) throws ValorInvalidoException {
        if(propriedade.getNumeroQuartos() == null){
            throw new ValorInvalidoException("Por favor insira um número de quartos.");
        }
        if(propriedade.getNumeroQuartos() <= 0){
            throw new ValorInvalidoException("Número de quartos não deve ser um valor inferior ou igual a zero \"0 => numero de quartos\".");
        }

        if(propriedade.getTipoPropriedade().equals(TipoPropriedade.VIVENDA)){
            if(propriedade.getNumeroQuartos() == 1){
                throw new ValorInvalidoException("O numero de quartos para Vivendas deve ser igual ou superior a dois (2) \"2 <= numero de quartos de vivenda\".");
            }
        }

        if(propriedade.getNumeroQuartos() > 3){
            throw new ValorInvalidoException("O numero de quartos não deve ser superior a 3, deve ser igual ou inferior \"numero de quartos <= 3\".");
        }
    }

    public static void validaValor(Propriedade propriedade) throws ValorInvalidoException {
            if(propriedade.getValor().compareTo(BigDecimal.ZERO) < 0){
                throw new ValorInvalidoException("O preço deve ser superior ou igual a 0 \"preço >= 0\".");
            }
    }

    public static void validaValorPago(Propriedade propriedade) throws ValorInvalidoException {
        if(propriedade.getValorPago().compareTo(BigDecimal.ZERO) < 0){
            throw new ValorInvalidoException("O valor pago deve ser superior ou igual a 0 \"valor pago >= 0\".");
        }
    }
}
