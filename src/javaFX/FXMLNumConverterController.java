package javaFX;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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

        initPopup();
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
        JFXButton btnBase4 = new JFXButton("Base4");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase5 = new JFXButton("Base5");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase6 = new JFXButton("Base6");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase7 = new JFXButton("Base7");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase8 = new JFXButton("Base8");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase9 = new JFXButton("Base9");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase10 = new JFXButton("Base10");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase11 = new JFXButton("Base11");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase12 = new JFXButton("Base12");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase13 = new JFXButton("Base13");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase14 = new JFXButton("Base14");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase15 = new JFXButton("Base15");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase16 = new JFXButton("Base16");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase17 = new JFXButton("Base17");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase18 = new JFXButton("Base18");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase19 = new JFXButton("Base19");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase20 = new JFXButton("Base20");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase21 = new JFXButton("Base21");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase22 = new JFXButton("Base22");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase23 = new JFXButton("Base23");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase24 = new JFXButton("Base24");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase25 = new JFXButton("Base25");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase26 = new JFXButton("Base26");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase27 = new JFXButton("Base27");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase28 = new JFXButton("Base28");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase29 = new JFXButton("Base29");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase30 = new JFXButton("Base30");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase31 = new JFXButton("Base31");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase32 = new JFXButton("Base32");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase33 = new JFXButton("Base33");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase34 = new JFXButton("Base34");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase35 = new JFXButton("Base35");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase36 = new JFXButton("Base36");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase37 = new JFXButton("Base37");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase38 = new JFXButton("Base38");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase39 = new JFXButton("Base39");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase40 = new JFXButton("Base40");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase41 = new JFXButton("Base41");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase42 = new JFXButton("Base42");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase43 = new JFXButton("Base43");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase44 = new JFXButton("Base44");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase45 = new JFXButton("Base45");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase46 = new JFXButton("Base46");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase47 = new JFXButton("Base47");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase48 = new JFXButton("Base48");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase49 = new JFXButton("Base49");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase50 = new JFXButton("Base50");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase51 = new JFXButton("Base51");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase52 = new JFXButton("Base52");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase53 = new JFXButton("Base53");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase54 = new JFXButton("Base54");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase55 = new JFXButton("Base55");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase56 = new JFXButton("Base56");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase57 = new JFXButton("Base57");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase58 = new JFXButton("Base58");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase59 = new JFXButton("Base59");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase60 = new JFXButton("Base60");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase61 = new JFXButton("Base61");
        btnBase4.setPadding(new Insets(10));
        JFXButton btnBase62 = new JFXButton("Base62");
        btnBase4.setPadding(new Insets(10));

        JFXButton[] arr = new JFXButton[]{btnBase2, btnBase3, btnBase4, btnBase5, btnBase6,
                btnBase7, btnBase8, btnBase9, btnBase10, btnBase11, btnBase12, btnBase13, btnBase14, btnBase15,
                btnBase16, btnBase17, btnBase18,
                btnBase19, btnBase20, btnBase21, btnBase22, btnBase23, btnBase24, btnBase25, btnBase26, btnBase27,
                btnBase28, btnBase29, btnBase30, btnBase31, btnBase32, btnBase33, btnBase34, btnBase35, btnBase36,
                btnBase37, btnBase38, btnBase39, btnBase40, btnBase41, btnBase42, btnBase43, btnBase44, btnBase45,
                btnBase46, btnBase47, btnBase48, btnBase49, btnBase50, btnBase51, btnBase52, btnBase53, btnBase54,
                btnBase55, btnBase56, btnBase57, btnBase58, btnBase59, btnBase60, btnBase61, btnBase62};

        JFXListView<JFXButton> lst = new JFXListView<JFXButton>();
        lst.getItems().addAll(arr);

        pppSourceBase.setContent(lst);
        pppSourceBase.setSource(lstVwSourceBase);
    }
}
