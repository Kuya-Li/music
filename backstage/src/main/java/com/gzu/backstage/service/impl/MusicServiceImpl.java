package com.gzu.backstage.service.impl;

import com.gzu.backstage.entity.Music;
import com.gzu.backstage.mapper.MusicMapper;
import com.gzu.backstage.service.IMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 苏文杰
 * @since 2023-08-21
 */
@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements IMusicService {

}
