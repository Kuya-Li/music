package com.example.sp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodeData {
    private int code;
    private String msg;
    private Object data;
}
