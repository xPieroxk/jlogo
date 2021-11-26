package it.unicam.cs.pa.jlogo_110944.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Window controller to see the result of the program.
 */
public class ResultController {

    private final Logger logger = Logger.getLogger("it.unicam.cs.pa.jlogo_110944.ResultController");
    @FXML
    private TextArea resultText;

    /**
     * Handles the onAction event of the Close button.
     */
    @FXML
    public void handleClose(ActionEvent actionEvent) {
        logger.info("Close button clicked");
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void initialize() {
        resultText.setEditable(false);
    }
}
