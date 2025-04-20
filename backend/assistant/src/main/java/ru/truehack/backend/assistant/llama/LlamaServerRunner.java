package ru.truehack.backend.assistant.llama;

import java.io.*;

public class LlamaServerRunner {

    // Пути к исполняемым файлам
    private static final String SERVER_PATH = "backend/native/llama.cpp/build/bin/Release/llama-server.exe";
    private static final String MODEL_PATH = "backend/native/llama.cpp/build/bin/Release/model/openhermes-2.5-mistral-7b.Q4_K_M.gguf";

    // Метод для запуска сервера
    public static void startServer() {
        ProcessBuilder processBuilder = new ProcessBuilder(SERVER_PATH, "--model", MODEL_PATH);
        processBuilder.inheritIO();  // Это позволяет выводить логи на консоль, как если бы ты запускал сервер вручную
        try {
            // Запуск сервера
            Process process = processBuilder.start();
            System.out.println("Llama server started...");

            // Ожидаем завершения процесса, чтобы сервер продолжал работать
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Llama server exited with code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error starting the llama server: " + e.getMessage());
        }
    }

}
