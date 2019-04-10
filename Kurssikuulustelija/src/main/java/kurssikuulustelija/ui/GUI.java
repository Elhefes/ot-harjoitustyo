package kurssikuulustelija.ui;

import kurssikuulustelija.dao.UserDao;
import kurssikuulustelija.domain.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kurssikuulustelija.dao.ExerciseDao;
import kurssikuulustelija.domain.Exercise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author henripal
 */
@Component
public class GUI extends JavaFxSpringService {

    private Scene loginScene;
    private Scene courseScene;
    private Scene settingsScene;
    private Scene exerciseCreatorScene;
    private Scene exerciseScene;
    private String currentCourse = "";
    private User currentUser;

    @Autowired
    UserDao userDao;
    @Autowired
    ExerciseDao exerciseDao;

    public void start(Stage stage) {

        //loginScene
        
        Label usernameText = new Label("Käyttäjätunnus");
        TextField usernameField = new TextField();
        Label passwordText = new Label("Salasana");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Kirjaudu");
        Button registerButton = new Button("Rekisteröidy");
        Label infoText = new Label("");
        loginButton.setDefaultButton(true);

        GridPane pane = new GridPane();
        pane.add(usernameText, 0, 0);
        pane.add(usernameField, 0, 1);
        pane.add(passwordText, 0, 2);
        pane.add(passwordField, 0, 3);
        pane.add(loginButton, 0, 4);
        pane.add(registerButton, 0, 5);
        pane.add(infoText, 0, 6);

        pane.setPrefSize(500, 300);
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(20, 20, 20, 20));

        loginScene = new Scene(pane);

        //courseScene
        
        Button logOutButton = new Button("Kirjaudu ulos");
        Label infoLabel = new Label("Valitse kurssi, jonka tehtäviä haluat harjoitella");
        Button titoButton = new Button("Tietokoneen toiminta");
        Button tiraButton = new Button("Tietorakenteet ja algoritmit");
        Button tikapeButton = new Button("Tietokantojen perusteet");

        BorderPane coursePlacement = new BorderPane();
        GridPane list = new GridPane();
        GridPane coursePane = new GridPane();

        coursePlacement.setTop(logOutButton);
        coursePlacement.setCenter(coursePane);
        list.add(titoButton, 1, 0);
        list.add(tiraButton, 2, 0);
        list.add(tikapeButton, 3, 0);
        coursePane.add(infoLabel, 0, 0);
        coursePane.add(list, 0, 1);

        list.setVgap(10);
        list.setHgap(10);
        coursePane.setPrefSize(700, 200);
        coursePane.setAlignment(Pos.CENTER);
        coursePane.setVgap(10);
        coursePane.setHgap(10);
        coursePane.setPadding(new Insets(20, 20, 20, 20));

        courseScene = new Scene(coursePlacement);

        //settingsScene
        
        BorderPane settingsPlacement = new BorderPane();
        Button backToCourseScene = new Button("Takaisin");
        Label courseTitle = new Label(currentCourse);
        Button exerciseButton = new Button("Harjoittele tehtäviä");
        Button createExercises = new Button("Luo uusia tehtäviä");

        GridPane settingsPane = new GridPane();
        settingsPlacement.setTop(backToCourseScene);
        settingsPlacement.setCenter(settingsPane);
        settingsPane.add(courseTitle, 0, 1);
        settingsPane.add(exerciseButton, 0, 2);
        settingsPane.add(createExercises, 0, 3);

        settingsPane.setPrefSize(800, 500);
        settingsPane.setAlignment(Pos.CENTER);
        settingsPane.setVgap(10);
        settingsPane.setHgap(10);
        settingsPane.setPadding(new Insets(20, 20, 20, 20));

        settingsScene = new Scene(settingsPlacement);

        //createExerciseScene
        
        BorderPane createExercisePlacement = new BorderPane();
        Button backToSettingsButton = new Button("Takaisin");
        Label createExerciseInfo = new Label("Kirjoita kysymys ja oikea vastaus luodaksesi uuden tehtävän kurssiin " + currentCourse);
        Label question = new Label("Tehtävänanto:");
        TextArea questionField = new TextArea();
        Label answer = new Label("Oikea vastaus:");
        TextArea answerField = new TextArea();
        Button submitNewExercise = new Button("Luo kysymys!");

        createExercisePlacement.setTop(backToSettingsButton);
        GridPane exerciseCreatorPane = new GridPane();
        createExercisePlacement.setCenter(exerciseCreatorPane);
        exerciseCreatorPane.add(createExerciseInfo, 0, 0);
        exerciseCreatorPane.add(question, 0, 1);
        exerciseCreatorPane.add(questionField, 0, 2);
        exerciseCreatorPane.add(answer, 0, 3);
        exerciseCreatorPane.add(answerField, 0, 4);
        exerciseCreatorPane.add(submitNewExercise, 0, 5);

        exerciseCreatorPane.setPrefSize(800, 500);
        exerciseCreatorPane.setAlignment(Pos.CENTER);
        exerciseCreatorPane.setVgap(10);
        exerciseCreatorPane.setHgap(10);
        exerciseCreatorPane.setPadding(new Insets(20, 20, 20, 20));

        exerciseCreatorScene = new Scene(createExercisePlacement);

        //exerciseScene
        
        BorderPane exercisePlacement = new BorderPane();
        Button backToSettingsButton2 = new Button("Takaisin");
        Label exerciseText = new Label("");
        TextArea answerArea = new TextArea();
        Button submitAnswer = new Button("Tarkista");
        Button correctAnswerButton = new Button("Näytä vastaus");

        exercisePlacement.setTop(backToSettingsButton2);
        GridPane exercisePane = new GridPane();
        exercisePlacement.setCenter(exercisePane);
        exercisePane.add(exerciseText, 0, 1);
        exercisePane.add(answerArea, 0, 2);
        exercisePane.add(submitAnswer, 0, 3);
        exercisePane.add(correctAnswerButton, 0, 4);

        exercisePane.setPrefSize(800, 500);
        exercisePane.setAlignment(Pos.CENTER);
        exercisePane.setVgap(10);
        exercisePane.setHgap(10);
        exercisePane.setPadding(new Insets(20, 20, 20, 20));

        exerciseScene = new Scene(exercisePlacement);
        
        //buttons' functionality

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (username.equals("debug")) {  //List every user to commandline
                try {
                    userDao.list().forEach(System.out::println);
                } catch (SQLException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            User user = new User(0, username, password);
            try {
                currentUser = userDao.checkCredentialsAndReturnUser(user);
                if (currentUser != null) {
                    infoText.setText(" ");
                    stage.setTitle("Valitse kurssi");
                    stage.setScene(courseScene);
                    usernameField.clear();
                    passwordField.clear();
                    System.out.println("UserId #" + currentUser.getId() + " logged in.");
                } else {
                    infoText.setText("Käyttäjätunnus tai salasana on väärä");
                    passwordField.clear();
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            infoText.setText("Käyttäjätunnus tai salasana on väärä");

        });

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            try {
                if (username.length() > 3 && password.length() > 3) {
                    User newAccount = new User(0, username, password);
                    User result = userDao.findByUsername(newAccount);
                    if (result != null) {
                        infoText.setText("Käyttäjätunnus on jo olemassa");
                    } else {
                        userDao.create(newAccount);
                        infoText.setText("Uusi käyttäjätunnus luotu!");
                        usernameField.clear();
                        passwordField.clear();
                    }

                } else {
                    infoText.setText("Käyttäjätunnus ja salasana pitää \nolla vähintään neljä merkkiä pitkiä");
                }
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        
        logOutButton.setOnAction(e -> {
            stage.setTitle("Kirjaudu sisään Kurssikuulustelijaan");
            stage.setScene(loginScene);

        });

        titoButton.setOnAction(e -> {
            currentCourse = "Tietokoneen toiminta";
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);

        });

        tiraButton.setOnAction(e -> {
            currentCourse = "Tietorakenteet ja algoritmit";
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);

        });

        tikapeButton.setOnAction(e -> {
            currentCourse = "Tietokantojen perusteet";
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);

        });

        backToCourseScene.setOnAction(e -> {
            stage.setTitle("Valitse kurssi");
            stage.setScene(courseScene);
  
        });

        exerciseButton.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(exerciseScene);
            Exercise exercise = exerciseDao.getRandom(currentCourse);
            if (exercise == null) exerciseText.setText("Ei kysymyksiä tällä kurssilla vielä.");
            else exerciseText.setText(exercise.getQuestion());
        });

        createExercises.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(exerciseCreatorScene);

        });

        submitNewExercise.setOnAction(e -> {
            String exerciseQuestion = questionField.getText();
            String exerciseAnswer = answerField.getText();
            Exercise exercise = new Exercise(currentCourse, exerciseQuestion, exerciseAnswer);
            questionField.clear();
            answerField.clear();
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);
            try {
                exerciseDao.create(exercise);
            } catch (SQLException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        backToSettingsButton.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);

        });
        
        backToSettingsButton2.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);

        });

        stage.setScene(loginScene);
        stage.setTitle("Kirjaudu sisään Kurssikuulustelijaan");
        stage.show();
    }
}
