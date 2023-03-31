package com.animals.dto;

import com.animals.model.Category;
import com.animals.model.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {

    private String name;
    private String description;
    private String image;
    private Category category;
    private LocalDate creationDate;
    private Status status;

}
