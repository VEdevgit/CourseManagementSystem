/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Students;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public interface StudentsDao {
    boolean StudentsAdd(Students students) throws Exception;
    
    List<Students> getStudentList() throws Exception;
    
    public Students getStudentById(int id) throws Exception;
    
    public boolean updateStudent(Students student) throws Exception;
    
    public List<Students> searcheStudent(String key) throws Exception;
    
}
