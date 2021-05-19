/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Lessons;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public interface LessonsDao {
    public boolean LessonAdd (Lessons lessons) throws Exception;
    
    public List<Lessons> getLessonList() throws Exception;
    
    public Lessons getLessonById(int id) throws Exception;
    
    public boolean updateLesson(Lessons lessons) throws Exception;
    
    public boolean deleteLesson(int id) throws Exception;
    
    public List<Lessons> searchLesson( String key) throws Exception;
}
