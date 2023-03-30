package com.animals.client;

import com.animals.configuration.FeignCatConfig;
import com.animals.model.Cat;
import com.animals.model.entity.Image;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "cats", url = "${client.cat.baseUrl}", configuration = FeignCatConfig.class)
public interface CatClient {

    @GetMapping("/breeds")
    List<Cat> getAllBreeds();

    @GetMapping("/images/{id}")
    Image findById(@PathVariable("id") String id);

}
