package discrete_structures;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;

public class App extends Application {
    private static App instance;
    private static MenuBar menuBar;
    private static Stage primaryStage;

    public static final int MAX_VARS = 10;

    public static int VARS = 0;
    public static int VMIN_BORDER = 2;
    public static int VMAX_BORDER = 3;
    public static boolean ATTEMPTS = true;
    public static int MAX_ATTEMPTS = 7;

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

    public static void openWeb(String url) {
        instance.getHostServices().showDocument(url);
    }
}