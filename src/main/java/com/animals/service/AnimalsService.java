package com.animals.service;

import com.animals.client.CatClient;
import com.animals.client.DogClient;
import com.animals.converter.AnimalsToDTOConverter;
import com.animals.converter.CatToAnimalConverter;
import com.animals.converter.DogToAnimalConverter;
import com.animals.dto.AnimalDTO;
import com.animals.exceptions.NotFoundException;
import com.animals.model.Cat;
import com.animals.model.Category;
import com.animals.model.Status;
import com.animals.model.entity.Animal;
import com.animals.model.entity.Image;
import com.animals.repository.AnimalsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimalsService {

    private final CatClient catClient;
    private final DogClient dogClient;
    private final AnimalsRepository animalsRepository;
    private final CatToAnimalConverter catToAnimalConverter;
    private final DogToAnimalConverter dogToAnimalConverter;
    private final AnimalsToDTOConverter animalsToDTOConverter;

    @Async
    public void indexes() {
        saveAllCats();
        saveAllDogs();
    }

    public Page<AnimalDTO> filterAnimals(String term,
                                         Category category,
                                         Status status,
                                         LocalDate creationDate,
                                         Pageable pageable) {
        final Animal animal = Animal.builder()
                                .name(term)
                                .description(term)
                                .category(category)
                                .status(status)
                                .creationDate(creationDate)
                                .build();

        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("NAME", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("DESCRIPTION", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("CATEGORY", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("STATUS", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("CREATION_DATE", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        Example<Animal> example = Example.of(animal, matcher);

        final Page<Animal> page = this.animalsRepository.findAll(example, pageable);
        List<AnimalDTO> animals = page
                .stream()
                .map(this.animalsToDTOConverter::convert)
                .collect(Collectors.toList());
        return new PageImpl<>(animals, page.getPageable(), page.getTotalElements());
    }

    public void updateStatus(Long id, Status status) {
        Animal animal = this.animalsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Animals " + id + " not found!"));
        animal.setStatus(status);
        this.animalsRepository.save(animal);
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
                .collect(Collectors.toList());
        this.animalsRepository.saveAll(animals);
    }

    private void saveAllDogs() {
        final List<Animal> dogs = this.dogClient.getAllBreeds()
                .stream()
                .map(this.dogToAnimalConverter::convert)
                .collect(Collectors.toList());
        this.animalsRepository.saveAll(dogs);
    }

}
