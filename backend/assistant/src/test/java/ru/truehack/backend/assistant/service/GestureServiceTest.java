//package ru.truehack.backend.assistant.service;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import ru.truehack.backend.assistant.model.Element;
//import ru.truehack.backend.assistant.model.GestureRequest;
//import ru.truehack.backend.assistant.model.ScreenContent;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class GestureServiceTest {
//
//    private GestureService gestureService;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @BeforeEach
//    void setUp() {
//        // Настройка mock-объектов
//        MockitoAnnotations.openMocks(this);
//        gestureService = new GestureService(restTemplate);  // RestTemplate внедряется через Spring
//    }
//
//    @Test
//    void testAnalyzeGesture() {
//        // Создание тестового объекта GestureRequest
//        GestureRequest gestureRequest = new GestureRequest();
//        gestureRequest.setGesture("circle");
//        ScreenContent screenContent = new ScreenContent();
//        Element element = new Element();
//        element.setName("Button");
//        element.setType("Button");
//        element.setDescription("Click me");
//
//        screenContent.setElements(new Element[]{element});
//        gestureRequest.setScreenContent(screenContent);
//
//        // Подготовка мок-ответа от RestTemplate
//        String mockResponse = "{\"generated_text\": \"You have a button at the center of the screen. Click it to proceed.\"}";
//        ResponseEntity<String> responseEntity = ResponseEntity.ok(mockResponse);
//
//        // Настройка поведения RestTemplate
//        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
//                .thenReturn(responseEntity);
//
//        // Вызов метода для теста
//        String result = gestureService.analyzeGesture(gestureRequest);
//
//        // Проверка результата
//        assertTrue(result.contains("Gesture detected: Circle"));
//        assertTrue(result.contains("Generated description: You have a button at the center of the screen. Click it to proceed."));
//    }
//}
