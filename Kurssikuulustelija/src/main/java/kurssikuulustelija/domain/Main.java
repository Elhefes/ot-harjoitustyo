package kurssikuulustelija.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Application.launch;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.jdbc.core.JdbcTemplate;
import kurssikuulustelija.ui.UserInterface;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henripal
 */
//@SpringBootApplication
public class Main {
    
    public static void main(String[] args) {
//        SpringApplication.run(Main.class);
        //formatDatabase();
        launch(UserInterface.class);
    }
    
//    @Autowired
//    UserInterface ui;
//
//    public void run(String... args) throws Exception {
//        Stage window = new Stage();
//        ui.start(window);
//    }
    
    public static void formatDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./kurssikuulustelija", "sa", "")) {
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Exercise IF EXISTS;").executeUpdate();
            
            conn.prepareStatement("CREATE TABLE User(id INTEGER AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Exercise(id INTEGER AUTO_INCREMENT, question VARCHAR(100), answer VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
}
