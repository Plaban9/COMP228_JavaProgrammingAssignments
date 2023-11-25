package exercise1.view;

import exercise1.Exercise1Manager;
import exercise1.controller.PlayerController;
import exercise1.model.PlayerModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class PlayerView
{
    private final GridPane gridPane;
    private final Exercise1Manager exercise1Manager;
    private final int width;
    private final int height;
    private int playerId;
    private HBox playerAreaBox;

    private final PlayerController playerController;

    public PlayerView(int width, int height, GridPane gridPane, Exercise1Manager exercise1Manager)
    {
        this.playerId = -1;
        this.width = width;
        this.height = height;
        this.gridPane = gridPane;
        this.exercise1Manager = exercise1Manager;

        playerController = new PlayerController();
    }

    public void showPlayerView()
    {
        //TODO: Set label and text field Size
        //TODO: Make Button equal length
        Label playerFirstNameLabel = new Label("First Name: ");
        TextField playerFirstNameTextField = new TextField();
        playerFirstNameTextField.setPromptText("Enter Player's first name...");

        Label playerLastNameLabel = new Label("Last Name: ");
        TextField playerLastNameTextField = new TextField();
        playerLastNameTextField.setPromptText("Enter Player's last name... ");

        Label playerAddressLabel = new Label("Address: ");
        TextField playerAddressTextField = new TextField();
        playerAddressTextField.setPromptText("Enter Player's address ...");

        Label playerPostalCodeLabel = new Label("Postal Code: ");
        TextField playerPostalCodeTextField = new TextField();
        playerPostalCodeTextField.setPromptText("Enter Player's Postal Code...");

        Label provinceLabel = new Label("Province...");
        TextField provinceTextField = new TextField();
        provinceTextField.setPromptText("Enter Player's province...");

        Label phoneNumberLabel = new Label("Phone Number: ");
        TextField phoneNumberTextField = new TextField();
        phoneNumberTextField.setPromptText("Enter Player's phone number...");

        Button insertButton = new Button("Add Player");
        Button updateButton = new Button("Update Player");
        Button displayButton = new Button("Display Players");
        Button performUpdateButton = new Button("Perform Update");
        performUpdateButton.setVisible(false);

        Label operationResult = new Label("Operation Result: ");
        operationResult.setAlignment(Pos.BASELINE_LEFT);
        TextArea resultDisplay = new TextArea();
        resultDisplay.setEditable(false);

        HBox firstNameHBox = new HBox(10, playerFirstNameLabel, playerFirstNameTextField);
        HBox lastNameHBox = new HBox(10, playerLastNameLabel, playerLastNameTextField);
        HBox playerAddressHBox = new HBox(10, playerAddressLabel, playerAddressTextField);
        HBox postalCodeHBox = new HBox(10, playerPostalCodeLabel, playerPostalCodeTextField);
        HBox provinceHBox = new HBox(10, provinceLabel, provinceTextField);
        HBox phoneNumberHBox = new HBox(10, phoneNumberLabel, phoneNumberTextField);

        VBox playerInputVBox = new VBox(10,
                firstNameHBox,
                lastNameHBox,
                playerAddressHBox,
                postalCodeHBox,
                provinceHBox,
                phoneNumberHBox);

        VBox buttonBoxes = new VBox(10,
                insertButton,
                updateButton,
                displayButton,
                performUpdateButton);
        buttonBoxes.setAlignment(Pos.CENTER);

        VBox resultAreaVBox = new VBox(10, operationResult, resultDisplay);
        resultAreaVBox.setAlignment(Pos.CENTER);
        resultAreaVBox.setMinWidth(0.3 * width);

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        playerAreaBox = new HBox(10, playerInputVBox, buttonBoxes, resultAreaVBox);
        playerAreaBox.setStyle(cssLayout);
        playerAreaBox.setMinWidth(width);

        gridPane.add(playerAreaBox, 0, 1);

        insertButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                boolean result = playerController.insertOperation(exercise1Manager.getDatabaseConnection(),
                        playerFirstNameTextField.getText(),
                        playerLastNameTextField.getText(),
                        playerAddressTextField.getText(),
                        playerPostalCodeTextField.getText(),
                        provinceTextField.getText(),
                        phoneNumberTextField.getText());

                if (result)
                {
                    resultDisplay.setText("Player added Successfully!");
                }
                else
                {
                    resultDisplay.setText("Error in adding Player. Try again!");
                }

                playerId = -1;
                performUpdateButton.setVisible(false);
            }
        });

        updateButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                playerId = showUpdateStage();

                if (playerId == -1)
                {
                    resultDisplay.setText("No player selected!");
                    return;
                }

                performUpdateButton.setVisible(true);
                PlayerModel playerModel = playerController.getPlayerModelArrayList().get(playerId);
                System.out.println("PlayerModel: " + playerModel.toString());

                playerFirstNameTextField.setText(playerModel.getFirstName());
                playerLastNameTextField.setText(playerModel.getLastName());
                playerAddressTextField.setText(playerModel.getAddress());
                playerPostalCodeTextField.setText(playerModel.getPostalCode());
                provinceTextField.setText(playerModel.getProvince());
                phoneNumberTextField.setText(playerModel.getPhoneNumber());

                resultDisplay.setText("Selected Player to be updated.");
            }
        });

        displayButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (playerController.selectOperation(exercise1Manager.getDatabaseConnection()))
                {
                    resultDisplay.setText("Players listed successfully!");
                }
                else
                {
                    resultDisplay.setText("Error while listing players!");
                }

                playerId = -1;
                performUpdateButton.setVisible(false);
            }
        });

        performUpdateButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

                boolean result = playerController.updateOperation(exercise1Manager.getDatabaseConnection(),
                        playerId + 1,
                        playerFirstNameTextField.getText(),
                        playerLastNameTextField.getText(),
                        playerAddressTextField.getText(),
                        playerPostalCodeTextField.getText(),
                        provinceTextField.getText(),
                        phoneNumberTextField.getText());

                performUpdateButton.setVisible(false);

                if (result)
                {
                    resultDisplay.setText("Player updated Successfully!");
                }
                else
                {
                    resultDisplay.setText("Error in updating Player. Try again!");
                }
            }
        });
    }

    private int showUpdateStage()
    {
        final int[] indexToReturn = {-1};

        Stage popUpStage = new Stage(StageStyle.DECORATED);
        ComboBox<String> playerList = new ComboBox<>(FXCollections.observableArrayList(getPlayerList()));
        playerList.setMinWidth(250);

        Button selectButton = new Button("Select Player");
        selectButton.setMinWidth(100);

        VBox comp = new VBox(10, playerList, selectButton);
        comp.setAlignment(Pos.CENTER);

        Scene stageScene = new Scene(comp, 300, 150);
        popUpStage.setScene(stageScene);
        popUpStage.setTitle("Select Player to Update...");

        selectButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                indexToReturn[0] = playerList.getSelectionModel().getSelectedIndex();
                popUpStage.close();
            }
        });

        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.showAndWait();

        return indexToReturn[0];
    }

    private ArrayList<String> getPlayerList()
    {
        ArrayList<String> playerList = new ArrayList<>();

        playerController.selectOperation(exercise1Manager.getDatabaseConnection());

        for (var playerModel : playerController.getPlayerModelArrayList())
        {
            playerList.add(playerModel.getFirstName() + " " + playerModel.getLastName());
        }

        return playerList;
    }

    public void hidePlayerView()
    {
        if (playerAreaBox != null)
        {
            playerAreaBox.setVisible(false);
        }
    }

    public PlayerController getPlayerController()
    {
        return playerController;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.view.PlayerView>> " + message);
    }
}
