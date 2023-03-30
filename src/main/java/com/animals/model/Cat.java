package com.animals.model;

import com.animals.model.entity.Image;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Cat {

    private Weight weight;
    private String id;
    private String name;

    @JsonProperty("cfa_url")
    private String cfaUrl;

    @JsonProperty("vetstreet_url")
    private String vetstreetUrl;

    @JsonProperty("vcahospitals_url")
    private String vcahospitalsUrl;

    private String temperament;
    private String origin;

    @JsonProperty("country_codes")
    private String countryCodes;

    @JsonProperty("countryCode")
    private String countryCode;

    private String description;

    @JsonProperty("life_span")
    private String lifeSpan;

    @JsonProperty("wikipedia_url")
    private String wikipediaUrl;

    @JsonProperty("reference_image_id")
    private String referenceImageId;

    private Image image;
}
