/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
/**
 *
 * @author User
 */
public class DBcontroller {
    
    
    private static String connectionString;
    private static Connection connection;
    private static Statement statement;
    private static ResultSet result;
    public static boolean openConn() throws SQLException
    {
        try
        {
            Class.forName("com.mysql.jdbc.driver");
        }
        catch (ClassNotFoundException ex)
                {
                    System.out.println(ex);
                }
        
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/workshopdb", "root", "");
            return true;
        }
         catch (SQLException e) {
            System.out.println("OpenConnection() produced the following error:");
            System.out.println(e);
        }
        return false;
    }


public static void getEmployees()
{

        try {
            statement = connection.createStatement();
            if (!statement.isClosed()) {
                String query = "SELECT * FROM `employee`;";
                 result = statement.executeQuery(query);
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String address = result.getString("address");
                    String telno = result.getString("telno");
                    String role = result.getString("role");
                    System.out.println( "ID: " + id + "\n" + 
                            "Name: " + name + "\n" + 
                            "Address: "+ address + "\n" +
                            "Telephone number: "+telno+"\n"+
                            "Role: "+role+"\n");
                    
                }
            }
        } catch (SQLException e) {
            System.out.println("DBcontroller.getEmployees() produced the following error:");
            System.out.println(e);
        }
}

public static void searchEmployeesForEditing(String id)
{
        DBeditEmployeeGUI newEdit = new DBeditEmployeeGUI();
        newEdit.setVisible(true);
     String querySearch = ("SELECT * FROM employee WHERE id = '" + id +"'");
    try
    {
        statement = connection.createStatement();
        if (!statement.isClosed())
        {
            result = statement.executeQuery(querySearch);
            while (result.next())
            {
                newEdit.setInfo(id, result.getString("name"), result.getString("address"), result.getString("telno"), result.getString("role"));
            }
        }
    }
    catch (SQLException e)
    {
        System.out.println("Error!");
        System.out.println(e);
    }
}

public static void insertEmployee(String name, String address, String telNo, String email, String role)
{
    //DBInsertGUI newInsert = new DBInsertGUI();
    String queryInsert = "INSERT INTO employee (name, address, telno,email, role)" + "VALUES('" +name+" ','" +address+" ','"+ telNo+"','"+role+"')"; 
    try
        {
            statement = connection.createStatement();
        if (!statement.isClosed())
        {
            statement.executeUpdate(queryInsert);
        }
        }
        catch (SQLException e)
                {
                    System.out.println("Error!");
                    System.out.println(e);
                }
        
}

public static void searchEmployee(int id)
{
    String querySearch = ("SELECT * FROM employee WHERE id = '" + id +"'");
    try
    {
        statement = connection.createStatement();
        if (!statement.isClosed())
        {
             result = statement.executeQuery(querySearch);
            while (result.next())
            {
             JOptionPane.showMessageDialog(null, "Employee name: " + result.getString("name") +"\n" 
                     + "Address: " + result.getString("address") + "\n" 
                     + "Tel.no: " + result.getString("telno")+ "\n" 
                     + "Role: " + result.getString("role") );   
            }
        }
    }
    catch (SQLException e)
    {
        System.out.println("Error!");
        System.out.println(e);
    }
}

public static void updateEmployees(int id,String name, String address, String telno, String role)
{
    String queryUpdateEmployee = ("UPDATE employee SET name='"+name +"',address = '"+address+"',telno='"+telno+"',role='"+role+"'WHERE id='"+id+"'");
    try
        {
            statement = connection.createStatement();
        if (!statement.isClosed())
        {
            statement.executeUpdate(queryUpdateEmployee);
        }
        }
        catch (SQLException e)
                {
                    System.out.println("Error!");
                    System.out.println(e);
                }
}

public static void deleteEmployees(String id)
{
     String queryDeleteEmployee = ("DELETE FROM employee WHERE id='"+id+"'");
    try
        {
            statement = connection.createStatement();
        if (!statement.isClosed())
        {
            JOptionPane.showMessageDialog(null, "Entry deleted!" );   
            statement.executeUpdate(queryDeleteEmployee);
        }
        }
        catch (SQLException e)
                {
                    System.out.println("Error!");
                    System.out.println(e);
                }
}

public static void addParts(String name, String description, Float price)
{
    String queryInsertPart = "INSERT INTO parts (name, partDescription, partPrice)" + "VALUES('" +name+" ','" +description+" ','" +price+" ')"; 
    try
        {
            statement = connection.createStatement();
        if (!statement.isClosed())
        {
             JOptionPane.showMessageDialog(null, "Part added!" );   
            statement.executeUpdate(queryInsertPart);
            
        }
        }
        catch (SQLException e)
                {
                    System.out.println("Error!");
                    System.out.println(e);
                }
}

public static void searchPartsForEditing(String id)
{
      DBeditPartsGUI newEditPart = new DBeditPartsGUI();
        newEditPart.setVisible(true);
     String querySearch = ("SELECT * FROM parts WHERE partID = '" + id +"'");
    try
    {
        statement = connection.createStatement();
        if (!statement.isClosed())
        {
            result = statement.executeQuery(querySearch);
            while (result.next())
            {
                newEditPart.setInfo(id, result.getString("name"), result.getString("partDescription"), result.getString("partCounter"), result.getString("partPrice"));
            }
        }
    }
    catch (SQLException e)
    {
        System.out.println("Error!");
        System.out.println(e);
    }
}
}