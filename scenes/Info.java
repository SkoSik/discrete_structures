package discrete_structures.scenes;

import discrete_structures.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Info implements Initializable {

    @FXML
    private Hyperlink hyperlink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hyperlink.setOnAction(event -> App.openWeb("https://github.com/SkoSik/discrete_structures"));
    }
}
