package com.example.melodify.controller;

import com.alibaba.fastjson2.JSONObject;

import com.example.melodify.pojo.Comment;
import com.example.melodify.service.CommentService;
import com.example.melodify.utils.Consts;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 评论控制类
 */
@RestController
@RequestMapping("/comment")
@Tag(name = "评论", description = "评论相关接口")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加评论
     */
    @Operation(summary = "添加评论")
    @PostMapping("/add")
    public Object addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           //用户id
        String type = request.getParameter("type");               //评论类型（0 歌曲1 歌单）
        String songId = request.getParameter("songId");           //歌曲id
        String songListId = request.getParameter("songListId");   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.parseByte(type));
        if (Byte.parseByte(type) == 0) {
            comment.setSongId(Integer.parseInt(songId));
        } else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        boolean flag = commentService.insert(comment);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评论成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评论失败");
        return jsonObject;
    }

    /**
     * 修改评论
     */
    @Operation(summary = "修改评论")
    @PostMapping("/update")
    public Object updateComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();                   //主键
        String userId = request.getParameter("userId").trim();           //用户id
        String type = request.getParameter("type").trim();               //评论类型（0 歌曲1 歌单）
        String songId = request.getParameter("songId").trim();           //歌曲id
        String songListId = request.getParameter("songListId").trim();   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.parseByte(type));
        if (songId != null && songId.isEmpty()) {
            songId = null;
        } else {
            comment.setSongId(Integer.parseInt(songId));
        }

        if (songListId != null && songListId.isEmpty()) {
            songListId = null;
        } else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);

        boolean flag = commentService.update(comment);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    /**
     * 删除评论
     */
    @Operation(summary = "删除评论")
    @GetMapping("/delete")
    public Object deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        return commentService.delete(Integer.parseInt(id));
    }

    /**
     * 根据主键查询整个对象
     */
    @Operation(summary = "根据主键查询整个对象")
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        return commentService.selectByPrimaryKey(Integer.parseInt(id));
    }

    /**
     * 查询所有评论
     */
    @Operation(summary = "查询所有评论")
    @GetMapping("/allComment")
    public Object allComment() {
        return commentService.allComment();
    }

    /**
     * 查询某歌曲下的所有评论
     */
    @Operation(summary = "查询某个歌曲下的所有评论")
    @GetMapping("/commentOfSongId")
    public Object commentOfSongId(HttpServletRequest request) {
        String songId = request.getParameter("songId");          //歌曲id
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    /**
     * 查询某个歌单下的所有评论
     */
    @Operation(summary = "查询某个歌单下的所有评论")
    @GetMapping("/commentOfSongListId")
    public Object commentOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");          //歌曲id
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    /**
     * 给某个评论点赞
     */
    @Operation(summary = "给某个评论点赞")
    @PostMapping("/like")
    public Object like(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();           //主键
        String up = request.getParameter("up").trim();           //用户id

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean flag = commentService.update(comment);
        if (flag) {   //保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "点赞成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "点赞失败");
        return jsonObject;
    }
}
