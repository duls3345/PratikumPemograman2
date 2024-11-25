package mapper;

import java.util.List;
import model.JenisMember;
import model.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberMapper {

    @Insert("INSERT INTO member (id, nama, jenis_member_id) VALUES (#{id}, #{nama}, #{jenisMember.id})")
    public Integer insert(Member member);

    @Update("UPDATE member SET nama = #{nama}, jenis_member_id = #{jenisMember.id} WHERE id = #{id}")
    public Integer update(Member member);  // Perbaikan: Menggunakan Member sebagai parameter

    @Delete("DELETE FROM member WHERE id = #{id}")
    public Integer delete(int id);  // Perbaikan: Menggunakan int untuk id langsung

    @Select("SELECT * FROM member")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama"),
        @Result(property = "jenisMember", column = "jenis_member_id",
        javaType = JenisMember.class, one = @One(select = "getJenisMember"))})
    List<Member> findAll();

    @Select("SELECT * FROM jenis_member WHERE id = #{id}")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama")})
    JenisMember getJenisMember(String id);  // Perbaikan: Menggunakan String karena parameter yang diteruskan adalah ID
}
