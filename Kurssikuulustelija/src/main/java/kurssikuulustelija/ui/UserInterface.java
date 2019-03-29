package kurssikuulustelija.ui;

import kurssikuulustelija.dao.ExerciseDao;
import kurssikuulustelija.dao.UserDao;
import kurssikuulustelija.domain.User;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author henripal
 */
@Component
public class UserInterface extends Application {

    private String title = "Kirjaudu kurssikuulustelijaan";

    @Autowired
//    private ExerciseDao questionDao;
    private UserDao userDao;
//    private JdbcTemplate jdbcTemplate;

    @Override
    public void start(Stage window) {
        //UserDao userDao = new UserDao();

        Label usernameText = new Label("Käyttäjätunnus");
        TextField usernameField = new TextField();
        Label passwordText = new Label("Salasana");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");
        Label infoText = new Label("");

        GridPane pane = new GridPane();
        pane.add(usernameText, 0, 0);
        pane.add(usernameField, 0, 1);
        pane.add(passwordText, 0, 2);
        pane.add(passwordField, 0, 3);
        pane.add(loginButton, 0, 4);
        pane.add(registerButton, 0, 5);
        pane.add(infoText, 0, 6);


        loginButton.setOnAction(e -> {
            try {
                userDao.create(new User("testi", "testi"));
            } catch (SQLException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
            String username = usernameField.getText();
            String password = passwordField.getText();
            infoText.setText("Käyttäjätunnus tai salasana on väärä");

        });

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (username.length() > 3 && password.length() > 3) {
                    User newAccount = new User(username, password);
                    System.out.println(username + " " + password);
                    User result = userDao.findByUsername(newAccount);
                    if (result != null) {
                        infoText.setText("Käyttäjätunnus on jo olemassa");
                    } else {
                        userDao.create(newAccount);
                        infoText.setText("Uusi käyttäjätunnus luotu!");
                    }

                } else {
                    infoText.setText("Käyttäjätunnus ja salasana pitää \nolla vähintään neljä merkkiä pitkiä");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }

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
}
