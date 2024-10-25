package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class dbcheck {
    //Esta funcion comprobara si existe la base de datos. En caso de que no exista, la creara.
    //Esto es mas que nada para evitar errores.
    public void checkAndCreateTable() {
        Connection conn = null;
        Statement stmt = null;

        String url = "jdbc:postgresql://localhost:5432/gptdb";
        String user = "";
        String password = "";

        try {
            //Establecemos conexion
            conn = DriverManager.getConnection(url, user, password);
            //Creo el statement
            stmt = conn.createStatement();

            //Este es el string con los parametros de la tabla, puede que la cambie en el futuro.
            //Aun quiero pensar como mejorar el bot, puede que haga un indice y concatene el contexto de cada mensaje segun el usuario.
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "user_id VARCHAR(50) PRIMARY KEY," +
                    "user_name VARCHAR(100)," +
                    "Context TEXT" +
                    ");";

            //Ejecuto la consulta
            stmt.executeUpdate(sql);
            System.out.println("Tabla verificada o creada correctamente");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Siempre cerrar la conexion, para evitar errores.
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

