package com.animals.converter;

import com.animals.dto.AnimalDTO;
import com.animals.model.entity.Animal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AnimalsToDTOConverter implements Converter<Animal, AnimalDTO> {

    @Override
    public AnimalDTO convert(Animal animal) {
        return AnimalDTO
                .builder()
                .name(animal.getName())
                .description(animal.getDescription())
                .image(animal.getImage().getUrl())
                .category(animal.getCategory())
                .creationDate(animal.getCreationDate())
                .status(animal.getStatus())
                .build();
    }

}
