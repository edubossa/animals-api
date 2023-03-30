package com.animals.model;

import com.animals.model.entity.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Dog {

    private Weight weight;
    private Height height;
    private String id;
    private String name;

    @JsonProperty("bred_for")
    private String bredFor;

    @JsonProperty("breed_group")
    private String breedGroup;

    @JsonProperty("life_span")
    private String lifeSpan;

    private String temperament;
    private String origin;

    @JsonProperty("reference_image_id")
    private String referenceImageId;

    private Image image;

}
