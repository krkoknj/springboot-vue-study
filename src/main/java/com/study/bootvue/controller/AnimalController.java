package com.study.bootvue.controller;

import com.study.bootvue.service.animal.AnimalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AnimalController {

//    private final AnimalServiceFinder animalServiceFinder;

    private final Map<String, AnimalService> animalServices;

    @GetMapping("/sound")
    public String sound(@RequestParam String type) {

        log.info("animalService={}", animalServices);
        AnimalService animalService = animalServices.get(type.toLowerCase() + "Service");
        return animalService.getSound();


//        AnimalService finder = animalServiceFinder.finder(type);
//        return finder.getSound();

//        if (type.equals("CAT")) {
//            return sound.getSound();
//        } else if (type.equals("DOG")) {
//            return "멍멍";
//        } else {
//            return "모르는 동물";
//        }
    }
}
