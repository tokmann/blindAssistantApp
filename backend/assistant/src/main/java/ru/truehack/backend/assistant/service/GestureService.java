package ru.truehack.backend.assistant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.truehack.backend.assistant.model.GestureRequest;
import ru.truehack.backend.assistant.model.GestureType;

@Service
public class GestureService {

    private final UILayoutAnalyzer analyzer;
    private final LlamaClient llamaClient;

    @Autowired
    public GestureService(UILayoutAnalyzer analyzer, LlamaClient llamaClient) {
        this.analyzer = analyzer;
        this.llamaClient = llamaClient;
    }

    public String processGesture(GestureRequest request) {
        GestureType gestureType = GestureType.fromString(request.getGesture());
        if (gestureType == null) {
            return "Unknown gesture: " + request.getGesture();
        }

        String prompt = analyzer.generatePrompt(request.getUiTreeJson(), gestureType, request.getLang());

        return llamaClient.sendPrompt(prompt);
    }
}
