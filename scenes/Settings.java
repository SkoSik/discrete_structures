package discrete_structures.scenes;

import discrete_structures.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {

    private boolean choice;

    @FXML
    private Pane randV, varsV;

    @FXML
    private Label error;

    @FXML
    private CheckBox checkBoxRandVars, checkBoxAttemts;

    @FXML
    private ComboBox comboBoxMin, comboBoxMax, comboBoxVars;

    @FXML
    private TextField attemts;

    @FXML
    public void save() {
        error.setText("");
        try {
            App.MAX_ATTEMTS = Integer.parseInt(attemts.getText());
            App.ATTEMTS = checkBoxAttemts.isSelected();
            App.VARS = checkBoxRandVars.isSelected() ? 0 : parseToInt(comboBoxVars.getValue());

            int min = parseToInt(comboBoxMin.getValue());
            int max = parseToInt(comboBoxMax.getValue());

            if (min > max) error.setText("Ошибка ввода минимума и максимума");
            else {
                App.VMIN_BORDER = min;
                App.VMAX_BORDER = max;
            }
            load();
        } catch (Exception e) {
            error.setText("Поле 'Количество попыток' должно быть числовым");
            error.setText(e.toString());
        }
    }

    @FXML
    private void changeVisible() {
        randV.setVisible(choice);
        varsV.setVisible(!choice);
        choice = !choice;
    }

    @FXML
    private void changeVisibleAttemts() {
        attemts.setDisable(!checkBoxAttemts.isSelected());
    }

    private int parseToInt(Object a) {
        return Integer.parseInt(a.toString());
    }

    private void load() {
        checkBoxAttemts.setSelected(App.ATTEMTS);

        choice = (App.VARS == 0) ? true : false;
        checkBoxRandVars.setSelected(choice);
        changeVisible();

        comboBoxMin.setValue(App.VMIN_BORDER);
        comboBoxMax.setValue(App.VMAX_BORDER);
        comboBoxVars.setValue(App.VARS == 0 ? 1 : App.VARS);

        attemts.setDisable(!App.ATTEMTS);

        attemts.setText(String.valueOf(App.MAX_ATTEMTS));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }

}