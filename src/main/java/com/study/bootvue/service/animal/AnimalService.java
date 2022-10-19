package com.study.bootvue.service.animal;

import com.study.bootvue.domain.AnimalType;
import org.springframework.stereotype.Service;

@Service
public interface AnimalService {
    String getSound();

    AnimalType getType();
}
