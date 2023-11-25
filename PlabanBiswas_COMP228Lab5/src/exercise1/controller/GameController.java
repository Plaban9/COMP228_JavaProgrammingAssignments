package exercise1.controller;

import exercise1.model.GameModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GameController
{
    private static final String DB_GAME_TABLE_NAME = "game";
    private static final String COLUMN_GAME_ID = "game_id";
    private static final String COLUMN_GAME_TITLE = "game_title";

    private final ArrayList<GameModel> gameModelArrayList;

    public GameController()
    {
        gameModelArrayList = new ArrayList<>();
    }

    public boolean insertOperation(Connection connection, String gameTitle)
    {
        String insertQuery = "INSERT INTO " + DB_GAME_TABLE_NAME + " ( " + COLUMN_GAME_ID + ", " + COLUMN_GAME_TITLE + ") VALUES( ?, ?)";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, gameTitle);

            int result = preparedStatement.executeUpdate();

            return result > 0; //When successful, greater than 0 rows affected https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html#executeUpdate()
        }
        catch (Exception exception)
        {
            log("Exception at insertOperation: " + exception.getMessage());
        }

        return false;
    }

    public boolean checkTitleExists(Connection connection, String title)
    {
        String checkQuery = "SELECT * FROM " + DB_GAME_TABLE_NAME + " WHERE " + COLUMN_GAME_TITLE + " = ?;";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, title);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());
        }

        return false;
    }

    public boolean selectOperation(Connection connection)
    {
        String selectQuery = "SELECT * FROM " + DB_GAME_TABLE_NAME;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            gameModelArrayList.clear();

            while(resultSet.next())
            {
                GameModel gameModel = new GameModel(resultSet.getInt(COLUMN_GAME_ID), resultSet.getString(COLUMN_GAME_TITLE));

                gameModelArrayList.add(gameModel);
            }

            return true;
        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());
        }

        gameModelArrayList.clear();
        return false;
    }

    public ArrayList<GameModel> getGameModelArrayList()
    {
        return gameModelArrayList;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.controller.GameController>> " + message);
    }
}
