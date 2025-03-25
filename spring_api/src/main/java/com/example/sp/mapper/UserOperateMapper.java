package com.example.sp.mapper;

import com.example.sp.bean.UserOperate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserOperateMapper {
    List<UserOperate> getUserOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int addUserOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int updateUserOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int addUserLikeOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int updateUserLikeOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int addUserFavOperate(@Param("uid") String uid, @Param("music_id") String music_id);
    int updateUserFavOperate(@Param("uid") String uid, @Param("music_id") String music_id);
}
