package ru.truehack.backend.assistant.llama;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LlamaServerClient {

    private static final String SERVER_URL = "http://127.0.0.1:8080/v1/chat/completions";

    // Метод для отправки запроса и получения ответа
    public static String getResponse(String prompt) throws IOException {
        // Формирование тела запроса в JSON
        String requestBody = "{\"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

        // Создание подключения к серверу
        URL url = new URL(SERVER_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Отправка запроса
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Получение ответа от сервера
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            // Преобразование строки ответа в JSONObject
            JSONObject jsonResponse = new JSONObject(response.toString());

            // Извлечение ответа модели
            String modelResponse = jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            return modelResponse;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    // Пример использования
    public static void main(String[] args) {
        try {
            String prompt = "Hello, tell me how are you.";
            String response = getResponse(prompt);
            System.out.println("Model response: " + response);
        } catch (IOException e) {
            System.err.println("Error communicating with the model server: " + e.getMessage());
        }
    }
}
