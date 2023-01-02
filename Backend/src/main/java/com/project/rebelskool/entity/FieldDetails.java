package com.project.rebelskool.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


//@Entity
@Getter
@Setter
@NoArgsConstructor

public class FieldDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public String name;
    public String range;
    public Integer year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange(){ return range;}

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
