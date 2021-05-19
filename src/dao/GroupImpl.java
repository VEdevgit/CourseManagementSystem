
package dao;

import Model.Group;
import Model.Students;
import Model.Teachers;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public class GroupImpl implements GroupDao {

    @Override
    public boolean GroupAdd(Group group) throws Exception {
        boolean isAdd = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Insert into groupoffline.group (group_No, student_id, teacher_id,documentation) values(?,?,?,?);";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, group.getGroup_No());
                ps.setInt(2, group.getStudent_id());
                ps.setInt(3, group.getTeacher_id());
                ps.setString(4, group.getDocumentation());
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
    public List<Group> getGroupList() throws Exception {
        List<Group> listGroup = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select g.id, g.group_No, s.name as Student_name,s.surname as Student_surname, t.name as Teacher_name, t.surname as Teacher_surname,t.documentation\n"
                + "FROM groupoffline.group g\n"
                + "INNER JOIN groupoffline.students  s on g.student_id = s.id\n"
                + "INNER JOIN groupoffline.teacher  t on g.teacher_id = t.id\n"
                + "where g.active = 1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getInt("id"));
                    group.setGroup_No(rs.getInt("group_No"));
                    Students students = new Students();
                    students.setName(rs.getString("Student_name"));
                    students.setSurname(rs.getString("Student_surname"));
                    Teachers teachers = new Teachers();
                    teachers.setName(rs.getString("Teacher_name"));
                    teachers.setSurname(rs.getString("Teacher_surname"));
                    group.setDocumentation(rs.getString("documentation"));
                    group.setStudents(students);
                    group.setTeachers(teachers);
                    listGroup.add(group);
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

        return listGroup;
    }

    @Override
    public Group getGroupById(int id) throws Exception {
        Group group = new Group();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select G.id,group_No,S.name StudentName, S.surname StudentSurname, T.name TeacherName, T.surname TeacherSurname, G.documentation \n"
                + "from groupoffline.group G\n"
                + "inner join students S on G.student_id = S.id\n"
                + "inner join teacher T on G.teacher_id = T.id where G.id = ? and G.active=1";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    group.setId(rs.getInt("id"));
                    group.setGroup_No(rs.getInt("group_No"));
                    Students students = new Students();
                    students.setName(rs.getString("StudentName"));
                    students.setSurname(rs.getString("StudentSurname"));
                    group.setStudents(students);
                    Teachers teachers = new Teachers();
                    teachers.setName(rs.getString("TeacherName"));
                    teachers.setSurname(rs.getString("TeacherSurname"));
                    teachers.setDocumentation("documentation");
                    group.setTeachers(teachers);
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
        return group;
    }

    @Override
    public boolean updateGroup(Group group) throws Exception {
        boolean isUpdate = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update  groupoffline.group set group_No=?, student_id=?, teacher_id=?, documentation=? where id=?;";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, group.getGroup_No());
                ps.setInt(2, group.getStudent_id());
                ps.setInt(3, group.getTeacher_id());
                ps.setString(4, group.getDocumentation());
                ps.setInt(5, group.getId());
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
    public boolean deleteGroup(int id) throws Exception {
        boolean isDelete = true;
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "Update groupoffline.group set active = 0 where id = ?";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
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
        return isDelete;
    }

    @Override
    public List<Group> searchGroup(String key) throws Exception {
        List<Group> listGroups = new ArrayList<>();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select g.id, g.group_No, s.name as Student_name,s.surname as Student_surname, t.name as Teacher_name, t.surname as Teacher_surname,g.documentation\n"
                + "FROM groupoffline.group g\n"
                + "INNER JOIN groupoffline.students  s on g.student_id = s.id\n"
                + "INNER JOIN groupoffline.teacher  t on g.teacher_id = t.id\n"
                + "where g.active = 1 and (\n"
                + "s.name like (?)\n"
                + "or s.surname like (?)\n"
                + "or t.name like(?)\n"
                + "or t.surname like(?)\n"
                + "or g.documentation like(?)\n"
                + ")";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                for (int i = 1; i <= 5; i++) {
                    ps.setString(i, key);
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    Group group = new Group();
                    group.setId(rs.getInt("id"));
                    group.setGroup_No(rs.getInt("group_No"));
                    Students students = new Students();
                    students.setName(rs.getString("Student_name"));
                    students.setSurname(rs.getString("Student_surname"));
                    Teachers teachers = new Teachers();
                    teachers.setName(rs.getString("Teacher_name"));
                    teachers.setSurname(rs.getString("Teacher_surname"));
                    group.setDocumentation(rs.getString("documentation"));
                    group.setStudents(students);
                    group.setTeachers(teachers);
                    listGroups.add(group);
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

        return listGroups;

    }

}
