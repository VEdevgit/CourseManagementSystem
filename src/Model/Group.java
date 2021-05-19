/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DEA-Elgun
 */
public class Group extends BaseEntity{
    private int group_No;
    private int student_id;
    private int teacher_id;
    private Students students;
    private Teachers teachers;

    public int getGroup_No() {
        return group_No;
    }

    public void setGroup_No(int group_No) {
        this.group_No = group_No;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Students getStudents() {
        return students;
    }

    public void setStudents(Students students) {
        this.students = students;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }
    
    
}
