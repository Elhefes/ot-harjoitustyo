/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author henripal
 */
public class UserInterface extends Application {
    private String title = "Kurssikuulustelija";
    
    @Override
    public void start(Stage window) {

        Label usernameText = new Label("Käyttäjätunnus");
        TextField usernameField = new TextField();
        Label passwordText = new Label("Salasana");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");
        Label infoText = new Label("Väärä käyttäjätunnus tai salasana");
        
        GridPane pane = new GridPane();
        pane.add(usernameText, 0, 0);
        pane.add(usernameField, 0, 1);
        pane.add(passwordText, 0, 2);
        pane.add(passwordField, 0, 3);
        pane.add(loginButton, 0, 4);
        pane.add(registerButton, 0, 5);
        
        loginButton.setOnAction(e->{
            String username = usernameField.getText();
            String password = passwordField.getText();
            
        });
        
        registerButton.setOnAction(e->{
            String username = usernameField.getText();
            String password = passwordField.getText();
            User user = new User(username, password);
            pane.getChildren().remove(infoText);
            pane.add(new Label("Uusi käyttäjätunnus luotu onnistuneesti!"), 0, 6);
            
        });
        
        pane.setPrefSize(500, 300);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(20, 20, 20, 20));
        
        Scene loginScreen = new Scene(pane);
        
        window.setScene(loginScreen);
        window.setTitle(title);
        window.show();
    }
    
    public static void formatDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./kurssikuulustelija", "sa", "")) {
            conn.prepareStatement("DROP TABLE User IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Exercise IF EXISTS;").executeUpdate();
            
            conn.prepareStatement("CREATE TABLE User(id INTEGER AUTO_INCREMENT, username VARCHAR(100), password VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Question(id INTEGER AUTO_INCREMENT, question VARCHAR(100), answer VARCHAR(100), PRIMARY KEY (id));").executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        formatDatabase();
        launch(UserInterface.class);
    }
    
    
}
