/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Lessons;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public class LessonsImpl implements LessonsDao {

    @Override
    public boolean LessonAdd(Lessons lessons) throws Exception {
        boolean isAdd = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into groupoffline.lesson ( subject, duration, documentation ) values (?,?,?);";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, lessons.getSubject());
                ps.setInt(2, lessons.getDuration());
                ps.setString(3, lessons.getDocumentation());
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
    public List<Lessons> getLessonList() throws Exception {
        List<Lessons> listLesson = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,subject,duration,documentation FROM groupoffline.lesson where active = 1;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lessons lesson = new Lessons();
                    lesson.setId(rs.getInt("id"));
                    lesson.setSubject(rs.getString("subject"));
                    lesson.setDuration(rs.getInt("duration"));
                    lesson.setDocumentation(rs.getString("documentation"));
                    listLesson.add(lesson);
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
        return listLesson;
    }

    @Override
    public Lessons getLessonById(int id) throws Exception {
        Lessons lesson = new Lessons();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id,subject,duration,documentation FROM groupoffline.lesson where id=? and active=1;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lesson.setId(rs.getInt("id"));
                    lesson.setSubject(rs.getString("subject"));
                    lesson.setDuration(rs.getInt("duration"));
                    lesson.setDocumentation(rs.getString("documentation"));
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
        return lesson;
    }

    @Override
    public boolean updateLesson(Lessons lessons) throws Exception {
        boolean isUpdate = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.lesson set subject = ?, duration = ?, documentation = ? where id = ? ;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {

                ps = c.prepareStatement(sql);
                ps.setString(1, lessons.getSubject());
                ps.setInt(2, lessons.getDuration());
                ps.setString(3, lessons.getDocumentation());
                ps.setInt(4, lessons.getId());
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
    public boolean deleteLesson(int id) throws Exception {
        boolean isDelete = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.lesson set active = 0 where id = ? ;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {

                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public List<Lessons> searchLesson(String key) throws Exception {
        List<Lessons> listLesson = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT subject, duration, documentation \n"
                + "FROM groupoffline.lesson \n"
                + "where active =1 and( lower(subject) like lower(?) \n"
                + "or lower(documentation) like lower(?));";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + key + "%");
                ps.setString(2, "%" + key + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    Lessons lesson = new Lessons();
                    lesson.setSubject(rs.getString("subject"));
                    lesson.setDuration(rs.getInt("duration"));
                    lesson.setDocumentation(rs.getString("documentation"));
                    listLesson.add(lesson);
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
        return listLesson;
    }

}
