package br.com.pedrocouto.spring_ai_pedrocouto.controller;


import br.com.pedrocouto.spring_ai_pedrocouto.service.ChatService;
import br.com.pedrocouto.spring_ai_pedrocouto.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerativeAiController {

    private final ChatService service;
    private final RecipeService recipeService;

    public GenerativeAiController(ChatService service, RecipeService recipeService) {
        this.service = service;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return service.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseWithOptions(@RequestParam String prompt){
        return service.getResponseWithOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredientes,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "none") String dietaryRestrictions){
        return recipeService.createRecipe(ingredientes, cuisine, dietaryRestrictions);
    }

}
