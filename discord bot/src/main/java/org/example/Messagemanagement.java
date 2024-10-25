package org.example;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.io.IOException;
import java.sql.SQLException;

public class Messagemanagement extends ListenerAdapter {

    dbconnect dbcon = new dbconnect();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;  // Esto hace que ignore los mensajes de otros bots, incluido el mismo.
        }
        // Aqui sacamos la informacion del usuario
        String userId = event.getAuthor().getId();
        String message = event.getMessage().getContentRaw();
        String userName = event.getAuthor().getName();
        System.out.println("Mensaje recibido de: " + event.getAuthor().getName());
        System.out.println("Canal: " + event.getChannel().getName());
        System.out.println("Contenido del mensaje: " + event.getMessage().getContentRaw());

        // Llamamos la funcion para insertar los datos en la DB
        dbcon.UpdateUserContext(userId, userName, message);


        try {
            String gptResponse = gpt_api.callGPT(message, userName, userId);
            event.getChannel().sendMessage(gptResponse).queue();
        } catch (IOException e) {
            event.getChannel().sendMessage("Error al llamar a la API de GPT").queue();
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
