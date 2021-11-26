package it.unicam.cs.pa.jlogo_110944.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * Controller of the window to create a new panel.
 */
public class NewPanelController {
    private final Logger logger = Logger.getLogger("it.unicam.cs.pa.jlogo_110944.NewPanelController");

    private boolean sizeSelected = false;

    @FXML
    private ComboBox<PanelSize> sizeSelection;
    @FXML
    private Button okButton;

    @FXML
    public void initialize() {
        sizeSelection.getItems().addAll(PanelSize.values());
        okButton.setVisible(sizeSelected);
    }

    /**
     * Handles the onAction event of the combo box.
     */
    @FXML
    public void handleSizeSelected() {
        logger.info("Size selected");
        sizeSelected = true;
        okButton.setVisible(true);
    }

    /**
     * Handles the onAction event of the Cancel button.
     */
    @FXML
    public void handleCancel(ActionEvent actionEvent) {
        logger.info("Cancel button clicked");
        sizeSelected = false;
        doClose(actionEvent);
    }

    /**
     * Handles the onAction event of the Ok button.
     */
    @FXML
    public void handleOk(ActionEvent actionEvent) {
        logger.info("Ok button clicked");
        doClose(actionEvent);
    }

    /**
     * Closes the window.
     */
    private void doClose(ActionEvent actionEvent) {
        ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).close();
    }

    /**
     * Returns the size chosen for the panel.
     */
    public PanelSize getSize() {
        return sizeSelection.getValue();
    }

    /**
     * Determines if the panel size is chosen.
     */
    public boolean isOk() {
        return this.sizeSelected;
    }
}
