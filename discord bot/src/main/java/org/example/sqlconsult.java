package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sqlconsult {
    //Objetivo de esta clase: sacar el contexto del usuario para actualizarlo mas adelante.
    public static String sqlquery(String userId) throws SQLException {
        addMessageContext add = new addMessageContext();
        limitContextSize limit = new limitContextSize();
        String resultado = null;
        String url = "jdbc:postgresql://localhost:5432/gptdb";
        String user = "postgres";
        String password = "planta";

        try(Connection conn = DriverManager.getConnection(url, user, password)) {

            String query = "SELECT Context FROM users WHERE user_id = ?";
            PreparedStatement queryStmt = conn.prepareStatement(query);
            queryStmt.setString(1, userId);

            try (ResultSet rsquery = queryStmt.executeQuery()) {
                if (rsquery.next()) {
                    resultado = rsquery.getString("context");
                    System.out.println(resultado);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
