package com.animals.controller;

import com.animals.dto.AnimalDTO;
import com.animals.service.AnimalsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
public class AnimalsController {

    private final AnimalsService service;

    @Operation(summary = "Index animals (from all partners) in the database")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/indexes")
    public void indexes() {
        this.service.indexes();
    }

    @Operation(summary = "Filter all animals by term, category, status and creation date")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<AnimalDTO> getAllAnimals(@RequestParam(required = false) String term,
                                         @ParameterObject Pageable pageable) {
        return null;
    }

    @Operation(summary = "change animal status")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/status")
    public void updateStatus(@PathVariable("id") String id) {
        System.out.println("AnimalsController.updateStatus");
    }

}
