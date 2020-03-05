package com.github.mohrazzr.dev.jfx.mdialog;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA at Tuesday in 2019/12/10 - 8:54 PM
 * Copyright (c) 2019 MoHRaZ
 * Project Name        : rothis.desktop
 *
 * Author : Mohammad Hadi Rafiei Zadeh
 * Email : mhrz.dev@gmail.com
 * Class Name          : MessageDialogViewController
 **/

public class MDialogMessageViewController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    public AnchorPane root;

    @FXML
    public BorderPane brdp_dialog_header;

    @FXML
    public ImageView imgv_icon;

    @FXML
    public Label lbl_header_title;

    @FXML
    public Label lbl_msg_title;

    @FXML
    public Text lbl_msg_text;

    @FXML
    public HBox hb_yes_no;

    @FXML
    public JFXButton btn_yes_1;

    @FXML
    public JFXButton btn_no_1;

    @FXML
    public HBox hb_ok;

    @FXML
    public JFXButton btn_ok_1;

    @FXML
    public HBox hb_ok_cancel;

    @FXML
    public JFXButton btn_ok_2;

    @FXML
    public JFXButton btn_cancel_1;

    @FXML
    public HBox hb_abort_retry_ignore;

    @FXML
    public JFXButton btn_abort_1;

    @FXML
    public JFXButton btn_retry_1;

    @FXML
    public JFXButton btn_ignore_1;

    @FXML
    public HBox hb_yes_no_cancel;

    @FXML
    public JFXButton btn_yes_2;

    @FXML
    public JFXButton btn_no_2;

    @FXML
    public JFXButton btn_cancel_2;

    @FXML
    public HBox hb_retry_cancel;

    @FXML
    public JFXButton btn_retry_2;

    @FXML
    public JFXButton btn_cancel_3;

    private MDialogResult MDialogResult;
    private MDialogMessageType MDialogMessageType;
    private MDialogButtonType MDialogButtonType;

    public MDialogMessageViewController() {
        initDialog();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                root.getScene().getWindow().setX(event.getScreenX() - xOffset);
                root.getScene().getWindow().setY(event.getScreenY() - yOffset);
            }
        });
        initDialog();
    }

    @FXML
    public void onBtnAbortClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.ABORT;
        closeDialog();
    }

    @FXML
    public void onBtnCancelClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.CANCEL;
        closeDialog();
    }

    @FXML
    public void onBtnIgnoreClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.IGNORE;
        closeDialog();
    }

    @FXML
    public void onBtnOkClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.OK;
        closeDialog();
    }

    @FXML
    public void onBtnRetryClicked(ActionEvent event) {
        this.MDialogResult =MDialogResult.RETRY;
        closeDialog();
    }

    @FXML
    public void onBtnNoClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.NO;
        closeDialog();
    }

    @FXML
    public void onBtnYesClicked(ActionEvent event) {
        this.MDialogResult = MDialogResult.YES;
        closeDialog();
    }

    private void closeDialog() {
        root.getScene().getWindow().hide();
    }

    public MDialogResult getMDialogResult() {
        return MDialogResult;
    }

    public void setMDialogMessageType(MDialogMessageType MDialogMessageType) {
        this.MDialogMessageType = MDialogMessageType;
    }

    public void setMDialogButtonType(MDialogButtonType MDialogButtonType) {
        this.MDialogButtonType = MDialogButtonType;
    }

    private void initLook() {
        //root.setEffect(new DropShadow(10, Color.LIGHTGRAY));
        if (MDialogMessageType == com.github.mohrazzr.dev.jfx.mdialog.MDialogMessageType.WARNING) {
            setStyle("#FF8800", "#FFBB33");
            imgv_icon.setImage(new Image(getClass().getResource("/assets/icons/icons8-warning-shield-96.png").toExternalForm()));
        } else if (MDialogMessageType == com.github.mohrazzr.dev.jfx.mdialog.MDialogMessageType.DANGER) {
            setStyle("#CC0000", "#FF4444");
            imgv_icon.setImage(new Image(getClass().getResource("/assets/icons/icons8-delete-shield-96.png").toExternalForm()));
        } else if (MDialogMessageType == com.github.mohrazzr.dev.jfx.mdialog.MDialogMessageType.INFORMATION) {
            setStyle("#0099CC", "#33B5E5");
            imgv_icon.setImage(new Image(getClass().getResource("/assets/icons/icons8-info-shield-96.png").toExternalForm()));
        } else if (MDialogMessageType == com.github.mohrazzr.dev.jfx.mdialog.MDialogMessageType.SUCCESS) {
            setStyle("#007E33", "#00C851");
            imgv_icon.setImage(new Image(getClass().getResource("/assets/icons/icons8-protect-96.png").toExternalForm()));
        }
    }

    private void setStyle(String textFillColor, String headerColor) {
        lbl_msg_title.setStyle("-fx-text-fill: " + textFillColor + ";");
        brdp_dialog_header.setStyle("-fx-background-color:" + headerColor + " ;");
    }

    public void initDialog() {
        initLook();
        if (MDialogButtonType == MDialogButtonType.OK) {
            hb_ok.toFront();
        } else if (MDialogButtonType == MDialogButtonType.YES_NO) {
            hb_yes_no.toFront();
        } else if (MDialogButtonType == MDialogButtonType.OK_CANCEL) {
            hb_ok_cancel.toFront();
        } else if (MDialogButtonType == MDialogButtonType.YES_NO_CANCEL) {
            hb_yes_no_cancel.toFront();
        } else if (MDialogButtonType == MDialogButtonType.ABORT_RETRY_IGNORE) {
            hb_abort_retry_ignore.toFront();
        } else if (MDialogButtonType == MDialogButtonType.RETRY_CANCEL) {
            hb_retry_cancel.toFront();
        }
    }
}
