package com.animals.converter;

import com.animals.model.Cat;
import com.animals.model.Category;
import com.animals.model.Status;
import com.animals.model.entity.Animal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CatToAnimalConverter implements Converter<Cat, Animal> {

    @Override
    public Animal convert(Cat cat) {
        cat.getImage().setId(null);
        return Animal
                .builder()
                .name(cat.getName())
                .description(cat.getDescription())
                .image(cat.getImage())
                .category(Category.CAT)
                .creationDate(LocalDate.now())
                .status(Status.AVAILABLE)
                .build();
    }

}
