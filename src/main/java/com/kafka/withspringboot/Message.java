package com.kafka.withspringboot;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Message
 * @Author ZhangY
 * @Date 2020/02/18 17:16
 * @Version 1.0.0
 * @Description 消息实体
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

}