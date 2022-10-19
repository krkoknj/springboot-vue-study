package com.study.bootvue.service.animal;

import com.study.bootvue.domain.AnimalType;
import org.springframework.stereotype.Component;

@Component
public class CatService implements AnimalService{

    @Override
    public String getSound() {
        return "냐옹";
    }

    @Override
    public AnimalType getType() {
        return AnimalType.CAT;
    }
}
