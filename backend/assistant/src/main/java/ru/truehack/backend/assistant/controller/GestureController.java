package ru.truehack.backend.assistant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.truehack.backend.assistant.model.GestureRequest;
import ru.truehack.backend.assistant.service.GestureService;

@RestController
@RequestMapping("/api/gesture")
public class GestureController {

    private final GestureService gestureService;

    @Autowired
    public GestureController(GestureService gestureService) {
        this.gestureService = gestureService;
    }

    @PostMapping
    public String handleGesture(@RequestBody GestureRequest request) {
        return gestureService.processGesture(request);
    }
}
