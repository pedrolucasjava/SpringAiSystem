package br.com.pedrocouto.spring_ai_pedrocouto.controller;


import br.com.pedrocouto.spring_ai_pedrocouto.service.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerativeAiController {

    private final ChatService service;

    public GenerativeAiController(ChatService service) {
        this.service = service;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return service.getResponse(prompt);
    }


}
