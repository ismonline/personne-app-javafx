package sn.ismonline.personneapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PersonneController {

    @FXML
    private TableColumn<?, ?> adresseCol;

    @FXML
    private TextField adresseTfd;

    @FXML
    private TableColumn<?, ?> ageCol;

    @FXML
    private TextField ageTfd;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nomCol;

    @FXML
    private TextField nomTfd;

    @FXML
    private TableView<?> personneTbv;

    @FXML
    private TableColumn<?, ?> prenomCol;

    @FXML
    private TextField prenomTfd;

    @FXML
    private Button saveBtn;

    @FXML
    void clearAction(ActionEvent event) {

    }

    @FXML
    void deleteAction(ActionEvent event) {

    }

    @FXML
    void getData(MouseEvent event) {

    }

    @FXML
    void saveAction(ActionEvent event) {

    }

    @FXML
    void updateAction(ActionEvent event) {

    }

}
