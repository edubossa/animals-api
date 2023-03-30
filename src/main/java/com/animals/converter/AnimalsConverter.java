package com.animals.converter;

import com.animals.dto.AnimalDTO;
import com.animals.model.entity.Animal;
import org.springframework.stereotype.Component;

@Component
public class AnimalsConverter implements Converter<AnimalDTO, Animal> {

    @Override
    public AnimalDTO toDto(Animal model) {
        return null;
    }

    @Override
    public Animal toModel(AnimalDTO dto) {
        return null;
    }

}
