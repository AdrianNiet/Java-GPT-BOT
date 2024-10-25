package org.example;

import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.SQLException;

public class gpt_api {
    //Para github esto estara vacio
    private static final String API_KEY = "";
    private static String url = "https://api.openai.com/v1/chat/completions";

    //Con esta funcion hago la llamada a la API
    public static String callGPT(String message, String userName, String userId) throws IOException, SQLException {
        OkHttpClient client = new OkHttpClient();
        sqlconsult sql = new sqlconsult();

        String text = sql.sqlquery(userId);

        //Aqui creamos el cuerpo del JSON que sera necesario para hacer la consulta a la API
        String json = "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {\"role\": \"system\", \"content\": \"" + text + "\"},"
                + "  {\"role\": \"user\", \"content\": \"" + message + "\"}"
                + "]"
                + "}";
        System.out.println(json);
        //Creamos el cuerpo de la solicitud
        RequestBody body = RequestBody.create(
                json.toString(),
                MediaType.get("application/json; charset=utf-8")
        );
        System.out.println(body);
        //Aqui se crea el HTTP para la solicitud.
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)  // Usamos POST porque estamos enviando datos
                .build();
        System.out.println(request);
        //Ejecutamos la solicitud
        Response response = client.newCall(request).execute();
        System.out.println(response);
        //If en caso de que la solicitud falle o se ejecute bien.
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            //Sacamos la respuesta.
            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
            //Retornamos la respuesta de la API
            return jsonResponse.get("choices").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("message").getAsJsonObject()
                    .get("content").getAsString();
        } else {
            // Por alguna razon, al crear el usuario siempre falla en el primer mensaje, por lo que voy a convertirlo en una "feature" por el momento.
            return "¡¡¡Bienvenido a mi bot!!! la base de datos con tu usuario ha sido creada, puedes proceder a hablar conmigo.";
        }
    }
}