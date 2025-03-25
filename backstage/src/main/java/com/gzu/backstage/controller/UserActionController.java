package com.gzu.backstage.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.gzu.backstage.service.IUserActionService;
import com.gzu.backstage.entity.UserAction;

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
@RequestMapping("/user-action")
        public class UserActionController {
    
@Resource
private IUserActionService userActionService;

// 新增或者更新
@PostMapping
public boolean save(@RequestBody UserAction userAction) {
        return userActionService.saveOrUpdate(userAction);
        }

@DeleteMapping("/{id}")
public Boolean delete(@PathVariable Integer id) {
        return userActionService.removeById(id);
        }

@PostMapping("/del/batch")
public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userActionService.removeByIds(ids);
        }

@GetMapping
public List<UserAction> findAll() {
        return userActionService.list();
        }

@GetMapping("/{id}")
public UserAction findOne(@PathVariable Integer id) {
        return userActionService.getById(id);
        }



        }

