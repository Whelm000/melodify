package com.example.melodify.mapper;


import com.example.melodify.pojo.Rank;



/**
 * 评价Dao
 */

public interface RankMapper {
    /**
     *增加
     */
    public int insert(Rank rank);

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);
}
