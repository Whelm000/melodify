package com.example.melodify.controller;

import com.alibaba.fastjson2.JSONObject;

import com.example.melodify.pojo.Rank;
import com.example.melodify.service.RankService;
import com.example.melodify.utils.Consts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Tag(name = "评价", description = "评价相关操作")
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 新增评价
     */
    @Operation(summary = "新增评价", description = "用户对歌单进行评价")
    @PostMapping("/rank/add")
    public Object add(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");

        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        boolean flag = rankService.insert(rank);
        if (flag) {
            jsonObject.put(Consts.CODE, 1);
            jsonObject.put(Consts.MSG, "评价成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, 0);
        jsonObject.put(Consts.MSG, "评价失败");
        return jsonObject;
    }

    /**
     * 计算平均分
     */
    @Operation(summary = "计算平均分", description = "根据歌单 ID 计算该歌单的评价平均分")
    @GetMapping("/rank")
    public Object rankOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        return rankService.rankOfSongListId(Integer.parseInt(songListId));
    }
}
