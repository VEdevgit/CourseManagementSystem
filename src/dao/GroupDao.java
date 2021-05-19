
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Model.Group;
import java.util.List;

/**
 *
 * @author DEA-Elgun
 */
public interface GroupDao {
    public boolean GroupAdd(Group group) throws Exception;
    
    public List<Group> getGroupList() throws Exception;
    
    public Group getGroupById(int id) throws Exception;
    
    public boolean updateGroup(Group group) throws Exception;
    
    public boolean deleteGroup(int id) throws Exception;
    
    public List<Group> searchGroup(String key) throws Exception;
}
