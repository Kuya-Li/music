package com.example.sp.bean;

import lombok.Data;

import java.math.BigInteger;

@Data
public class MusicCate {
    private BigInteger id;
    private String url;
    private String title;
    private String cate;
}
