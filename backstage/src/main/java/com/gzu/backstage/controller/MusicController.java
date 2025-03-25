package com.gzu.backstage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gzu.backstage.service.IMusicService;
import com.gzu.backstage.entity.Music;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 苏文杰
 * @since 2023-08-21
 */
@RestController
@RequestMapping("/music")
        public class MusicController {

        @Resource
        private IMusicService musicService;

        // 新增或者更新
        @PostMapping
        public boolean save(@RequestBody Music music) {
                return musicService.saveOrUpdate(music);
        }

        @DeleteMapping("/{id}")
        public Boolean delete(@PathVariable Integer id) {
                return musicService.removeById(id);
        }

        @PostMapping("/del/batch")
        public boolean deleteBatch(@RequestBody List<Integer> ids) {
                return musicService.removeByIds(ids);
        }

        @GetMapping
        public List<Music> findAll() {
                return musicService.list();
        }

        @GetMapping("/{id}")
        public Music findOne(@PathVariable Integer id) {
                return musicService.getById(id);
        }

}

