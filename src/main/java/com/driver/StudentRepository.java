package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    private HashMap<String,Student> studentHashMap=new HashMap<>();
    private HashMap<String,Teacher> teacherHashMap=new HashMap<>();
    private HashMap<String, List<String>> teacherandStudent=new HashMap<>();

    // checking teacher and student they exits or not
    public boolean isStudentexits(String Student){
        if(studentHashMap.containsKey(Student)) return true;
        return false;
    }
    public boolean isTeacherexits(String Teacher){
        if(teacherHashMap.containsKey(Teacher)) return true;
        return false;
    }
    // adding student and teacher to their respective hashmap
    public void addStudent(Student student){
        String name=student.getName();
        studentHashMap.put(name,student);
    }
    public void addTeacher(Teacher teacher){
        String name= teacher.getName();
        teacherHashMap.put(name,teacher);
    }
    // adding teacher pair with student
    public void addteacherandStudent(String Student,String Teacher){

        if(teacherandStudent.containsKey(Teacher)) teacherandStudent.get(Teacher).add(Student);
        else{
            teacherandStudent.put(Teacher,new ArrayList<>());
            teacherandStudent.get(Teacher).add(Student);
        }
    }
    //  getting the details of student and teacher
    public Student getStudent(String studentname){
        if(studentHashMap.containsKey(studentname)) return studentHashMap.get(studentname);
        return null;
    }
    public Teacher getTeacher(String Teachername){
        if(teacherHashMap.containsKey(Teachername)) return teacherHashMap.get(Teachername);
        return null;
    }
    public List<String> getStudentbyTeacher(String TeacherName){
        if(teacherandStudent.containsKey(TeacherName)) return teacherandStudent.get(TeacherName);
        return new ArrayList<>();
    }
    public List<String> getAllstudent(){
        ArrayList<String> ans = new ArrayList<>();
        for(String s: studentHashMap.keySet()){
            ans.add(s);
        }
        return ans;
    }
    public void deleteStudent(String StudentName){
        if(studentHashMap.containsKey(StudentName)) studentHashMap.remove(StudentName);
    }
    public void deleteTeacherfromStudent(String TeacherName){
        if(teacherandStudent.containsKey(TeacherName)) teacherandStudent.remove(TeacherName);
    }
    public void deleteteacherallstudent(String TeacherName){
        if(teacherHashMap.containsKey(TeacherName)){
            teacherHashMap.remove(TeacherName);
            List<String> list = getStudentbyTeacher(TeacherName);
            for(String s:list){
                deleteStudent(s);
            }
            deleteTeacherfromStudent(TeacherName);
        }
    }
    public void deleteallteacher(String TeacherName){
        List<String> delteacher = getStudentbyTeacher(TeacherName);
        for(String s:teacherHashMap.keySet()){
            delteacher.addAll(getStudentbyTeacher(TeacherName));
        }
        teacherHashMap.clear();
        teacherandStudent.clear();
        for(String s: delteacher){
            deleteStudent(s);
        }
    }
}
