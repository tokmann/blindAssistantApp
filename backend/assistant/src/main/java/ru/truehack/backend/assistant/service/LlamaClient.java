package ru.truehack.backend.assistant.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@Component
public class LlamaClient {

    //private static final int TIMEOUT = 10000;
    private static final String SERVER_URL = "http://127.0.0.1:8080/v1/chat/completions";

    public String sendPrompt(String prompt) {
        try {
            JSONObject body = new JSONObject();
            JSONArray messages = new JSONArray();
            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", prompt);
            messages.put(userMessage);
            body.put("messages", messages);

            URL url = new URL(SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            //connection.setConnectTimeout(TIMEOUT);
            //connection.setReadTimeout(TIMEOUT);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = body.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }

                JSONObject jsonResponse = new JSONObject(response.toString());
                return jsonResponse
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content")
                        .trim();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            return "Error connecting to Llama: " + e.getMessage();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
