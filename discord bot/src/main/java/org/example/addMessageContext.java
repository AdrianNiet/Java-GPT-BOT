package org.example;

public class addMessageContext {
    //el unico objetivo de esta funcion es concatenar el viejo contexto con el ultimo mensaje del usuario.
    //De esta forma el bot recuerda los mensajes que le han enviado.
    public String addMessageToContext(String context, String newMessage) {
        System.out.println("El nuevo contexto es: " + context + "| |" + newMessage);
        return context + "| |" + newMessage;

    }
}
