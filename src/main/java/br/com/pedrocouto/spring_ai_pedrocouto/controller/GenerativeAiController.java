package br.com.pedrocouto.spring_ai_pedrocouto.controller;


import br.com.pedrocouto.spring_ai_pedrocouto.service.ChatService;
import br.com.pedrocouto.spring_ai_pedrocouto.service.ImageService;
import br.com.pedrocouto.spring_ai_pedrocouto.service.RecipeService;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenerativeAiController {

    private final ChatService service;
    private final RecipeService recipeService;
    private final ImageService imageService;

    public GenerativeAiController(ChatService service, RecipeService recipeService, ImageService imageService) {
        this.service = service;
        this.recipeService = recipeService;
        this.imageService = imageService;
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

    @GetMapping("generate-image")
    public List<String> generateImages(@RequestParam String prompt,
                                       @RequestParam(defaultValue = "hd") String quality,
                                       @RequestParam(defaultValue = "1") Integer n,
                                       @RequestParam(defaultValue = "1024") Integer height,
                                       @RequestParam(defaultValue = "1024") Integer width
    ){
      ImageResponse response = imageService.generateImage(prompt, quality, n, height, width);
        List<String> imageUrls = response.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
        return imageUrls;
    }

}
