package com.rebelskool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class RebelSkoolStarter implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public  static  void  main (String[] args){
        SpringApplication.run(RebelSkoolStarter.class,args);
    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
