package com.project.rebelskool.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="incomestatementheaders")
@Getter
@Setter
@NoArgsConstructor
public class IncomeStatementHeaders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public int id;
    public String header;
}
