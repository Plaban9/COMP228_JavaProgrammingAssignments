package exercise1.view;

import exercise1.Exercise1Manager;
import exercise1.controller.PlayerGamesController;
import exercise1.model.PlayerGamesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;

public class PlayerGamesView
{
    private final GridPane gridPane;
    private final Exercise1Manager exercise1Manager;
    private final int width;
    private final int height;
    private HBox playerAreaHBox;

    private final PlayerGamesController playerGamesController;


    public PlayerGamesView(int width, int height, GridPane gridPane, Exercise1Manager exercise1Manager)
    {
        this.width = width;
        this.height = height;
        this.gridPane = gridPane;
        this.exercise1Manager = exercise1Manager;

        playerGamesController = new PlayerGamesController();
    }

    public void showPlayerGameView()
    {
        ArrayList<String> gameList = getGamesList();
        ArrayList<String> playerList = getPlayersList();

        int min_width_label = 100;
        int input_width = 150;
        int button_width = 200;

        Label gameLabel = new Label("Choose Game: ");
        gameLabel.setMinWidth(min_width_label);
        ComboBox<String> gameComboBox = new ComboBox<>(FXCollections.observableArrayList(gameList));
        gameComboBox.setMinWidth(input_width);

        Label playerLabel = new Label("Choose Player: ");
        playerLabel.setMinWidth(min_width_label);
        ComboBox<String> playerComboBox = new ComboBox<>(FXCollections.observableArrayList(playerList));
        playerComboBox.setMinWidth(input_width);

        Label playerDateLabel = new Label("Enter Date: ");
        playerDateLabel.setMinWidth(min_width_label);
        DatePicker playerDatePicker = new DatePicker();
        playerDatePicker.setPromptText("Enter play date...");
        playerDatePicker.setMinWidth(input_width);

        Label playerScoreLabel = new Label("Score: ");
        playerScoreLabel.setMinWidth(min_width_label);
        TextField playerScoreTextField = new TextField();
        playerScoreTextField.setPromptText("Enter player score...");
        playerScoreTextField.setMinWidth(input_width);

        Button insertButton = new Button("Add");
        insertButton.setMinWidth(button_width);
        Button displayButton = new Button("Display");
        displayButton.setMinWidth(button_width);
        Button displayByIdButton = new Button("Display by Player");
        displayByIdButton.setMinWidth(button_width);

        Label operationResult = new Label("Operation Result: ");
        operationResult.setAlignment(Pos.CENTER_LEFT);
        TextArea resultDisplay = new TextArea();
        resultDisplay.setMinWidth(0.45 * width);
        resultDisplay.setMaxWidth(0.45 * width);
        resultDisplay.setEditable(false);

        HBox gameHBox = new HBox(10, gameLabel, gameComboBox);
        HBox playerHBox = new HBox(10, playerLabel, playerComboBox);
        HBox dateHBox = new HBox(10, playerDateLabel, playerDatePicker);
        HBox scoreHBox = new HBox(10, playerScoreLabel, playerScoreTextField);

        VBox inputArea = new VBox(10,
                gameHBox,
                playerHBox,
                dateHBox,
                scoreHBox);
        inputArea.setAlignment(Pos.CENTER);
        inputArea.setPadding(new Insets(10));

        VBox buttonBoxes = new VBox(10,
                insertButton,
                displayButton,
                displayByIdButton);
        buttonBoxes.setAlignment(Pos.CENTER);

        VBox resultAreaVBox = new VBox(10, operationResult, resultDisplay);
        resultAreaVBox.setAlignment(Pos.CENTER);
        resultAreaVBox.setMinWidth(0.3 * width);

        insertButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                boolean atleastOneAlpha = playerScoreTextField.getText().matches(".*[a-zA-Z]+.*");

                if (playerScoreTextField.getText().isEmpty())
                {
                    resultDisplay.setText("Score is Empty!");
                    return;
                }

                if (atleastOneAlpha)
                {
                    resultDisplay.setText("Score can't have alphabetical values.");
                    return;

                }

                int gameIndex = gameComboBox.getSelectionModel().getSelectedIndex();
                int playerIndex = playerComboBox.getSelectionModel().getSelectedIndex();

                if (gameIndex == -1)
                {
                    resultDisplay.setText("Game not selected.");
                    return;
                }

                if (playerIndex == -1)
                {
                    resultDisplay.setText("Player not selected.");
                    return;
                }

                java.sql.Date sqlDate;

                try
                {
                    playerDatePicker.getConverter().fromString(playerDatePicker.getEditor().getText());
                    java.util.Date date = java.util.Date.from(playerDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                    sqlDate = new java.sql.Date(date.getTime());
                }
                catch (Exception exception)
                {
                    resultDisplay.setText("Input correct date");
                    return;
                }

                boolean result = playerGamesController.insertOperation(exercise1Manager.getDatabaseConnection(), gameIndex + 1, playerIndex + 1, sqlDate, Integer.parseInt(playerScoreTextField.getText()));

                if (result)
                {
                    resultDisplay.setText("Player Game Data insertion successful.");
                }
                else
                {
                    resultDisplay.setText("Error in inserting player game data.");
                }
            }
        });

        displayButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (playerGamesController.selectOperation(exercise1Manager.getDatabaseConnection()))
                {
                    exercise1Manager.showTable(getTableModel());
                    resultDisplay.setText("Showing player game Details.");
                }
                else
                {
                    resultDisplay.setText("Error while Showing player Details.");
                }
            }
        });

        displayByIdButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                int playerId = selectPlayer();

                if (playerId == -1)
                {
                    resultDisplay.setText("No player selected!");
                    return;
                }

                if (playerGamesController.selectOperation(exercise1Manager.getDatabaseConnection(), playerId + 1))
                {
                    exercise1Manager.showTable(getTableModel());
                    resultDisplay.setText("Showing player game Details.");
                }
                else
                {
                    resultDisplay.setText("Error while Showing player Details.");
                }

            }
        });

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        playerAreaHBox = new HBox(10, inputArea, buttonBoxes, resultAreaVBox);
        playerAreaHBox.setStyle(cssLayout);
        playerAreaHBox.setMinWidth(width);

        gridPane.add(playerAreaHBox, 0, 1);
    }


    public void hidePlayerGameView()
    {
        if (playerAreaHBox != null)
        {
            playerAreaHBox.setVisible(false);
        }
    }

    private DefaultTableModel getDefaultTableModel()
    {
        String[] columnNames = {"player_game_id", "game_id", "player_id", "playing_date", "score"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (var playerGameModel : playerGamesController.getPlayerGamesModelArrayList())
        {
            int playerGameId = playerGameModel.getPlayerGameId();
            int gameId = playerGameModel.getGameId();
            int playerId = playerGameModel.getPlayerId();
            Date date = playerGameModel.getPlayingDate();
            int score = playerGameModel.getScore();

            // create a single array of one row's worth of data
            String[] data = {playerGameId + "", gameId + "", playerId + "", date.toString(), score + ""};

            // and add this row of data into the table model
            tableModel.addRow(data);
        }

        return tableModel;
    }

    private TableView<PlayerGamesModel> getTableModel()
    {
        ObservableList<PlayerGamesModel> data = FXCollections.observableArrayList(playerGamesController.getPlayerGamesModelArrayList());

        TableView<PlayerGamesModel> table = new TableView<>();
        table.setEditable(true);

        TableColumn playerGamesId = new TableColumn("player_game_id");
        playerGamesId.setCellValueFactory(new PropertyValueFactory<>("playerGameId"));

        TableColumn gameId = new TableColumn("game_id");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        TableColumn playerId = new TableColumn("player_id");
        playerId.setCellValueFactory(new PropertyValueFactory<>("playerId"));

        TableColumn playingDate = new TableColumn("playing_date");
        playingDate.setCellValueFactory(new PropertyValueFactory<>("playingDate"));

        TableColumn score = new TableColumn("score");
        score.setCellValueFactory(new PropertyValueFactory<>("score"));

        table.getColumns().addAll(playerGamesId, gameId, playerId, playingDate, score);
        table.setItems(data);

        return table;
    }

    private int selectPlayer()
    {
        final int[] indexToReturn = {-1};

        Stage popUpStage = new Stage(StageStyle.DECORATED);
        ComboBox<String> playerList = new ComboBox<>(FXCollections.observableArrayList(getPlayersList()));
        playerList.setMinWidth(250);

        Button selectButton = new Button("Select Player");
        selectButton.setMinWidth(100);

        VBox comp = new VBox(10, playerList, selectButton);
        comp.setAlignment(Pos.CENTER);

        Scene stageScene = new Scene(comp, 300, 150);
        popUpStage.setScene(stageScene);
        popUpStage.setTitle("Select Player to Display...");

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

    private ArrayList<String> getGamesList()
    {
        ArrayList<String> gamesList = new ArrayList<>();
        exercise1Manager.getGameView().getGameController().selectOperation(exercise1Manager.getDatabaseConnection());

        for (var gamesModel : exercise1Manager.getGameView().getGameController().getGameModelArrayList())
        {
            gamesList.add(gamesModel.getGameTitle());
        }

        return gamesList;
    }

    private ArrayList<String> getPlayersList()
    {
        ArrayList<String> playerList = new ArrayList<>();
        exercise1Manager.getPlayerView().getPlayerController().selectOperation(exercise1Manager.getDatabaseConnection());

        for (var playerModel : exercise1Manager.getPlayerView().getPlayerController().getPlayerModelArrayList())
        {
            playerList.add(playerModel.getFirstName() + " " + playerModel.getLastName());
        }

        return playerList;
    }


    private static void log(String message)
    {
        System.out.println("<<exercise1.view.PlayerGamesView>> " + message);
    }
}
