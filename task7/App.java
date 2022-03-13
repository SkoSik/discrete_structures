package discrete_structures.task7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.*;

public class App extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Pane layout = FXMLLoader.load(App.class.getResource("/discrete_structures/task7/scene.fxml"));
        primaryStage.setScene(new Scene(layout));
        primaryStage.setTitle("#7");
        primaryStage.show();
    }
}