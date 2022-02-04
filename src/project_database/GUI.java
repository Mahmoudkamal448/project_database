/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package project_database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author mahmoud kamal
 */
public class GUI extends Application {
public static int counter = 0;
TextField ID;
TextField Name;
TextField Phone;
Vector<ContactPerson> MyContactPersonVector;
    
    @Override
    public void start(Stage primaryStage) throws SQLException, ClassNotFoundException {
        Model.connect();
        MyContactPersonVector = Model.get_all();
        Label for_ID = new Label("ID: ");
        ID = new TextField();
        FlowPane ID_pane = new FlowPane();
        ID_pane.getChildren().addAll(for_ID, ID);
        ID.setEditable(false);
//---------------------------------------------------------
        Label for_name = new Label("Name: ");
        Name = new TextField();
        FlowPane Name_pane = new FlowPane();
        Name_pane.getChildren().addAll(for_name, Name);
//---------------------------------------------------------
        Label for_Phone = new Label("Phone: ");
        Phone  = new TextField();
        
        FlowPane Phone_pane = new FlowPane();
        Phone_pane.getChildren().addAll(for_Phone,Phone);
//------------------------------------------------------
        ID.setText(String.valueOf(MyContactPersonVector.get(counter).getId()));
        Name.setText(MyContactPersonVector.get(counter).getName());
        Phone.setText(MyContactPersonVector.get(counter).getHome_phone());
//------------------------Buttons-----------------------
        GUI that = this;
//------------------------ First
        Button First = new Button("First");
        First.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                counter = 0;
                that.refresh();
        }});
//------------------------ Next
        Button Next = new Button("Next");
        Next.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            if(counter < MyContactPersonVector.size()-1){
                counter++;
                that.refresh();
            }}});
        
               
        Button Prev = new Button("Prev");
        Prev.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
            if(counter > 0){
                counter--;
                that.refresh();
            }}});
        Button Last = new Button("Last");
        Last.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                counter = MyContactPersonVector.size()-1;
                that.refresh();
            }});
//---------------------------New
        Button New = new Button("New");
        New.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event){
        
        try{
            Model.connect();
            MyContactPersonVector = Model.Insert(Name.getText(), Phone.getText());
            counter = MyContactPersonVector.size()-1;
            that.refresh();
            Model.exit();
        }catch(SQLException e){} catch (ClassNotFoundException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }});


//---------------------------Update
        Button Update = new Button("Update");
        Update.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ContactPerson the_updated_person = new ContactPerson(Integer.parseInt(ID.getText()), Name.getText(), Phone.getText());
            try{
                Model.connect();
                Model.update(the_updated_person);
                MyContactPersonVector.set(counter, the_updated_person);
                Model.exit();
            }catch(SQLException e){
            System.out.println("it didn't really work");
            }   catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }}});

//---------------------------Delete
        Button Delete = new Button("Delete");
        Delete.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ContactPerson the_updated_person = new ContactPerson(Integer.parseInt(ID.getText()), Name.getText(), Phone.getText());
            try{
                Model.connect();
                Model.delete(MyContactPersonVector.get(counter).getId());
                MyContactPersonVector.remove(counter);
                that.refresh();
                Model.exit();
            }catch(SQLException e){
            System.out.println("it didn't really work");
            }   catch (ClassNotFoundException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }}});
            FlowPane Buttons = new FlowPane(New, Update, Delete, First, Next, Prev, Last);
//---------------------------------------------------------
        Model.exit();
        FlowPane root = new FlowPane();
        root.setOrientation(Orientation.VERTICAL);

        root.getChildren().addAll(ID_pane,Name_pane,Phone_pane, Buttons);
        root.setVgap(10);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

public void refresh(){
    if(MyContactPersonVector.size()==counter){
    counter--;
    this.refresh();
    }
    ID.setText(String.valueOf(MyContactPersonVector.get(counter).getId()));
    Name.setText(MyContactPersonVector.get(counter).getName());
    Phone.setText(MyContactPersonVector.get(counter).getHome_phone());
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
