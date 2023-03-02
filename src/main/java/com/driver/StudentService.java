package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    //1
    public void addStudent(Student student){
        repository.addStudent(student);
    }

    //2
    public void addTeacher(Teacher teacher){
        repository.addTeacher(teacher);
    }

    //3
    public void addteacherandStudent(String studentName,String teacherName){
        if(repository.isStudentexits(studentName) && repository.isTeacherexits(teacherName)){
            repository.addteacherandStudent(studentName,teacherName);
        }
    }

    //4
    public Student getStudent(String studentName){
        return repository.getStudent(studentName);
    }

    //5
    public Teacher getTeacher(String teacherName){
        return repository.getTeacher(teacherName);
    }

    //6
    public List<String> getStudentbyTeacher(String teacherName){
        return repository.getStudentbyTeacher(teacherName);
    }

    //7
    public List<String> getAllstudent(){
        return repository.getAllstudent();
    }

    //8
    public void deleteteacherallstudent(String teacherName){
        repository.deleteteacherallstudent(teacherName);
    }

    //9
    public void deleteallteacher(){
        repository.deleteallteacher();
    }
}