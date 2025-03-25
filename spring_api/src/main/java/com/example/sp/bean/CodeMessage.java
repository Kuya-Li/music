package com.example.sp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeMessage {
    private int code;
    private String msg;
}
