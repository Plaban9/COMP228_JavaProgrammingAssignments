import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application
{
    private static final String DB_DATABASE_NAME = "java_lab_assignment";
    private static final String DB_PLAYER_TABLE_NAME = "player";
    private static final String DB_GAME_TABLE_NAME = "game";
    private static final String DB_PLAYER_AND_GAME_TABLE_NAME = "player_and_game";
    private static final String TEST_SELECT_QUERY = "SELECT * FROM game ORDER BY game_id;";

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        try
                (
                        Connection connection = DriverManager.getConnection(Constants.DatabaseConstants.DB_CONNECTION_URL + DB_DATABASE_NAME, Constants.DatabaseConstants.DB_CONNECTION_USERNAME, Constants.DatabaseConstants.DB_CONNECTION_PASSWORD);
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(TEST_SELECT_QUERY);
                )
        {
            while(resultSet.next())
            {
                System.out.println("------ Record ------");
                System.out.println("ID: " + resultSet.getInt("game_id"));
                System.out.println("Game Name: " + resultSet.getString("game_title"));
                System.out.println("--------------------");
            }
        }
        catch (SQLException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_LEFT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        Scene scene = new Scene(gridPane, 1600, 900); // Set/Replace the stage title Pane in 1st parameter
        primaryStage.setTitle("Student Information Panel"); // Place the scene in the stage
        primaryStage.setScene(scene); // Display the stage
        primaryStage.show();
    }
}