package com.animals.converter;

import com.animals.model.Cat;
import com.animals.model.CategoryType;
import com.animals.model.Status;
import com.animals.model.entity.Animal;
import com.animals.model.entity.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                .category(new Category(1L, CategoryType.CAT))
                .creationDate(LocalDateTime.now())
                .status(Status.AVAILABLE)
                .build();
    }

}
