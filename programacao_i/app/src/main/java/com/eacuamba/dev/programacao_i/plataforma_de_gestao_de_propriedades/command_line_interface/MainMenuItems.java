package com.eacuamba.dev.programacao_i.plataforma_de_gestao_de_propriedades.command_line_interface;

public enum MainMenuItems {
    PPRIMEIRO("1 - Adicionar propriedade;"), SEGUNDO("2 - Ver estatisticas das propriedades adicionadas (como por exemplo: valor total recebido ...);"), TERCEIRO("3 - Ver informações do desenvolvedor;"), SAIR("0 - Sair (terminar a execução da aplicação);");

    MainMenuItems(String itemName){
        this.itemName = itemName;
    }
    private String itemName;

    public String getItemName() {
        return itemName;
    }

}
