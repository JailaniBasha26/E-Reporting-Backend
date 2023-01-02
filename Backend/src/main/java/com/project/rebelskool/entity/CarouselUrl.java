package com.project.rebelskool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name="carouselurl")
@Getter
@Setter
@NoArgsConstructor

public class CarouselUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //public Integer id;
    public String url;
}
