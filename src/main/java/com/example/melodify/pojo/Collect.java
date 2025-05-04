package com.example.melodify.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 收藏
 */
@Data
public class Collect implements Serializable {
    private Integer id;     //主键
    private Integer userId; //用户id
    private Byte type;      //收藏类型（0 歌曲 1 歌单）
    private Integer songId; //歌曲id
    private Integer songListId; //歌单id
    private Date createTime;    //收藏时间
}
