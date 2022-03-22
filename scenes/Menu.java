package discrete_structures.scenes;

import discrete_structures.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    public void changeSceneSettings(ActionEvent e) throws IOException {
        App.changeScene("settings");
    }

    @FXML
    public void changeSceneInfo(ActionEvent e) throws IOException {
        App.changeScene("main");
    }

    @FXML
    public void changeSceneTask1(ActionEvent e) throws IOException {
        App.changeScene("task1");
    }

    @FXML
    public void changeSceneTask2(ActionEvent e) throws IOException {
        App.changeScene("task2");
    }

    @FXML
    public void changeSceneTask3(ActionEvent e) throws IOException {
        App.changeScene("task3");
    }

    @FXML
    public void changeSceneTask4(ActionEvent e) throws IOException {
        App.changeScene("task4");
    }

    @FXML
    public void changeSceneTask5(ActionEvent e) throws IOException {
        App.changeScene("task5");
    }

    @FXML
    public void changeSceneTask6(ActionEvent e) throws IOException {
        App.changeScene("task6");
    }

    @FXML
    public void changeSceneTask7(ActionEvent e) throws IOException {
        App.changeScene("task7");
    }

    @FXML
    public void changeSceneTask8(ActionEvent e) throws IOException {
        App.changeScene("task8");
    }

    @FXML
    public void changeSceneTask9(ActionEvent e) throws IOException {
        App.changeScene("task9");
    }

    @FXML
    public void changeSceneTask10(ActionEvent e) throws IOException {
        App.changeScene("task10");
    }

    @FXML
    public void changeSceneTask11(ActionEvent e) throws IOException {
        App.changeScene("task11");
    }

    @FXML
    public void changeSceneTask12(ActionEvent e) throws IOException {
        App.changeScene("task12");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
