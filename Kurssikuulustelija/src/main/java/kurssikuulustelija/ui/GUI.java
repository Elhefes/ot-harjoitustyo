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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import kurssikuulustelija.dao.ExerciseDao;
import kurssikuulustelija.dao.PointDao;
import kurssikuulustelija.domain.Exercise;
import kurssikuulustelija.domain.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Luokka huolehtii sovelluksen käyttöliittymän muodostamisesta.
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
    private Exercise currentExercise;

    @Autowired
    UserDao userDao;
    @Autowired
    ExerciseDao exerciseDao;
    @Autowired
    PointDao pointDao;

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
        coursePlacement.setPadding(new Insets(10, 10, 10, 10));
        list.add(titoButton, 1, 0);
        list.add(tiraButton, 2, 0);
        list.add(tikapeButton, 3, 0);
        coursePane.add(infoLabel, 0, 0);
        coursePane.add(list, 0, 1);

        list.setVgap(10);
        list.setHgap(10);
        coursePane.setPrefSize(800, 500);
        coursePane.setAlignment(Pos.CENTER);
        coursePane.setVgap(10);
        coursePane.setHgap(10);
        coursePane.setPadding(new Insets(20, 20, 20, 20));

        courseScene = new Scene(coursePlacement);

        //settingsScene
        BorderPane settingsPlacement = new BorderPane();
        Button backToCourseScene = new Button("Takaisin");
        Label courseTitle = new Label("");
        Label exercisesCompleted = new Label("");
        CheckBox uniqueExercisesToggle = new CheckBox("Näytä vain vastaamattomia tehtäviä");
        Button exerciseButton = new Button("Harjoittele tehtäviä");
        Button createExercises = new Button("Luo uusia tehtäviä");
        exerciseButton.setDefaultButton(true);
        settingsPlacement.setPadding(new Insets(10, 10, 10, 10));

        GridPane settingsPane = new GridPane();
        settingsPlacement.setTop(backToCourseScene);
        settingsPlacement.setCenter(settingsPane);
        settingsPane.add(courseTitle, 0, 0);
        settingsPane.add(exercisesCompleted, 0, 1);
        settingsPane.add(uniqueExercisesToggle, 0, 3);
        settingsPane.add(exerciseButton, 0, 4);
        settingsPane.add(createExercises, 0, 5);

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
        createExercisePlacement.setPadding(new Insets(10, 10, 10, 10));

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
        Button deleteExercise = new Button("Poista tehtävä");
        Label exerciseText = new Label("");
        TextArea answerArea = new TextArea();
        Label indicator = new Label("");
        Button submitAnswer = new Button("Tarkista");
        Button correctAnswerButton = new Button("Näytä vastaus");
        submitAnswer.setDefaultButton(true);
        exercisePlacement.setPadding(new Insets(10, 10, 10, 10));

        exercisePlacement.setTop(backToSettingsButton2);
        GridPane exercisePane = new GridPane();
        exercisePlacement.setCenter(exercisePane);
        exercisePane.add(exerciseText, 0, 1);
        exercisePane.add(deleteExercise, 0, 2);
        exercisePane.add(answerArea, 0, 3);
        exercisePane.add(indicator, 0, 4);
        exercisePane.add(submitAnswer, 0, 5);
        exercisePane.add(correctAnswerButton, 0, 6);

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
            } else {
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
            }

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
            try {
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            stage.setScene(settingsScene);
            courseTitle.setText(currentCourse);
        });

        tiraButton.setOnAction(e -> {
            currentCourse = "Tietorakenteet ja algoritmit";
            stage.setTitle("Kurssikuulustelija");
            try {
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            stage.setScene(settingsScene);
            courseTitle.setText(currentCourse);
        });

        tikapeButton.setOnAction(e -> {
            currentCourse = "Tietokantojen perusteet";
            stage.setTitle("Kurssikuulustelija");
            try {
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            stage.setScene(settingsScene);
            courseTitle.setText(currentCourse);
        });

        backToCourseScene.setOnAction(e -> {
            stage.setTitle("Valitse kurssi");
            stage.setScene(courseScene);

        });

        exerciseButton.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(exerciseScene);
            try {
                Exercise exercise;
                if (uniqueExercisesToggle.isSelected()) {
                    exercise = exerciseDao.getUniqueRandomExercise(currentCourse, currentUser.getId());
                } else {
                    exercise = exerciseDao.getRandom(currentCourse);
                }

                if (exercise == null) {
                    exerciseText.setText("Ei kysymyksiä tällä kurssilla vielä.");
                } else {
                    exerciseText.setText(exercise.getQuestion());
                    currentExercise = exercise;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

        createExercises.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            createExerciseInfo.setText("Kirjoita kysymys ja oikea vastaus luodaksesi uuden tehtävän kurssiin " + currentCourse);
            stage.setScene(exerciseCreatorScene);

        });

        submitNewExercise.setOnAction(e -> {
            String exerciseQuestion = questionField.getText();
            String exerciseAnswer = answerField.getText();
            Exercise exercise = new Exercise(0, currentCourse, exerciseQuestion, exerciseAnswer);
            questionField.clear();
            answerField.clear();
            stage.setTitle("Kurssikuulustelija");
            stage.setScene(settingsScene);
            submitAnswer.setVisible(true);
            try {
                exerciseDao.create(exercise);
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

        backToSettingsButton.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            try {
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            stage.setScene(settingsScene);

        });

        backToSettingsButton2.setOnAction(e -> {
            stage.setTitle("Kurssikuulustelija");
            try {
                exercisesCompleted.setText("Tehtäviä suoritettu: " + pointDao.getUserPoints(currentCourse, currentUser.getId())
                        + "/" + exerciseDao.getCourseExerciseAmount(currentCourse));
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            stage.setScene(settingsScene);

        });

        deleteExercise.setOnAction(e -> {
            try {
                exerciseDao.deleteExercise(currentExercise.getQuestion());
                pointDao.deleteExercisePoints(currentExercise.getId());
                Exercise exercise = exerciseDao.getRandom(currentCourse);
                currentExercise = exercise;
                correctAnswerButton.setVisible(true);
                indicator.setText("");
                answerArea.clear();
                exerciseText.setText(exercise.getQuestion());
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

        });

        submitAnswer.setOnAction(e -> {
            if (submitAnswer.getText().equals("Seuraava")) {
                try {
                    Exercise exercise = exerciseDao.getRandom(currentCourse);
                    currentExercise = exercise;
                    correctAnswerButton.setVisible(true);
                    indicator.setText("");
                    answerArea.clear();
                    submitAnswer.setText("Tarkista");
                    exerciseText.setText(exercise.getQuestion());
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                String givenAnswer = answerArea.getText();
                if (givenAnswer.equals("debug")) {                      //List every exercise to commandline
                    exerciseDao.list().forEach(System.out::println);
                } else {
                    try {
                        if (givenAnswer.toLowerCase().equals(exerciseDao.getAnswer(currentExercise.getId()).toLowerCase())) {
                            Point point = new Point(currentCourse, currentUser.getId(), currentExercise.getId());
                            try {
                                pointDao.create(point);
                                //pointDao.list().forEach(System.out::println);
                            } catch (SQLException ex) {
                                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            indicator.setText("Oikein!");
                            submitAnswer.setText("Seuraava");
                            correctAnswerButton.setVisible(false);
                        } else {
                            indicator.setText("Väärä vastaus.");
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }

                }

            }

        });

        correctAnswerButton.setOnAction(e -> {
            try {
                answerArea.setText(exerciseDao.getAnswer(currentExercise.getId()));
                submitAnswer.setText("Seuraava");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            correctAnswerButton.setVisible(false);
        });

        stage.setScene(loginScene);
        stage.setTitle("Kirjaudu sisään Kurssikuulustelijaan");
        stage.show();
    }
}
