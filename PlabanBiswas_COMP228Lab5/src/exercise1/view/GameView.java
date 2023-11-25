package exercise1.view;

import exercise1.Exercise1Manager;
import exercise1.controller.GameController;
import exercise1.model.GameModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.swing.table.DefaultTableModel;

public class GameView
{
    private final GridPane gridPane;
    private final Exercise1Manager exercise1Manager;
    private final int width;
    private final int height;
    private final GameController gameController;

    private HBox gameAreaHBox;

    public GameView(int width, int height, GridPane gridPane, Exercise1Manager exercise1Manager)
    {
        this.width = width;
        this.height = height;
        this.gridPane = gridPane;
        this.exercise1Manager = exercise1Manager;

        gameController = new GameController();
    }

    public void showGameView()
    {
        Label gameTitle = new Label("Game Title: ");
        TextField gameTitleTextField = new TextField();
        gameTitleTextField.setPromptText("Enter Game Title Here...");

        Button insertButton = new Button("Add Game");
        insertButton.setMinWidth(150);
        Button displayButton = new Button("Display Games");
        displayButton.setMinWidth(150);

        HBox titleHBox = new HBox(10, gameTitle, gameTitleTextField);
        titleHBox.setAlignment(Pos.CENTER);

        Label operationResult = new Label("Operation Result: ");
        operationResult.setAlignment(Pos.CENTER_LEFT);
        TextArea resultDisplay = new TextArea();
        resultDisplay.setMinWidth(0.45 * width);
        resultDisplay.setMaxWidth(0.45 * width);
        resultDisplay.setEditable(false);

        VBox inputAreaVBox = new VBox(10, titleHBox);
        inputAreaVBox.setAlignment(Pos.CENTER);
        inputAreaVBox.setMinWidth(0.3 * width);

        VBox buttonAreaVBox = new VBox(10, insertButton, displayButton);
        buttonAreaVBox.setAlignment(Pos.CENTER);
        buttonAreaVBox.setMinWidth(0.2 * width);

        VBox resultAreaVBox = new VBox(10, operationResult, resultDisplay);
        resultAreaVBox.setAlignment(Pos.CENTER);
        resultAreaVBox.setMinWidth(0.2 * width);

        String cssLayout = """
                -fx-border-color: grey;
                -fx-border-insets: 5;
                -fx-border-width: 1;
                -fx-border-style: solid;
                """;

        gameAreaHBox = new HBox(10, inputAreaVBox, buttonAreaVBox, resultAreaVBox);
        gameAreaHBox.setStyle(cssLayout);
        gameAreaHBox.setMinWidth(width);

        insertButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if (!gameTitleTextField.getText().isEmpty())
                {
                    if (gameController.checkTitleExists(exercise1Manager.getDatabaseConnection(), gameTitleTextField.getText()))
                    {
                        resultDisplay.setText("Error: Game already Exists - Title: " + gameTitleTextField.getText());
                        return;
                    }

                    boolean result = gameController.insertOperation(exercise1Manager.getDatabaseConnection(), gameTitleTextField.getText());

                    if (result)
                    {
                        resultDisplay.setText("Game added Successfully - Title: " + gameTitleTextField.getText());
                    }
                    else
                    {
                        resultDisplay.setText("Error in adding Title: " + gameTitleTextField.getText() + ". Try again!");
                    }
                }
                else
                {
                    resultDisplay.setText("Error: Title not supplied. Enter Title!");
                }
            }
        });

        displayButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                if (gameController.selectOperation(exercise1Manager.getDatabaseConnection()))
                {
                    exercise1Manager.showTable(getTableModel());

                    resultDisplay.setText("Games listed successfully!");
                }
                else
                {
                    resultDisplay.setText("Error while listing games!");
                }
            }
        });

        gridPane.add(gameAreaHBox, 0, 1);
    }

    private DefaultTableModel getDefaultTableModel()
    {
        String[] columnNames = {"game_id", "game_title"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (var gameModel : gameController.getGameModelArrayList())
        {
            int game_id = gameModel.getGameId();
            String game_title = gameModel.getGameTitle();
            String[] data = {game_id + "", game_title};
            tableModel.addRow(data);
        }

        return tableModel;
    }

    private TableView<GameModel> getTableModel()
    {
        ObservableList<GameModel> data = FXCollections.observableArrayList(gameController.getGameModelArrayList());

        TableView<GameModel> table = new TableView<>();
        table.setEditable(true);

        TableColumn gameId = new TableColumn("game_id");
        gameId.setCellValueFactory(new PropertyValueFactory<>("gameId"));

        TableColumn gameTitle = new TableColumn("game_title");
        gameTitle.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));


        table.getColumns().addAll(gameId, gameTitle);
        table.setItems(data);

        return table;
    }

    public void hideGameView()
    {
        if (gameAreaHBox != null)
        {
            gameAreaHBox.setVisible(false);
        }
    }

    public GameController getGameController()
    {
        return gameController;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.view.GameView>> " + message);
    }
}
