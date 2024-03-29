package com.eacuamba.dev.command_line_interface;

public enum MainMenuItems {
    PPRIMEIRO("1 - Adicionar propriedade;"),
    SEGUNDO("2 - Ver estatisticas das propriedades adicionadas (como por exemplo: valor total recebido ...);"),
    TERCEIRO("3 - Ver informações do desenvolvedor;"),
    FOURTH("4 - Ver relatorio de acessos de todos os utilizadores;"),
    SAIR("0 - Sair (terminar a execução da aplicação);");

    MainMenuItems(String itemName) {
        this.itemName = itemName;
    }

    private String itemName;

    public String getItemName() {
        return itemName;
    }

}
