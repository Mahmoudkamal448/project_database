/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project_database;

/**
 *
 * @author mahmoud kamal
 */
public class ContactPerson {
private int id;
private String name;
private String home_phone;

    public ContactPerson(int id, String name, String home_phone) {
        this.id = id;
        this.name = name;
        this.home_phone = home_phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }
}
