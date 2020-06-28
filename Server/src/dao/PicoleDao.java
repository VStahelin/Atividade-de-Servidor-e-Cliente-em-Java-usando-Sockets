package dao;

import conector.ConnectionFactory;
import modelos.Picole;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PicoleDao{

    public boolean createTable() {
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `dbpicole`.`picole` (\n" +
                    "  `id` INT UNSIGNED NOT NULL,\n" +
                    "  `sabor` VARCHAR(45) NOT NULL,\n" +
                    "  `preco` VARCHAR(45) NOT NULL,\n" +
                    "  `marca` VARCHAR(45) NOT NULL,\n" +
                    "  `validade` VARCHAR(45) NOT NULL,\n" +
                    "  `peso` VARCHAR(45) NOT NULL,\n" +
                    "  `is_deleted` INT NOT NULL DEFAULT 0,\n" +
                    "  PRIMARY KEY (`id`))");
            stmt.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTable(){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("delete from picole");
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean inset(Picole picole){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;

        try {
            stmt = connection.prepareStatement("INSERT INTO picole (id , sabor, preco, marca, validade, peso) VALUES(?,?,?,?,?,?)");
            stmt.setInt(1,picole.getId());
            stmt.setString(2,picole.getSabor());
            stmt.setString(3, picole.getPreco());
            stmt.setString(4, picole.getMarca());
            stmt.setString(5, picole.getValidade());
            stmt.setString(6, picole.getPeso());
            stmt.executeLargeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public boolean updade(Picole picole, int newId){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE picole SET id = ?, sabor = ?, preco = ?, marca = ?, validade = ?, peso = ? WHERE id = ?  and is_deleted = 0");
            stmt.setInt(1, newId);
            stmt.setString(2,picole.getSabor());
            stmt.setString(3, picole.getPreco());
            stmt.setString(4, picole.getMarca());
            stmt.setString(5, picole.getValidade());
            stmt.setString(6, picole.getPeso());
            stmt.setInt(7, picole.getId());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro em Alterar:  " + e);
            e.printStackTrace();
            return false;
        }finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }
    public boolean softDelete(int id){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("UPDATE picole SET is_deleted = 1 where id = " + id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro em Excluido:  " + e);
            e.printStackTrace();
            return false;
        }finally {
            ConnectionFactory.closeConnection(connection, stmt);
        }
    }

    public Picole selectById(int id){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Picole picole = new Picole();
        try {
            stmt = connection.prepareStatement("SELECT * FROM picole WHERE id = " + id + " and is_deleted = 0");
            resultSet = stmt.executeQuery();
            while (resultSet.next()){
                if (resultSet.getInt("id") != 0){
                    picole.setId(resultSet.getInt("id"));
                    picole.setSabor(resultSet.getString("sabor"));
                    picole.setPreco(resultSet.getString("preco"));
                    picole.setMarca(resultSet.getString("marca"));
                    picole.setValidade(resultSet.getString("validade"));
                    picole.setPeso(resultSet.getString("peso"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (picole.getSabor().equals("") || picole.getPreco().equals("") || picole.getMarca().equals("") || picole.getValidade().equals("") || picole.getPeso().equals("")){
            return null;
        }

        return picole;
    }

    public List<Picole> softSelect(){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        List<Picole> picoles = new LinkedList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM picole WHERE is_deleted = 0");
            resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Picole picole = new Picole();
                picole.setId(resultSet.getInt("id"));
                picole.setSabor(resultSet.getString("sabor"));
                picole.setPreco(resultSet.getString("preco"));
                picole.setMarca(resultSet.getString("marca"));
                picole.setValidade(resultSet.getString("validade"));
                picole.setPeso(resultSet.getString("peso"));
                picoles.add(picole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return picoles;
    }
    public List<Picole> select(){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        List<Picole> picoles = new LinkedList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM picole");
            resultSet = stmt.executeQuery();
            while (resultSet.next()){
                Picole picole = new Picole();
                picole.setId(resultSet.getInt("id"));
                picole.setSabor(resultSet.getString("sabor"));
                picole.setPreco(resultSet.getString("preco"));
                picole.setMarca(resultSet.getString("marca"));
                picole.setValidade(resultSet.getString("validade"));
                picole.setPeso(resultSet.getString("peso"));
                picole.setDeleted(resultSet.getBoolean("is_deleted"));
                picoles.add(picole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return picoles;
    }

    public int rows(){
        Connection connection = ConnectionFactory.getConnction();
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        int rows = 0;
        try {
            stmt = connection.prepareStatement("SELECT id FROM picole");
            resultSet = stmt.executeQuery();
            while (resultSet.next()){
               rows ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

}
