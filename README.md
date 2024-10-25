# BOT de Discord creado con Java

## Resumen

Discord es un chat que utilizan millones de personas al dia.

Es mas comunmente utilizado por Gamers, sin embargo algunas escuelas pueden llegar a utilizarlo debido a su systema de servidores y multiples bots que pueden ayudar a crear una gran experencia.

Este bot esta contruido en Java y se conecta a una Base de datos en PostgreSQL para almacenar los mensajes del usuario y tener cierta memoria.

## IDE Utilizado.

He utilizado Intellij por su facilidad para crear proyectos en Java, he necesitado que el IDE crease rapidamente el systema de archivos MAVEN.

## Funcionamiento:

El bot esta construido en Java, por lo que he dividido las distintas funciones en clases para que fuese facil y sencillo de entender y trabajar.

Al iniciar el bot, se ejecutara el metodo [checkAndCreateTable](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/dbcheck.java) para comprobar si la base de datos esta creada, si no, la creara para evitar errores.

Cuando se envia un mensaje, la clase [OnMessageReceived](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/Messagemanagement.java) procesara el mensaje y guardara el nombre del usuario, su ID y el mensaje para el bot.

Dicha clase llamara al metodo [UpdateUserContext](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/dbconnect.java) que comprobara si el usuario ya existe en la base de datos o no, si no existe lo creara con el contexto base y añadira el mensaje del usuario, si ya existe, solo actualizara la DB añadiendo al contexto el mensaje del usuario.

La clase que combina el mensaje del usuario con el contexto es [addMessageContext](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/addMessageContext.java) y la clase que revisa que no excedamos el limite de Tokens es [limitContextSize](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/limitContextSize.java).

Si todo ha funcionado bien, el metodo [gpt_API](https://github.com/AdrianNiet/Java-GPT-BOT/blob/main/discord%20bot/src/main/java/org/example/gpt_api.java) creara el JSON para hacer la llamada a la API y nos devolvera el resultado que publicara el bot en Discord.

## Ejemlpo de funcionamiento.

![image](https://github.com/user-attachments/assets/065fe582-7b1f-4b9e-87a3-8cc2a43209d5)






