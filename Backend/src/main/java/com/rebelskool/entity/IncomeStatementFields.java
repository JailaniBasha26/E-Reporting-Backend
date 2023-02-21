package com.rebelskool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="incomestatementfields")
@Getter
@Setter
@NoArgsConstructor
public class IncomeStatementFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String header;
    public String fields;
    public String accountnos;
    public Boolean acceptonlynegativevalues;
    public Boolean issumfield;
    public Integer year;
}
