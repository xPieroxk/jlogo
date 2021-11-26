package it.unicam.cs.pa.jlogo_110944.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Controller of the window for writing commands.
 */
public class WriteCommandsController {
    private final Logger logger = Logger.getLogger("it.unicam.cs.pa.jlogo_110944.WriteCommandsController");

    private boolean filledTextArea = false;

    @FXML
    public TextArea textWriteCommands;
    @FXML
    public Button okButton;

    /**
     * Handles the onAction event of the Cancel button.
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        logger.info("Cancel button clicked");
        doClose(actionEvent);
    }

    /**
     * Handles the onAction event of the Ok button.
     */
    @FXML
    public void handleOk(ActionEvent actionEvent) {
        logger.info("Ok button clicked");
        this.filledTextArea = true;
        doClose(actionEvent);
    }

    @FXML
    public void initialize() {
        okButton.setVisible(false);
        textWriteCommands.textProperty().addListener((observable, oldValue, newValue) -> okButton.setVisible(!newValue.equals("")));
    }

    /**
     * Closes the window.
     */
    private void doClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    /**
     * Determines if something has been written in the text area.
     */
    public boolean isOk() {
        return this.filledTextArea;
    }
}
