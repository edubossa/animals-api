package com.animals.controller;

import com.animals.dto.AnimalDTO;
import com.animals.model.Category;
import com.animals.model.Status;
import com.animals.service.AnimalsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/animals")
@Api(value = "Animals")
public class AnimalsController {

    private final AnimalsService service;

    @ApiOperation(value = "Index animals (from all partners) in the database")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/indexes")
    public void indexes() {
        this.service.indexes();
    }

    @ApiOperation(value = "Filter all animals by term, category, status and creation date")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)", defaultValue = "0"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sort criteria in the format: property(,asc|desc).")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Page<AnimalDTO> filterAnimals(@RequestParam(required = false) String term,
                                         @RequestParam(required = false) Category category,
                                         @RequestParam(required = false) Status status,
                                         @ApiParam("yyyy-MM-dd") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(required = false) LocalDate creationDate,
                                         @ApiIgnore Pageable pageable) {
        return this.service.filterAnimals(term, category, status, creationDate, pageable);
    }

    @ApiOperation(value = "change animal status")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/status/{status}")
    public void updateStatus(@PathVariable("id") Long id, @PathVariable("status") Status status) {
        this.service.updateStatus(id, status);
    }

}
