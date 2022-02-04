/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project_database;
import java.sql.*;
import java.util.Vector;


/**
 *
 * @author mahmoud kamal
 */
public class Model {
    public static ResultSet rs;
    public static Connection con;
    public static Statement stmt;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.print("working");
    }

    public static int connect() throws ClassNotFoundException{
    try{  
        Class.forName("com.mysql.jdbc.Driver");  
        con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/addressbook","root","");
        stmt=con.createStatement();
        rs=stmt.executeQuery("select * from contact");  
        return 0;
        }catch(SQLException e){
            return 1;
        }
    }

    public static void exit() throws SQLException{
        con.close();
        rs.close();
    }

    public static Vector<ContactPerson> get_all() throws SQLException, ClassNotFoundException{
        Vector<ContactPerson> MyContactPersonVector = new Vector<ContactPerson>();
        rs=stmt.executeQuery("select * from contact");
        while(rs.next()){
            ContactPerson the_one = new ContactPerson(rs.getInt(1), rs.getString(2), rs.getString(4));
            MyContactPersonVector.add(the_one);
        }
        return MyContactPersonVector;
    }

    public static void update(ContactPerson person_to_be_updated) throws SQLException{
        String query_String= new String("UPDATE contact SET name = '"+person_to_be_updated.getName()+"', home_phone = '"+person_to_be_updated.getHome_phone()+"' WHERE id="+person_to_be_updated.getId()+";");
        stmt.executeUpdate(query_String);
    }

    public static void delete(int _id) throws SQLException{
        String query_String= new String("DELETE FROM contact WHERE id="+_id+";");
        stmt.executeUpdate(query_String);
    }

    public static Vector<ContactPerson> Insert(String _name, String _phone) throws SQLException{
            String query_String= new String("INSERT INTO contact (name, home_phone) VALUES('"+_name+"','" +_phone +"');");
            stmt.executeUpdate(query_String);
            rs=stmt.executeQuery("select * from contact");
            Vector<ContactPerson> MyContactPersonVector = new Vector<ContactPerson>();
            while(rs.next()){
            ContactPerson the_one = new ContactPerson(rs.getInt(1), rs.getString(2), rs.getString(4));
            MyContactPersonVector.add(the_one);
        }
        return MyContactPersonVector;
    }
}
