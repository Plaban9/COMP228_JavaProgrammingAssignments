package exercise1;

import exercise1.controller.Constants;
import exercise1.view.GameView;
import exercise1.view.PlayerGamesView;
import exercise1.view.PlayerView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;

public class Exercise1Manager
{
    private static final String DB_DATABASE_NAME = "java_lab_assignment";

    private Connection databaseConnection;
    private static final String TEST_SELECT_QUERY = "SELECT * FROM game ORDER BY game_id;";

    private final Stage primaryStage;
    private final int width;
    private final int height;


    private GameView gameView;
    private PlayerView playerView;
    private PlayerGamesView playerGamesView;


    // GUI
    ToggleGroup toggleGroup;
    RadioButton gameViewRadioButton;
    RadioButton playerViewRadioButton;
    RadioButton playerGamesViewRadioButton;


    public Exercise1Manager(Stage primaryStage)
    {
        this(primaryStage, 1600, 900);
    }

    public Exercise1Manager(Stage primaryStage, int width, int height)
    {
        this(DB_DATABASE_NAME, primaryStage, width, height);
    }

    public Exercise1Manager(String databaseName, Stage primaryStage, int width, int height)
    {
        this.primaryStage = primaryStage;
        this.width = width;
        this.height = height;

        initializeConnection(databaseName);
        showParentView();
    }

    public void initializeConnection(String databaseName)
    {
        try
        {
            log("Attempting Connection");
            databaseConnection = DriverManager.getConnection(Constants.DatabaseConstants.DB_CONNECTION_URL + databaseName,
                    Constants.DatabaseConstants.DB_CONNECTION_USERNAME,
                    Constants.DatabaseConstants.DB_CONNECTION_PASSWORD);

            log("Attempted Connection");
        }
        catch (Exception exception)
        {
            databaseConnection = null;
            log("Exception at initializing connection: " + exception.getMessage());
        }
    }

    public void showParentView()
    {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);


        gridPane.getRowConstraints().add(new RowConstraints(0.15 * height));
        gridPane.getRowConstraints().add(new RowConstraints(0.8 * height));

        gridPane.getColumnConstraints().add(new ColumnConstraints(width));

        this.gameView = new GameView(width, height / 2, gridPane, this);
        this.playerView = new PlayerView(width, height / 2, gridPane, this);
        this.playerGamesView = new PlayerGamesView(width, height / 2, gridPane, this);

        handleChildViews(gridPane);

        Scene scene = new Scene(gridPane, width, height); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Player Game Information"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show(); // Display the stage
    }


    private void handleChildViews(GridPane gridPane)
    {
        toggleGroup = new ToggleGroup();

        Label label = new Label("Select Operation Entity");

        gameViewRadioButton = new RadioButton("Game");
        playerViewRadioButton = new RadioButton("Player");
        playerGamesViewRadioButton = new RadioButton("Player Games");

        gameViewRadioButton.setToggleGroup(toggleGroup);
        playerViewRadioButton.setToggleGroup(toggleGroup);
        playerGamesViewRadioButton.setToggleGroup(toggleGroup);

        HBox radioButtonHBox = new HBox(20, gameViewRadioButton, playerViewRadioButton, playerGamesViewRadioButton);
        radioButtonHBox.setAlignment(Pos.CENTER);
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle old_toggle, Toggle new_toggle)
            {
                if (toggleGroup.getSelectedToggle() != null)
                {
                    RadioButton radioButton = (RadioButton) toggleGroup.getSelectedToggle();

                    if (gameView != null && playerView != null && playerGamesView != null)
                    {
                        switch (radioButton.getText())
                        {
                            case "Game":
                                gameView.showGameView();
                                playerView.hidePlayerView();
                                playerGamesView.hidePlayerGameView();
                                break;

                            case "Player":
                                gameView.hideGameView();
                                playerView.showPlayerView();
                                playerGamesView.hidePlayerGameView();
                                break;

                            case "Player Games":
                                gameView.hideGameView();
                                playerView.hidePlayerView();
                                playerGamesView.showPlayerGameView();
                                break;
                        }
                    }
                }
            }
        });

//        toggleGroup.selectToggle(gameViewRadioButton);

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        VBox topPanelVBox = new VBox(10, label, radioButtonHBox);
        topPanelVBox.setStyle(cssLayout);
        topPanelVBox.setAlignment(Pos.CENTER);

        gridPane.add(topPanelVBox, 0, 0);

//        test(gridPane);
    }

    public void showTable(GridPane gridPane, DefaultTableModel tableModel)
    {

    }

    public void showTable(TableView tableView)
    {
        Stage popUpStage = new Stage(StageStyle.DECORATED);
        popUpStage.setWidth(550);
        popUpStage.setHeight(450);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_SUBSEQUENT_COLUMNS);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tableView);

        Scene stageScene = new Scene(vbox, 550, 450);
        popUpStage.setScene(stageScene);
        popUpStage.setTitle("Player Table");

        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.showAndWait();
    }

    private void test(GridPane gridPane)
    {
//        gameView.showGameView();
//        playerView.showPlayerView();
//        playerGamesView.showPlayerGameView();

//        GameController game = new GameController();
//
//        try
//        {
////            System.out.println("Result: " + game.selectOperation(databaseConnection));
////            System.out.println("Result: " + game.insertOperation(databaseConnection, 5, "Horizon: Zero Dawn"));
//        }
//        catch (Exception exception)
//        {
//            log("Exception at executeQuery: " + exception.getMessage());
//        }

//        PlayerController player = new PlayerController();

//        try
//        {
////            System.out.println("Result: " + player.insertOperation(databaseConnection, 1, "First", "Last", "address", "A1B2C3", "province", "number"));
////            System.out.println("Result: " + player.updateOperation(databaseConnection, 1, "First", "Last", "address", "C3B2A1", "province", "number"));
//            System.out.println("Result: " + player.selectOperation(databaseConnection));
//        }
//        catch (Exception exception)
//        {
//            log("Exception at executeQuery: " + exception.getMessage());
//        }

//        PlayerGamesController playerGamesController = new PlayerGamesController();

//        try
//        {
////            System.out.println("Result: " + playerGamesController.insertOperation(databaseConnection, 1, 1, 1, new Date(1700638499), 1));
////            System.out.println("Result: " + playerGamesController.insertOperation(databaseConnection, 2, 2, 2, new Date(1700639000), 1));
////            System.out.println("Result: " + playerGamesController.updateOperation(databaseConnection, 1, 1, 1, new Date(1700638499), 55));
////            System.out.println("Result: " + playerGamesController.selectOperation(databaseConnection, 1));
////            System.out.println("Result: " + playerGamesController.selectOperation(databaseConnection));
//        }
//        catch (Exception exception)
//        {
//            log("Exception at executeQuery: " + exception.getMessage());
//        }
    }

    public Connection getDatabaseConnection()
    {
        return databaseConnection;
    }

    public GameView getGameView()
    {
        return gameView;
    }

    public PlayerView getPlayerView()
    {
        return playerView;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.Exercise1Manager>> " + message);
    }
}
