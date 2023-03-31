package com.animals.model.entity;

import com.animals.model.Category;
import com.animals.model.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ANIMAL")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Lob
    private String description;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "IMAGE_ID" )
    private Image image;

    @Column(name = "CATEGORY", length = 12)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "CREATION_DATE", columnDefinition = "DATE")
    private LocalDate creationDate;

    @Column(name = "STATUS", length = 40)
    @Enumerated(EnumType.STRING)
    private Status status;

}
