package javaFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLNumConverterController implements Initializable {


    @FXML
    private JFXListView<Label> lstVwSourceBase;

    @FXML
    private JFXPopup pppSourceBase;

    @FXML
    private JFXButton btnTest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int i = 0; i < 100; i++) {
            Label lbl = new Label("item " + i);
            lstVwSourceBase.getItems().add(lbl);
        }
    }

    @FXML
    void showPopUp(MouseEvent event) {
        pppSourceBase.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY());
    }

    private void initPopup() {
        JFXButton btnBase2 = new JFXButton("Binary");
        btnBase2.setPadding(new Insets(10));
        JFXButton btnBase3 = new JFXButton("Base3");
        btnBase3.setPadding(new Insets(10));
        JFXButton btnBase4= new JFXButton("Base4");
        btnBase4.setPadding(new Insets(10));

        VBox vBox = new VBox(btnBase2,btnBase3,btnBase4);

        pppSourceBase.setContent(vBox);
        pppSourceBase.setSource(lstVwSourceBase);
    }
}
