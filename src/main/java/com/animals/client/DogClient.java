package com.animals.client;

import com.animals.configuration.FeignDogConfig;
import com.animals.model.Dog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "dogs", url = "${client.dog.baseUrl}", configuration = FeignDogConfig.class)
public interface DogClient {

    @GetMapping("/breeds")
    List<Dog> getAllBreeds();

}
