package com.example.prj2be.mapper.hs;

import com.example.prj2be.domain.hs.HsLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HsLikeMapper {

    @Delete("""
        DELETE FROM businesslike
        WHERE businessId = #{businessId}
        AND memberId = #{memberId}
        """)
    int delete(HsLike like);

    @Insert("""
        INSERT INTO businesslike(memberId, businessId) 
        VALUES (#{memberId},#{businessId})
        """)
    int insert(HsLike like);

    @Select("""
        SELECT COUNT(id)
        FROM businesslike
        WHERE businessId = #{businessId}
        """)
    int countByBoardId(Integer businessId);

    @Select("""
        SELECT *
        FROM businesslike
        WHERE businessId = #{businessId}
        AND memberId = #{memberId}
        """)
    HsLike selectByBusinessIdAndMemberId(Integer businessId, String memberId);

    @Delete("""
        DELETE FROM businesslike
        WHERE businessId = #{id}
        """)
    void deleteByBusinessId(Integer id);
}
