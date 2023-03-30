package com.animals.model.entity;

import com.animals.model.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


/**
 * http://localhost:8080/h2-console
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Lob
    private String description;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "image_id" )
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(length = 40)
    @Enumerated(EnumType.STRING)
    private Status status;

}
