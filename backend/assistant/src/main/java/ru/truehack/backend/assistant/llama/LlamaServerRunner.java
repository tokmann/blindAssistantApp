package ru.truehack.backend.assistant.llama;

import java.io.*;

public class LlamaServerRunner {

    private static final String SERVER_PATH = "backend/native/llama.cpp/build/bin/Release/llama-server.exe";
    private static final String MODEL_PATH = "backend/native/llama.cpp/build/bin/Release/model/openhermes-2.5-mistral-7b.Q4_K_M.gguf";

    public static void startServer() {
        ProcessBuilder processBuilder = new ProcessBuilder(SERVER_PATH, "--model", MODEL_PATH);
        processBuilder.inheritIO();
        try {
            Process process = processBuilder.start();
            System.out.println("Llama server started...");

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Llama server exited with code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error starting the llama server: " + e.getMessage());
        }
    }

}
