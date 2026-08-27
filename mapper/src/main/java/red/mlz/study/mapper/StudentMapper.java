package red.mlz.study.mapper;

import red.mlz.study.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface StudentMapper {

    @Select("select * from student where id = #{id} and is_deleted = 0")
    Student getById(@Param("id") BigInteger id);

    @Select("select * from student where is_deleted = 0")
    List<Student> getAll();

    int insert(@Param("student") Student student);

    int update(@Param("student") Student student);

    @Update("update student set is_deleted=1, update_time=#{time} where id=#{id} limit 1")
    int deleteById(@Param("id") BigInteger id, @Param("time") Integer time);

}
