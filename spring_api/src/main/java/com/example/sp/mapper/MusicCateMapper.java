package com.example.sp.mapper;

import com.example.sp.bean.MusicCate;
import com.example.sp.bean.UserOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MusicCateMapper {
    List<MusicCate> getMusicCateByTitle(@Param("title") String title);
    List<MusicCate> getMusicCateByUrl(@Param("url") String url);
    List<String> getCateByUrl(@Param("url") String url);
}
