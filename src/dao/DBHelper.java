/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;

/**
 *
 * @author DEA-Elgun
 */
public class DBHelper {
    public static Connection getConnection()throws Exception{
        Locale.setDefault(Locale.ENGLISH);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/groupoffline?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
        return c;
    }
}
