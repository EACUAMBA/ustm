/*
 * Copyright (c) Edilson Alexandre Cuamba - 4 - 6 - 2022
 */

package com.eacuamba.dev.utils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Utils {
    public static  <T> List<T> baralhar(List<T> lista){
        SecureRandom secureRandom = new SecureRandom();
        HashSet<T> listaBaralhada = new HashSet<>();

        for(int i = lista.size(); i > 0; i--){
            listaBaralhada.add(lista.get(secureRandom.nextInt(lista.size())));
        }
        listaBaralhada.addAll(lista);
        return new ArrayList<>(listaBaralhada);
    }

    public static <T> List<T> baralhar(List<T> lista, int vezes){
        List<T> _lista = new ArrayList<>(lista);
        HashSet<T> listaBaralhada = new HashSet<>();
        if(vezes <= 0)
            throw new IllegalArgumentException("O valor de vezes deve ser maior ou igual a 1");
        for (int i = 1; i <= vezes; i++) {
            listaBaralhada.addAll(baralhar(_lista));
            _lista.clear();
            _lista.addAll(listaBaralhada);
            listaBaralhada.clear();
        }
        return _lista;
    }
}
