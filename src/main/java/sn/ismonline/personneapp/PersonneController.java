package sn.ismonline.personneapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PersonneController implements Initializable {

    @FXML
    private TableColumn<Personne, String> adresseCol;

    @FXML
    private TextField adresseTfd;

    @FXML
    private TableColumn<Personne, Integer> ageCol;

    @FXML
    private TextField ageTfd;

    @FXML
    private TableColumn<Personne, Integer> idCol;

    @FXML
    private TableColumn<Personne, String> nomCol;

    @FXML
    private TextField nomTfd;

    @FXML
    private TableView<Personne> personneTbv;

    @FXML
    private TableColumn<Personne, String> prenomCol;

    @FXML
    private TextField prenomTfd;

    @FXML
    private Button saveBtn;

    private DbConnexion db = new DbConnexion();
    private ResultSet rs;
    private int ok;

    @FXML
    void clearAction(ActionEvent event) {
        prenomTfd.setText("");
        nomTfd.setText("");
        adresseTfd.setText("");
        ageTfd.setText("");
    }

    @FXML
    void deleteAction(ActionEvent event) {
        String sql = "DELETE FROM personne WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearAction(event);
            saveBtn.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int id;

    @FXML
    void getData(MouseEvent event) {
        Personne p = personneTbv.getSelectionModel().getSelectedItem();
        id = p.getId();
        prenomTfd.setText(p.getPrenom());
        nomTfd.setText(p.getNom());
        adresseTfd.setText(p.getAdresse());
        ageTfd.setText(String.valueOf(p.getAge()));
        saveBtn.setDisable(true);
    }

    @FXML
    void saveAction(ActionEvent event) {
        String sql = "INSERT INTO personne VALUES(null, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, prenomTfd.getText());
            db.getPstm().setString(2, nomTfd.getText());
            db.getPstm().setInt(3, Integer.parseInt(ageTfd.getText()));
            db.getPstm().setString(4, adresseTfd.getText());
            ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearAction(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ObservableList<Personne> getPersonnes(){
        ObservableList<Personne> personnes = FXCollections.observableArrayList();
        try{
            String sql = "SELECT * FROM personne ORDER BY nom ASC";
            db.initPrepar(sql);
            rs = db.executeSelect();
            while(rs.next()){
                Personne p = new Personne();
                p.setId(rs.getInt("id"));
                p.setPrenom(rs.getString("prenom"));
                p.setNom(rs.getString("nom"));
                p.setAge(rs.getInt("age"));
                p.setAdresse(rs.getString("adresse"));
                personnes.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personnes;
    }

    void loadTable(){
        personneTbv.setItems(getPersonnes());
        idCol.setCellValueFactory(new PropertyValueFactory<Personne, Integer>("id"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
        ageCol.setCellValueFactory(new PropertyValueFactory<Personne, Integer>("age"));
        adresseCol.setCellValueFactory(new PropertyValueFactory<Personne, String>("adresse"));
    }

    @FXML
    void updateAction(ActionEvent event) {
        String sql = "UPDATE personne SET prenom = ?, nom = ?, age = ?, adresse = ? WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, prenomTfd.getText());
            db.getPstm().setString(2, nomTfd.getText());
            db.getPstm().setInt(3, Integer.parseInt(ageTfd.getText()));
            db.getPstm().setString(4, adresseTfd.getText());
            db.getPstm().setInt(5, id);
            ok = db.executeMaj();
            db.closeConnection();
            loadTable();
            clearAction(event);
            saveBtn.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
}
