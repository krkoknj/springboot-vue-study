package com.study.bootvue.service.animal;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimalServiceFinder {

    private final List<AnimalService> animalServices;

    public AnimalServiceFinder(List<AnimalService> animalServices) {
        this.animalServices = animalServices;
    }

    public AnimalService finder(String type) {
        return animalServices.stream()
                .filter(animalService -> animalService.getType().name().equals(type))
                .findAny()
                .orElseThrow(RuntimeException::new);
    }

}
