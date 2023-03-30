package com.animals.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer width;
    private Integer height;
    private String url;

}
