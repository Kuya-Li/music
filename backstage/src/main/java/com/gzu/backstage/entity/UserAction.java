package com.gzu.backstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
  @TableName("user_action")
@ApiModel(value = "UserAction对象", description = "")
public class UserAction implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("用户id")
      private Integer uid;

      @ApiModelProperty("id，用户操作表唯一主键")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("操作代号	点赞：L (Like)")
      private String oc;

      @ApiModelProperty("音乐id")
      private Integer musicId;

      @ApiModelProperty("操作时间")
      private LocalDateTime operateTime;


}
