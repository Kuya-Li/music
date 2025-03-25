package com.example.sp.controller;

import com.example.sp.bean.CodeData;
import com.example.sp.bean.CodeMessage;
import com.example.sp.mapper.UserOperateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOperateController {
    @Autowired
    UserOperateMapper mapper;

    @PostMapping("/uop/music-play")
    public CodeMessage musicPlay(@RequestParam("uid") String uid, @RequestParam("music_id") String music){
        var music_id = "/song?id=" + music;
        mapper.addUserOperate(uid, music_id);
        return new CodeMessage(0, "success");
    }

    @PostMapping("/uop/music-like")
    public CodeMessage musicLike(@RequestParam("uid") String uid, @RequestParam("music_id") String music){
        var music_id = "/song?id=" + music;
        mapper.addUserLikeOperate(uid, music_id);
        return new CodeMessage(0, "success");
    }

    @PostMapping("/uop/music-fav")
    public CodeMessage musicFav(@RequestParam("uid") String uid, @RequestParam("music_id") String music){
        var music_id = "/song?id=" + music;
        mapper.addUserFavOperate(uid, music_id);
        return new CodeMessage(0, "success");
    }
}
