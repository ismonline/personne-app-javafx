package sn.ismonline.personneapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("/pages/personne.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("CRUD Personne");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DbConnexion db = new DbConnexion();
        db.getConnection();
        launch();
    }
}
