package org.example;

import java.util.Arrays;

public class limitContextSize {
    //el objetivo de esta funcion es evitar que nos pasemos con los tokens de contexto.
    //Por el momento esta para evitar errores, en el futuro la revisare.
    public Boolean limitContext(String context, int maxMessages) {
        String[] messages = context.split("| |");
        if (messages.length <= maxMessages) {
            return false;
        } else {
            //Aun no se como limitar el contexto en profundidad,siempre se puede quitar trozos aleatorios.
            //Aunque, se podria hacer otra consulta a la propia api y que haga un resumen del contexto actual.
                return true;

        }
    }
}
