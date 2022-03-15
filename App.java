package discrete_structures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;

public class App extends Application {
    private static App instance;
    private static MenuBar menuBar;
    private static Stage primaryStage;

    public static void main(String[] args) {
        Application.launch();
    }

    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        instance = this;

        menuBar = FXMLLoader.load(App.class.getResource("/discrete_structures/fxmls/menu.fxml"));
        changeScene("main");
        primaryStage.setTitle("Дискретные структуры");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void changeScene(String fxml) throws IOException {
        Pane layout = FXMLLoader.load(App.class.getResource("/discrete_structures/fxmls/" + fxml + ".fxml"));
        layout.getChildren().addAll(menuBar);
        primaryStage.setScene(new Scene(layout));
    }

    public static void openWeb(String url){
        instance.getHostServices().showDocument(url);
    }
}