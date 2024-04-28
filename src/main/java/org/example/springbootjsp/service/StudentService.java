package org.example.springbootjsp.service;

import org.example.springbootjsp.entity.Student;
import org.example.springbootjsp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public void save(Student student){
        studentRepository.save(student);
    }
    public void delete(Student student){
        studentRepository.delete(student);
    }

    public Student findStudentById(Integer idStudent){
        return studentRepository.findById(idStudent).get();
    }

    public void updateStudent(Student student){
        studentRepository.save(student);
    }


}
