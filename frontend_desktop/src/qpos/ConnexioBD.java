/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qpos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Enric
 */
public class ConnexioBD {

    public static Connection mycon() {

        Connection con = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/qpos", "root", "");
            return con;

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e);
            return null;
        }

    }

}
