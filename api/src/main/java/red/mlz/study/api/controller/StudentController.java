package red.mlz.study.api.controller;

import red.mlz.study.entity.Student;
import red.mlz.study.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{studentId}")
    public Student getStudentInfo(@PathVariable("studentId") BigInteger studentId) {
        return studentService.getStudentInfoById(studentId);
    }

    @GetMapping("/list")
    public List<Student> getStudentList() {
        return studentService.getAllStudentInfo();
    }

    @GetMapping("/addStudent")
    public String addStudent(@RequestParam("name") String name,
                             @RequestParam("gender") Integer gender) {
        int result = studentService.createStudent(name, gender);
        return result == 1 ? "成功" : "失败";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam("studentId") BigInteger studentId,
                                @RequestParam("name") String name,
                                @RequestParam("gender") Integer gender) {
        int result = studentService.updateStudent(studentId, name, gender);
        return result == 1 ? "成功" : "失败";
    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") BigInteger studentId) {
        int result = studentService.deleteStudent(studentId);
        return result == 1 ? "成功" : "失败";
    }

}
