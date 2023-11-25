package exercise1.view;

import exercise1.Exercise1Manager;
import exercise1.controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
        //TODO: Make Button equal length
        //TODO: Set label and text field Size

        Label gameTitle = new Label("Game Title: ");
        TextField gameTitleTextField = new TextField();
        gameTitleTextField.setPromptText("Enter Game Title Here...");

        Button insertButton = new Button("Add Game");
        Button displayButton = new Button("Display Games");

        HBox titleHBox = new HBox(10, gameTitle, gameTitleTextField);

        Label operationResult = new Label("Operation Result: ");
        operationResult.setAlignment(Pos.BASELINE_LEFT);
        TextArea resultDisplay = new TextArea();
        resultDisplay.setEditable(false);

        VBox inputAreaVBox = new VBox(10, titleHBox);
        inputAreaVBox.setAlignment(Pos.CENTER);
        inputAreaVBox.setMinWidth(0.3 * width);

        VBox buttonAreaVBox = new VBox(10, insertButton, displayButton);
        buttonAreaVBox.setAlignment(Pos.CENTER);
        buttonAreaVBox.setMinWidth(0.3 * width);

        VBox resultAreaVBox = new VBox(10, operationResult, resultDisplay);
        resultAreaVBox.setAlignment(Pos.CENTER);
        resultAreaVBox.setMinWidth(0.3 * width);

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
                //TODO: List Logic
                if (gameController.selectOperation(exercise1Manager.getDatabaseConnection()))
                {
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
