package red.mlz.study.service;

import red.mlz.study.entity.Student;
import red.mlz.study.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    public Student getStudentInfoById(BigInteger id) {
        return studentMapper.getById(id);
    }

    public List<Student> getAllStudentInfo() {
        return studentMapper.getAll();
    }

    public int createStudent(String name, Integer gender) {
        int now = (int) (System.currentTimeMillis() / 1000);

        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setCreateTime(now);
        student.setUpdateTime(now);
        student.setIsDeleted(0);

        return studentMapper.insert(student);
    }

    public int updateStudent(BigInteger studentId, String name, Integer gender) {
        int now = (int) (System.currentTimeMillis() / 1000);

        Student student = new Student();
        student.setId(studentId);
        student.setName(name);
        student.setGender(gender);
        student.setUpdateTime(now);

        return studentMapper.update(student);
    }

    public int deleteStudent(BigInteger id) {
        return studentMapper.deleteById(id, (int) (System.currentTimeMillis() / 1000));
    }

}
