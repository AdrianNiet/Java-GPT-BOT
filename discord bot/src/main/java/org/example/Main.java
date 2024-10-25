package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main extends ListenerAdapter {
    public static void main(String[] args) throws Exception {
        //funcion main
        dbcheck db = new dbcheck();
        db.checkAndCreateTable();
        //Este es el token del bot de discord.
        //El bot de discord es simplemente una cuenta bot a la que nosotros nos conectamos.
        //La creamos desde el portal del developer y ahi simplemente le damos permisos y lo añadimos a nuestro servidor.
        String token = "";

        JDABuilder builder = JDABuilder.createDefault(token);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        builder.addEventListeners(new Messagemanagement());
        builder.build();
    }
}


