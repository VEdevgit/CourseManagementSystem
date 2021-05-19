/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Teachers;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public interface TeachersDao {
    boolean TeacherAdd(Teachers teachers) throws Exception;
    
    List<Teachers> getTeacherList() throws Exception;
    
    Teachers getTeacherById(int id) throws Exception;
    
    boolean updateTeacher(Teachers teacher) throws Exception;
    
    boolean deleteTeacher(int id) throws Exception;
    
    List<Teachers> searchTeacher(String key) throws Exception;
    
}
