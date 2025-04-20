package ru.truehack.backend.assistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.truehack.backend.assistant.llama.LlamaServerRunner;

@SpringBootApplication
public class AssistantApplication {
	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
		LlamaServerRunner.startServer();
	}
}