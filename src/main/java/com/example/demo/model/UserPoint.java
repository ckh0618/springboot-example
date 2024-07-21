package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Setter
@Getter
public class UserPoint {
    @Id
    private int userId ;
    private String userName ;
    private Boolean isActive ;
    private int point;
}
