package ru.truehack.backend.assistant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;
import ru.truehack.backend.assistant.model.GestureType;

@Component
public class UILayoutAnalyzer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public String generatePrompt(String json, GestureType gesture, String lang) {
        try {
            JsonNode root = objectMapper.readTree(json);
            JsonNode simplifiedTree = cleanUITree(root);

            String languageInstruction = "Give answer in " + lang + " language.";

            return String.format(
                    "The user performed a %s gesture. Based on the following UI elements, briefly describe the screen's " +
                            "content. Focus on the general location and function of the elements relative to the screen. " +
                            "Avoid numerical details such as positions, dimensions, or exact coordinates. Provide a " +
                            "simple description from a few words for most important elements: %s",
                    gesture.name().toLowerCase(),
                    languageInstruction,
                    simplifiedTree.toPrettyString()
            );

        } catch (Exception e) {
            return "Error analyzing UI: " + e.getMessage();
        }
    }

    private JsonNode cleanUITree(JsonNode node) {
        // Если это объект
        if (node.isObject()) {
            ObjectNode objectNode = objectMapper.createObjectNode();

            // Копируем данные из исходного объекта в новый объект
            if (node.has("className")) objectNode.put("className", node.get("className").asText());
            if (node.has("text")) objectNode.put("text", node.get("text").asText());
            if (node.has("bounds")) objectNode.set("bounds", node.get("bounds"));

            // Обрабатываем дочерние элементы
            if (node.has("children") && node.get("children").isArray()) {
                ArrayNode childrenArray = objectMapper.createArrayNode();
                for (JsonNode child : node.get("children")) {
                    childrenArray.add(cleanUITree(child)); // Рекурсивно очищаем каждый дочерний элемент
                }
                objectNode.set("children", childrenArray);
            }

            return objectNode;
        }
        // Если это массив
        else if (node.isArray()) {
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (JsonNode arrayElement : node) {
                arrayNode.add(cleanUITree(arrayElement)); // Рекурсивно очищаем каждый элемент массива
            }
            return arrayNode;
        }
        // Если это другой тип (например, строка, число и т.д.), возвращаем сам элемент
        else {
            return node;
        }
    }
}
