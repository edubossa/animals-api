package com.animals.converter;

import com.animals.model.Category;
import com.animals.model.Dog;
import com.animals.model.Status;
import com.animals.model.entity.Animal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DogToAnimalConverter implements Converter<Dog, Animal> {

    @Override
    public Animal convert(Dog dog) {
        dog.getImage().setId(null);
        return Animal
                .builder()
                .name(dog.getName())
                .description(dog.getBredFor())
                .image(dog.getImage())
                .category(Category.DOG)
                .creationDate(LocalDate.now())
                .status(Status.AVAILABLE)
                .build();
    }

}
