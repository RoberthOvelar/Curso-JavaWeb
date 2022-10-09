package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
    // Parâmetros de conexão
    private String drive = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
    private String user = "root";
    private String password = "";

    private Connection conectar() {
        Connection con = null;
        try {
            Class.forName(drive);
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (Exception e) {
            System.out.println("Esse errinho: " + e);
            return null;
        }
    }

    public void testeConexao() {
        try {
            Connection con = conectar();
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Funções de CRUD
    public void inserirContato(JavaBeans contato) {
        String sql = "insert into contatos (nome, fone, email) values (?, ?, ?)";
        try {
            Connection con = conectar();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, contato.getNome());
            pst.setString(2, contato.getFone());
            pst.setString(3, contato.getEmail());

            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<JavaBeans> buscarContatos() {
        ArrayList<JavaBeans> listaContatos = new ArrayList<>();
        String sql = "select * from dbagenda.contatos";
        try {
            // Iniciando conexão
            Connection con = conectar();
            // Preparando statement
            PreparedStatement pst = con.prepareStatement(sql);
            // Executando busca e atribuindo ao resultset
            ResultSet rs = pst.executeQuery();

            // Percorrendo resultset para preencher array
            while (rs.next()) {
                // Atribuindo valores de cada registro para variáveis
                String idcon = rs.getString(1);
                String nome = rs.getString(2);
                String fone = rs.getString(3);
                String email = rs.getString(4);
                // Preenchendo arraylist
                listaContatos.add(new JavaBeans(idcon, nome, fone, email));
            }
            // Encerrando conexão
            con.close();
            return listaContatos;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
