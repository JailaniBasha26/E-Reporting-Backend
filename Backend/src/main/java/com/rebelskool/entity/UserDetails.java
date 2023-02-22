package com.rebelskool.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name="userdetails")
@Getter
@Setter
@NoArgsConstructor

public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String Email;
    public String Username;
    public String Password;
}
