/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.sql.SQLException;

/**
 *
 * @author User
 */
public class DatabaseTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            DBcontroller.openConn();
            //DBcontroller.getEmployees();
            //DBcontroller.searchEmployee();
            DBmainMenuGUI newMenu = new DBmainMenuGUI();
            newMenu.setVisible(true);
        } catch (SQLException ex) {
            System.out.print("DatabaseTest.main() produced the following error:");
            System.out.print(ex);
        }
        
    }
    
}
