package com.animals;

import com.animals.client.CatClient;
import com.animals.client.DogClient;
import com.animals.converter.AnimalsToDTOConverter;
import com.animals.converter.CatToAnimalConverter;
import com.animals.converter.DogToAnimalConverter;
import com.animals.dto.AnimalDTO;
import com.animals.exceptions.NotFoundException;
import com.animals.model.Cat;
import com.animals.model.Dog;
import com.animals.model.entity.Animal;
import com.animals.model.entity.Image;
import com.animals.service.AnimalsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnimalsControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CatToAnimalConverter catToAnimalConverter;

	@Autowired
	private DogToAnimalConverter dogToAnimalConverter;

	@Autowired
	private AnimalsToDTOConverter animalsToDTOConverter;

	@MockBean
	private AnimalsService service;

	@MockBean
	private CatClient catClient;

	@MockBean
	private DogClient dogClient;

	@SneakyThrows
	@Test
	void shouldCallIndexesAndReturnOk() {
		final Cat cat = buildCat();
		final Dog dog = buildDog();
		Mockito.when(this.catClient.getAllBreeds()).thenReturn(List.of(cat));
		Mockito.when(this.dogClient.getAllBreeds()).thenReturn(List.of(dog));

		mockMvc.perform(post("/animals/indexes")
						.contentType("application/json"))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void shouldFilterAnimals() {
		final Animal cat = this.catToAnimalConverter.convert(buildCat());
		final Animal dog = this.dogToAnimalConverter.convert(buildDog());

		final List<AnimalDTO> animals = Stream.of(cat, dog)
				.map(animalsToDTOConverter::convert)
				.collect(Collectors.toList());

		Mockito.when(this.service.filterAnimals(any(), any(), any(), any(), any()))
				.thenReturn(new PageImpl<>(animals));

		mockMvc.perform(get("/animals?term=Abyssinian&category=CAT&status=AVAILABLE&creationDate=2023-03-30&page=0&size=10&sort=name,asc")
						.contentType("application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalElements", is(2)));
	}

	@SneakyThrows
	@Test
	void shouldReturnNotFound() {
		Mockito.doThrow(new NotFoundException("Animals 1 not found!")).when(this.service).updateStatus(any(), any());
		mockMvc.perform(patch("/animals/3/status/ADOPTED")
						.contentType("application/json"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.message", is("Animals 1 not found!")));
	}

	private static Cat buildCat() {
		Cat cat = new Cat();
		cat.setName("Abyssinian");
		cat.setDescription("The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.");
		Image catImage = new Image();
		catImage.setWidth(1204);
		catImage.setHeight(1445);
		catImage.setUrl("https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg");
		cat.setImage(catImage);
		return cat;
	}

	private static Dog buildDog() {
		Dog dog = new Dog();
		dog.setName("Affenpinscher");
		dog.setBredFor("Small rodent hunting, lapdog");
		Image dogImage = new Image();
		dogImage.setWidth(1600);
		dogImage.setHeight(1199);
		dogImage.setUrl("https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg");
		dog.setImage(dogImage);
		return dog;
	}

}
