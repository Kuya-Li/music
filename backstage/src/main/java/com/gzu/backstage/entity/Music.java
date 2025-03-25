package com.gzu.backstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 苏文杰
 * @since 2023-08-21
 */
@Getter
@Setter
  @ApiModel(value = "Music对象", description = "")
public class Music implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("歌曲id,唯一主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("歌曲名")
      private String musicName;

      @ApiModelProperty("歌手")
      private String singer;

      @ApiModelProperty("专辑")
      private String album;

      @ApiModelProperty("发行时间")
      private LocalDateTime issueDate;

      @ApiModelProperty("语种")
      private String language;

      @ApiModelProperty("标签")
      private String tag;

      @ApiModelProperty("图片地址")
      private String imgUrl;


}
