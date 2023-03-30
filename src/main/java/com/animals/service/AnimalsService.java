package com.animals.service;

import com.animals.client.CatClient;
import com.animals.client.DogClient;
import com.animals.converter.CatToAnimalConverter;
import com.animals.converter.DogToAnimalConverter;
import com.animals.model.Cat;
import com.animals.model.entity.Animal;
import com.animals.model.entity.Image;
import com.animals.repository.AnimalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalsService {

    private final AnimalsRepository animalsRepository;
    private final CatToAnimalConverter catToAnimalConverter;
    private final DogToAnimalConverter dogToAnimalConverter;
    private final CatClient catClient;
    private final DogClient dogClient;

    @Async
    public void indexes() {
        saveAllCats();
        saveAllDogs();
    }

    private void saveAllCats() {
        final List<Cat> cats = this.catClient.getAllBreeds();
        cats.forEach(cat -> {
            if (Objects.isNull(cat.getImage())) {
                Image defaultImage = new Image();
                defaultImage.setWidth(1204);
                defaultImage.setHeight(1445);
                defaultImage.setUrl("https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg");
                cat.setImage(defaultImage);
            }
        });
        final List<Animal> animals = cats
                .stream()
                .map(this.catToAnimalConverter::convert)
                .toList();
        this.animalsRepository.saveAll(animals);
    }

    private void saveAllDogs() {
        final List<Animal> dogs = this.dogClient.getAllBreeds()
                .stream()
                .map(this.dogToAnimalConverter::convert)
                .toList();
        this.animalsRepository.saveAll(dogs);
    }

}
