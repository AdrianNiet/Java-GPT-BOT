package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class dbconnect {

    //Clase para insertar el contexto del usuario en la base de datos.
    public void UpdateUserContext(String userId, String userName, String message) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        addMessageContext add = new addMessageContext();
        limitContextSize limit = new limitContextSize();

        //Conexion con mi base de datos (en github esto estara vacio.)
        String url = "jdbc:postgresql://localhost:5432/gptdb";
        //No olvides poner tus datos si usar postgre, o la DB que uses
        String user = "";
        String password = "";

        try {
            conn = DriverManager.getConnection(url, user, password);
            String query = "SELECT Context FROM users WHERE user_id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            System.out.println(rs);
            //Aqui tenemos el if que comprueba si hay minimo 1 fila de resultados.
            //SI no lo hay, pasamos al else que creara al usuario con el contexto base.
            if (rs.next()) {
                String context = "";
                if (rs.next()) {
                    context = rs.getString("context");
                }

                context = add.addMessageToContext(context, message);
                Boolean limite = limit.limitContext(context, 100);


                //If en el caso de que lleguemos al maximo de contexto.
                //Esta parte no tiene else porque aun no se que hare con el contexto sobrante.
                //Puede que haga una llamada a la api y que haga un resumen del contexto.
                if (limite == false){
                String updateQuery = "INSERT INTO users (user_id, user_name, context) VALUES (?, ?, ?)" +
                        "ON CONFLICT (user_id) DO UPDATE SET context = users.context || E'' || EXCLUDED.context";
                PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                updateStmt.setString(1, userId);
                updateStmt.setString(2, userName);
                updateStmt.setString(3, context);
                updateStmt.executeUpdate();
                System.out.println("Contexto actualizado");
                rs.close();
                pstmt.close();
                updateStmt.close();
                conn.close();
                }
            } else {
                    //Creamos el usuario con contexto base.
                    String initialContext = "I'm using your API to create a discord bot, act like a woman called Lucia and remember the username " + userName + " in case you need it, you are a helpful assistant. You respond diligently and directly with specific data when needed. Your personality is calm and always profesional.";
                    initialContext = add.addMessageToContext(initialContext, message);
                    String insertQuery = "INSERT INTO users (user_id, user_name, context) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, userId);
                    insertStmt.setString(2, userName);
                    insertStmt.setString(3, initialContext);
                    insertStmt.executeUpdate();
                    System.out.println("USUARIO CREADO CON EXITO.");
                    rs.close();
                    pstmt.close();
                    insertStmt.close();
                    conn.close();
                }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
