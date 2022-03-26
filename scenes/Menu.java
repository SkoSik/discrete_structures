package discrete_structures.scenes;

import discrete_structures.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    public void changeScene(ActionEvent e) throws IOException {
        App.changeScene(((MenuItem) e.getSource()).getId());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
