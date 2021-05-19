/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.*;
import Model.Students;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public class StudentsImpl implements StudentsDao {

    @Override
    public boolean StudentsAdd(Students students) throws Exception {
        boolean isAdd = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into groupoffline.students ( name, surname, birth_date, documentation ) values (?,?,?,?);";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, students.getName());
                ps.setString(2, students.getSurname());
                ps.setDate(3, students.getBirth_date());
                ps.setString(4, students.getDocumentation());
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            isAdd = false;
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return isAdd;
    }

    @Override
    public List<Students> getStudentList() throws Exception {
        List<Students> listStudent = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,name,surname,birth_date,documentation FROM groupoffline.students where active=1;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Students students = new Students();
                    students.setId(rs.getInt("id"));
                    students.setName(rs.getString("name"));
                    students.setSurname(rs.getString("surname"));
                    students.setBirth_date(rs.getDate("birth_date"));
                    students.setDocumentation(rs.getString("documentation"));
                    listStudent.add(students);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return listStudent;
    }

    @Override
    public Students getStudentById(int id) throws Exception {
        Students students = new Students();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,name,surname,birth_date,documentation FROM groupoffline.students where id=? and active =1;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    students.setId(rs.getInt("id"));
                    students.setName(rs.getString("name"));
                    students.setSurname(rs.getString("surname"));
                    students.setBirth_date(rs.getDate("birth_date"));
                    students.setDocumentation(rs.getString("documentation"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return students;
    }

    @Override
    public boolean updateStudent(Students student) throws Exception {
        boolean isUpdate = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.students set name=?, surname=?, birth_date=?, documentation=? where id = ?;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, student.getName());
                ps.setString(2, student.getSurname());
                ps.setDate(3, student.getBirth_date());
                ps.setString(4, student.getDocumentation());
                ps.setInt(5, student.getId());
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
            isUpdate = false;
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return isUpdate;
    }

    @Override
    public List<Students> searcheStudent(String key) throws Exception {
        List<Students> listStudent = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT name,surname,birth_date,documentation FROM groupoffline.students\n"
                + "where active= 1 and (lower(name) like lower(?)\n"
                + "or lower(surname) like lower(?)\n"
                + "or lower(documentation) like lower(?));";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                for (int i = 1; i <= 3; i++) {
                    ps.setString(i, "%" + key + "%");
                }
                rs = ps.executeQuery();
                while(rs.next()){
                 Students student = new Students();
                 student.setName(rs.getString("name"));
                 student.setSurname(rs.getString("surname"));
                 student.setBirth_date(rs.getDate("birth_date"));
                 student.setDocumentation(rs.getString("documentation"));
                 listStudent.add(student);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
            if(rs!=null){
                rs.close();
            }
        }
        return listStudent;
    }

}
