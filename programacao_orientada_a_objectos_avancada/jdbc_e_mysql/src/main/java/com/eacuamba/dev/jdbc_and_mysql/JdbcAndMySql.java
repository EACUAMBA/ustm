package com.eacuamba.dev.jdbc_and_mysql;

import com.github.freva.asciitable.AsciiTable;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
public class JdbcAndMySql {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        DatabaseConnectionManager instance = DatabaseConnectionManager.getInstance();
        Connection connection = instance.getConnection();

        String options = """
                0. Sair
                1. Listar
                2. Registar
                3. Editar
                4. Remover
                                
                """;

        int optionChosen = -1;
        do {
            log.info("Choose a option");
            log.info(options);
            optionChosen = scanner.nextInt();

            switch (optionChosen) {
                case 0 -> {
                    System.exit(0);
                }
                case 1 -> {
                    String listData = listData(connection);
                    JOptionPane.showMessageDialog(null, listData);
                    optionChosen = -1;
                }

                case 2 -> {
                    log.info("Iteração de Registo de Produto");
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String descricao = JOptionPane.showInputDialog("Descrição:");
                    double venda = Double.parseDouble(JOptionPane.showInputDialog("Venda:"));
                    double custo = Double.parseDouble(JOptionPane.showInputDialog("Custo:"));
                    int quantidadeEmEstoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em estoque:"));

                    PreparedStatement preparedStatement = connection.prepareStatement("insert into produto (codigo, nome, description, venda, custo, quantidade_estoque) values (?, ?, ?, ?, ?, ?);");
                    preparedStatement.setInt(1, Instant.now().getNano());
                    preparedStatement.setString(2, nome);
                    preparedStatement.setString(3, descricao);
                    preparedStatement.setDouble(4, venda);
                    preparedStatement.setDouble(5, custo);
                    preparedStatement.setInt(6, quantidadeEmEstoque);
                    preparedStatement.execute();

                    log.info("Producto registado!");
                    optionChosen = -1;
                }

                case 3 -> {
                    String listData = listData(connection);

                    String codigo = JOptionPane.showInputDialog(listData + "\n\nInsira o codigo do produto:");

                    log.info("Iteração de Registo de Produto");
                    String nome = JOptionPane.showInputDialog("Nome:");
                    String descricao = JOptionPane.showInputDialog("Descrição:");
                    double venda = Double.parseDouble(JOptionPane.showInputDialog("Venda:"));
                    double custo = Double.parseDouble(JOptionPane.showInputDialog("Custo:"));
                    int quantidadeEmEstoque = Integer.parseInt(JOptionPane.showInputDialog("Quantidade em estoque:"));

                    PreparedStatement preparedStatement = connection.prepareStatement("update produto set nome = ?, description = ?, venda = ?, custo = ?, quantidade_estoque = ? where codigo = ?;");
                    preparedStatement.setString(1, nome);
                    preparedStatement.setString(2, descricao);
                    preparedStatement.setDouble(3, venda);
                    preparedStatement.setDouble(4, custo);
                    preparedStatement.setInt(5, quantidadeEmEstoque);
                    preparedStatement.setInt(6, Integer.parseInt(codigo));
                    preparedStatement.execute();

                    log.info("Producto editado!");
                    optionChosen = -1;
                }

                case 4 -> {
                    String listData = listData(connection);

                    String codigo = JOptionPane.showInputDialog(listData + "\n\nInsira o codigo do produto:");

                    PreparedStatement preparedStatement = connection.prepareStatement("delete from produto where codigo = ?;");
                    preparedStatement.setInt(1, Integer.parseInt(codigo));
                    preparedStatement.execute();

                    log.info("Producto apagado!");
                    optionChosen = -1;
                }
            }
        } while (optionChosen == -1);
    }

    private static String listData(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from produto order by codigo;");
        ResultSet resultSet = preparedStatement.executeQuery();

        String[] headerRow = {"Codigo", "Nome", "Descrição", "Venda", "Custo", "Quantidade no Estoque"};
        List<String[]> bodyRowList = new ArrayList<>();

        while (resultSet.next()) {
            String[] bodyRow = {
                    Objects.toString(resultSet.getInt("codigo")),
                    Objects.toString(resultSet.getString("nome")),
                    Objects.toString(resultSet.getString("description")),
                    Objects.toString(resultSet.getDouble("venda")),
                    Objects.toString(resultSet.getDouble("custo")),
                    Objects.toString(resultSet.getInt("quantidade_estoque"))
            };

            bodyRowList.add(bodyRow);
        }

        String[][] bodyRows = bodyRowList.toArray(String[][]::new);

        return AsciiTable.getTable(headerRow, new String[]{}, bodyRows);
    }
}