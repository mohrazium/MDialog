package com.mohraz.dev.jfx.mdialog;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA at Tuesday in 2019/12/10 - 9:19 PM
 * Copyright (c) 2019 MoHRaZ
 * Project Name        : rothis.desktop
 *
 * Author : Mohammad Hadi Rafiei Zadeh
 * Email : mhrz.dev@gmail.com
 * Class Name          : MDialog
 **/

public class MDialog {
    private Stage stage;
    private Stage ownerStage;private String headerTitle;
    private String messageTitle;
    private String messageText;
    private MDialogButtonType MDialogButtonType;
    private MDialogMessageType MDialogMessageType;

    public MDialog(Stage ownerStage, String headerTitle, String messageTitle, String messageText, MDialogButtonType MDialogButtonType, MDialogMessageType MDialogMessageType) {
        this.stage = new Stage();
        this.ownerStage = ownerStage;
        this.headerTitle = headerTitle;
        this.messageTitle = messageTitle;
        this.messageText = messageText;
        this.MDialogButtonType = MDialogButtonType;
        this.MDialogMessageType = MDialogMessageType;
    }

    public MDialog() {
        this.stage = new Stage();
    }

    public MDialogResult show() {
        MDialogResult result = null;
        try {
            if (!stage.isShowing())
                if (stage.getOwner() == null) {
                    FXMLLoader dialogLoader = new FXMLLoader();
                    dialogLoader.setLocation(MDialog.class.getResource("/views/message_view.fxml"));
                    AnchorPane root = dialogLoader.load();
                    MDialogMessageViewController dialogViewController = dialogLoader.getController();
                    dialogViewController.lbl_header_title.setText(headerTitle);
                    dialogViewController.lbl_msg_title.setText(messageTitle);
                    dialogViewController.lbl_msg_text.setText(messageText);
                    dialogViewController.setMDialogButtonType(MDialogButtonType);
                    dialogViewController.setMDialogMessageType(MDialogMessageType);
                    dialogViewController.initDialog();
                    stage.setAlwaysOnTop(true);
                    stage.initOwner(ownerStage);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(getShadowScene(root));
                    stage.showAndWait();
                    result = !stage.isShowing() ? dialogViewController.getMDialogResult() : null;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @SuppressWarnings("Duplicates")
    private Scene getShadowScene(Parent p) {
        Scene scene;
        if (p instanceof AnchorPane) {
            AnchorPane outer = new AnchorPane();
            outer.getChildren().add(p);
            outer.setPadding(new Insets(10.0d));
            outer.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), new CornerRadii(0), new
                    Insets(0))));
            DropShadow dropShadow = new DropShadow(20, Color.color(0.4, 0.5, 0.5));
            p.setEffect(dropShadow);
            ((AnchorPane) p).setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0)
            )));
            scene = new Scene(outer);
            scene.setFill(Color.rgb(0, 255, 0, 0));
            return scene;
        } else if (p instanceof StackPane) {
            StackPane outer = new StackPane();
            outer.getChildren().add(p);
            outer.setPadding(new Insets(10.0d));
            outer.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), new CornerRadii(0), new
                    Insets(0))));
            DropShadow dropShadow = new DropShadow(20, Color.color(0.4, 0.5, 0.5));
            p.setEffect(dropShadow);
            ((StackPane) p).setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0)
            )));
            scene = new Scene(outer);
            scene.setFill(Color.rgb(0, 255, 0, 0));
            return scene;
        }
        return null;
    }

    public Stage getOwnerStage() {
        return ownerStage;
    }

    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MDialogButtonType getMDialogButtonType() {
        return MDialogButtonType;
    }

    public void setMDialogButtonType(MDialogButtonType MDialogButtonType) {
        this.MDialogButtonType = MDialogButtonType;
    }

    public MDialogMessageType getMDialogMessageType() {
        return MDialogMessageType;
    }

    public void setMDialogMessageType(MDialogMessageType MDialogMessageType) {
        this.MDialogMessageType = MDialogMessageType;
    }
}
