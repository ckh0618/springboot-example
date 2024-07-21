package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class TransferForm {
    private int fromUserId ;
    private int toUserId;
    private int point;
}
