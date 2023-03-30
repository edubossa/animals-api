package com.animals.dto;

import com.animals.model.CategoryType;
import com.animals.model.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnimalDTO {

    private String name;
    private String description;
    private String image; //TODO Object
    private CategoryType category;
    private LocalDateTime creationDate;
    private Status status;

}
