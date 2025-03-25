package com.gzu.backstage.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:用于返回统一的包装类数据
 * Author: SuWenJay
 * Date: 2023-08-17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;


    /**
     * 成功
     * @return 返回成功状态码，不反回数据
     */
    public static Result success(){
        return new Result(Constants.CODE_200,"",null);
    }

    /**
     * 成功
     * @param data 接受对象参数
     * @return 返回状态码以及data
     */
    public static Result success(Object data){
        return new Result(Constants.CODE_200,"",data);
    }

    /**
     * 错误
     * @param code 错误状态码
     * @param msg  错误消息
     * @return 返回错误状态码
     */
    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }

    /**
     * 无参错误
     * @return 返回错误代码，msg:系统错误
     */
    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null);
    }

}
