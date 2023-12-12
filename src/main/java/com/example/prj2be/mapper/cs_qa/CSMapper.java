package com.example.prj2be.mapper.cs_qa;

import com.example.prj2be.domain.cs_qa.CustomerService;
import com.example.prj2be.domain.member.Member;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CSMapper {

   @Insert("""
      INSERT INTO customerService (csTitle, csCategory, csContent, csWriter)
      VALUES (#{csTitle}, #{csCategory}, #{csContent}, #{csWriter})
      """)
   int insert(CustomerService cs);

   @Select("""
      SELECT c.id, c.csTitle, c.csCategory, c.csWriter, m.nickName, c.inserted, c.csHit
      FROM customerService c JOIN member m ON c.csWriter = m.id
      WHERE c.csTitle LIKE #{keyword}
      or c.csCategory LIKE #{keyword}
      or m.nickName LIKE #{keyword}
      ORDER BY c.id DESC 
      LIMIT #{from}, 10
      """)
   List<CustomerService> selectAll(Integer from, String keyword);

   @Select("""
      SELECT c.id,
          c.csTitle, 
          c.csContent, 
             c.csWriter, 
          m.nickName,
          c.inserted
              FROM customerService c JOIN member m ON c.csWriter = m.id
              WHERE c.id = #{id}
            """)
   CustomerService selectById(Integer id);

   @Delete("""
      DELETE FROM customerService 
      WHERE id=#{id}
      """
   )
   int deleteById(Integer id);

   @Update("""
      UPDATE customerService
      SET csTitle = #{csTitle},
         csContent = #{csContent},
         csWriter = #{csWriter}
       
      WHERE id = #{id}
      """)
   int update(CustomerService cs);


//   @Delete("""
//        DELETE FROM customerService
//        WHERE csWriter = #{csWriter}
//        """)
//
//   int deleteByWriter(String writer);
//
//   @Select("""
//      SELECT id
//      FROM customerService
//      WHERE csWriter = #{id}
//      """)
//
//   List<Integer> selectIdListByMemberId(String writer);

   @Update("""
      UPDATE customerService
      SET csHit = csHit + 1
      WHERE id = #{id}
      """)
   void increaseHit(int id);

   @Select("""
        SELECT COUNT(*) FROM customerService
        WHERE csTitle LIKE #{keyword}
           OR csContent LIKE #{keyword}
           OR csCategory LIKE #{keyword}
        """)
   int countAll(String keyword);
}
