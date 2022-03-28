package discrete_structures;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class GameLogic {
    Button checkAns;
    Label labelAns;
    Label labelAttemts;
    int attemts = 0;

    public GameLogic(Button button, Label label, Label attemts) {
        checkAns = button;
        labelAns = label;
        labelAttemts = attemts;

        labelAns.setTextFill(Color.color(0.7, 0, 0));

        if (!App.ATTEMPTS) {
            labelAttemts.setVisible(false);
        }
    }

    public void check(boolean result) {
        if (result) {
            labelAns.setTextFill(Color.color(0, 0.7, 0));
            labelAns.setText("Правильно");
            checkAns.setDisable(true);
        } else {
            labelAns.setText("Неправильно");
            labelAttemts.setText("Попыток: " + (++attemts));
            if (!App.ATTEMPTS || attemts >= App.MAX_ATTEMPTS) {
                checkAns.setDisable(true);
            }
        }
    }

    public void reload() {
        labelAns.setText("");
        labelAns.setTextFill(Color.color(0.7, 0, 0));
        attemts = 0;
        labelAttemts.setText("Попыток: 0");
        checkAns.setDisable(false);
    }

    public static int getVars() {
        return (App.VARS == 0) ? BinNumber.randInt(App.VMAX_BORDER - App.VMIN_BORDER + 1) + App.VMIN_BORDER : App.VARS;
    }
}