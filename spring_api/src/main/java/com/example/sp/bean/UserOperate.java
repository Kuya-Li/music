package com.example.sp.bean;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserOperate {
    private BigInteger id;
    private int uid;
    private String music_id;
    private int likes;
    private int plays;
    private int favs;
}
