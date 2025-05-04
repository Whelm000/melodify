package com.example.melodify.controller;

import com.alibaba.fastjson2.JSONObject;

import com.example.melodify.pojo.Collect;
import com.example.melodify.service.CollectService;
import com.example.melodify.utils.Consts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 收藏控制类
 */
@RestController
@RequestMapping("/collect")
@Tag(name = "收藏", description = "收藏相关操作")
public class CollectController {

    @Autowired
    private CollectService collectService;

    /**
     * 添加收藏
     */
    @Operation(summary = "添加收藏、取消收藏", description = "根据用户 ID 和歌曲 ID 进行收藏或取消收藏操作")
    @PostMapping("/add")
    public Object addCollect(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");           // 用户 id
        String type = request.getParameter("type");               // 收藏类型（0 歌曲 1 歌单）
        String songId = request.getParameter("songId");           // 歌曲 id
        if (songId == null || songId.isEmpty()) {
            jsonObject.put(Consts.CODE, 0);
            jsonObject.put(Consts.MSG, "收藏歌曲为空");
            return jsonObject;
        }
        if (collectService.existSongId(Integer.parseInt(userId), Integer.parseInt(songId))) {
            collectService.deleteByUserIdSongId(Integer.parseInt(userId), Integer.parseInt(songId));

            jsonObject.put(Consts.CODE, 2);
            jsonObject.put(Consts.MSG, "取消收藏成功");
            return jsonObject;
        }

        // 保存到收藏的对象中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(Byte.parseByte(type));
        collect.setSongId(Integer.parseInt(songId));

        boolean flag = collectService.insert(collect);
        if (flag) {   // 保存成功
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "收藏成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "收藏失败");
        return jsonObject;
    }

    /**
     * 删除收藏
     */
    @Operation(summary = "取消收藏", description = "根据用户 ID 和歌曲 ID 取消收藏")
    @GetMapping("/delete")
    public Object deleteCollect(HttpServletRequest request) {
        String userId = request.getParameter("userId");           // 用户 id
        String songId = request.getParameter("songId");           // 歌曲 id
        return collectService.deleteByUserIdSongId(Integer.parseInt(userId), Integer.parseInt(songId));
    }

    /**
     * 查询所有收藏
     */
    @Operation(summary = "查看所有收藏", description = "查询系统中所有的收藏记录")
    @GetMapping("/allCollect")
    public Object allCollect() {
        return collectService.allCollect();
    }

    /**
     * 查询某个用户的收藏列表
     */
    @Operation(summary = "用户的收藏列表", description = "根据用户 ID 查询该用户的收藏列表")
    @GetMapping("/collectOfUserId")
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");          // 用户 id
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }
}
