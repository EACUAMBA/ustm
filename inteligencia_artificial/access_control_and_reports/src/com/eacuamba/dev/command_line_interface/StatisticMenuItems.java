package com.eacuamba.dev.command_line_interface;

public enum StatisticMenuItems {
    PRIMEIRO("1 - Quantidade de propriedades vendidas (para todos tipo propriedades e para um tipo especifico);"),
    SEGUNDO("2 - Calcular valor total recebido na venda de propriedades (para todos tipos de propriedade e para um tipo especifico);"),
    TERCEIRO("3 - Determinar o local que facturou mais (lista em ordem decrescente dos locais e seus facturamentos);"),
    QUARTO("4 - Valor total recebido pela empresa (tendo em conta os descontos);"),
    QUINTO("5 - Calcular lucro da empresa (tendo em conta que 70% do lucro é utilizado em despesa da empresa);"),
    SEXTO("6 - Calcular o valor total de desconto (tendo em mente que 10% são para flats tipo 3 e 5% para flats tipo 2);"),
    SETIMO("7 - Imprimir dados em forma de tabela;"),
    VOLTAR("10 - Voltar;"),
    SAIR("0 - Sair;");

    StatisticMenuItems(String itemName){
        this.itemName = itemName;
    }
    private String itemName;

    public String getItemName() {
        return itemName;
    }

}
