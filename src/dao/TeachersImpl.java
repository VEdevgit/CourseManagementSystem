/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Lessons;
import Model.Teachers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public class TeachersImpl implements TeachersDao {

    @Override
    public boolean TeacherAdd(Teachers teachers) throws Exception {
        boolean isAdd = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Insert into groupoffline.teacher (name, surname, birthday, phone, address, lesson_id, documentation) values(?,?,?,?,?,?,?);";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, teachers.getName());
                ps.setString(2, teachers.getSurname());
                ps.setDate(3, teachers.getBirthday());
                ps.setLong(4, teachers.getPhone());
                ps.setString(5, teachers.getAddress());
                ps.setInt(6, teachers.getLesson_id());
                ps.setString(7, teachers.getDocumentation());
                ps.execute();
            }
        } catch (Exception e) {
            isAdd = false;
            e.printStackTrace();
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
    public List<Teachers> getTeacherList() throws Exception {
        List<Teachers> listTeachers = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select t.id as id, name,surname,birthday,phone,address, l.subject, t.documentation  "
                + "FROM groupoffline.teacher  t\n"
                + "INNER JOIN groupoffline.lesson  l on t.lesson_id = l.id\n"
                + "where t.active = 1;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Teachers teachers = new Teachers();
                    teachers.setId(rs.getInt("id"));
                    teachers.setName(rs.getString("name"));
                    teachers.setSurname(rs.getString("surname"));
                    teachers.setBirthday(rs.getDate("birthday"));
                    teachers.setAddress(rs.getString("address"));
                    Lessons lesson = new Lessons();
                    lesson.setSubject(rs.getString("subject"));
                    teachers.setLesson(lesson);
                    teachers.setDocumentation(rs.getString("documentation"));
                    listTeachers.add(teachers);
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
            if(rs!=null){
                rs.close();
            }
        }

        return listTeachers;
    }

    @Override
    public Teachers getTeacherById(int id) throws Exception {
        Teachers teachers = new Teachers();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "Select t.id as id, name,surname,birthday,phone,address, l.subject, t.documentation  "
                + "FROM groupoffline.teacher  t\n"
                + "INNER JOIN groupoffline.lesson  l on t.lesson_id = l.id where t.id=?and active =1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    teachers.setId(rs.getInt("id"));
                    teachers.setName(rs.getString("name"));
                    teachers.setSurname(rs.getString("surname"));
                    teachers.setBirthday(rs.getDate("birthday"));
                    teachers.setAddress(rs.getString("address"));
                    Lessons lesson = new Lessons();
                    lesson.setSubject(rs.getString("subject"));
                    teachers.setLesson(lesson);
                    teachers.setDocumentation(rs.getString("documentation"));
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
        }

        return teachers;
    }

    @Override
    public boolean updateTeacher(Teachers teacher) throws Exception {
        boolean isUpdate = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.teacher set name=?, surname=?, birthday=?, phone=?, address=?, lesson_id=?, documentation=? "
                + "where id=?; ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getSurname());
                ps.setDate(3, teacher.getBirthday());
                ps.setLong(4, teacher.getPhone());
                ps.setString(5, teacher.getAddress());
                ps.setInt(6, teacher.getLesson_id());
                ps.setString(7, teacher.getDocumentation());
                ps.setInt(8, teacher.getId());
                ps.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
    public boolean deleteTeacher(int id) throws Exception {
        boolean isDelete = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.teacher set active = 0 "
                + "where id=?; ";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            isDelete = false;
        } finally {
            if (c != null) {
                c.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return isDelete;
    }

    @Override
    public List<Teachers> searchTeacher(String key) throws Exception {
        List<Teachers> listTeacher = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT t.name,t.surname,t.birthday,t.phone,t.address,l.subject,t.lesson_id, t.documentation\n"
                + " FROM groupoffline.teacher t\n"
                + "INNER JOIN groupoffline.lesson  l on t.lesson_id = l.id\n"
                + " where t.active = 1 AND( lower(t.name) like lower(?)\n"
                + " or lower(t.surname) like lower(?)\n"
                + " or lower(t.address) like lower(?)\n"
                + " or lower(t.documentation) like lower(?));";
        try {
            c=DBHelper.getConnection();
            if(c!=null){
                ps= c.prepareStatement(sql);
                for(int i = 1; i<=4;i++){
                    ps.setString(i, "%"+key+"%");
                }
                rs=ps.executeQuery();
                while (rs.next()) {                    
                    Teachers teachers = new Teachers();
                    teachers.setName(rs.getString("name"));
                    teachers.setSurname(rs.getString("surname"));
                    teachers.setBirthday(rs.getDate("birthday"));
                    teachers.setAddress(rs.getString("address"));
                    Lessons lesson = new Lessons();
                    lesson.setSubject(rs.getString("subject"));
                    teachers.setLesson(lesson);
                    teachers.setDocumentation(rs.getString("documentation"));
                    listTeacher.add(teachers);
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
        return listTeacher;
    }

}
