package exercise1.controller;

import exercise1.model.PlayerGamesModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerGamesController
{
    private static final String DB_PLAYER_AND_GAME_TABLE_NAME = "player_and_game";

    private static final String COLUMN_PLAYER_GAME_ID = "player_game_id";
    private static final String COLUMN_GAME_ID = "game_id";
    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_PLAYER_PLAYING_DATE = "playing_date";
    private static final String COLUMN_PLAYER_SCORE = "score";

    private final ArrayList<PlayerGamesModel> playerGamesModelArrayList;

    public PlayerGamesController()
    {
        playerGamesModelArrayList = new ArrayList<>();
    }

    public boolean insertOperation(Connection connection, int gameId, int playerId, Date playingDate, int score)
    {
        String insertQuery = "INSERT INTO " + DB_PLAYER_AND_GAME_TABLE_NAME +
                " (" +
                COLUMN_PLAYER_GAME_ID + ", " +
                COLUMN_GAME_ID + ", " +
                COLUMN_PLAYER_ID + ", " +
                COLUMN_PLAYER_PLAYING_DATE + ", " +
                COLUMN_PLAYER_SCORE +
                ") " +
                "VALUES( ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 0);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setInt(3, playerId);
            preparedStatement.setDate(4, playingDate);
            preparedStatement.setInt(5, score);

            int result = preparedStatement.executeUpdate();

            return result > 0; //When successful, greater than 0 rows affected https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html#executeUpdate()
        }
        catch (Exception exception)
        {
            log("Exception at insertOperation: " + exception.getMessage());

            return false;
        }
    }

    public boolean updateOperation(Connection connection, int playerGameId, int gameId, int playerId, Date playingDate, int score)
    {
        String insertQuery = "UPDATE " + DB_PLAYER_AND_GAME_TABLE_NAME +
                " SET " +
                COLUMN_GAME_ID + " = ?, " +
                COLUMN_PLAYER_ID + " = ?, " +
                COLUMN_PLAYER_PLAYING_DATE + " = ?, " +
                COLUMN_PLAYER_SCORE + " = ? " +
                " WHERE " +
                COLUMN_PLAYER_GAME_ID + " = ?;";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, playerId);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setDate(3, playingDate);
            preparedStatement.setInt(4, score);
            preparedStatement.setInt(5, playerGameId);

            int result = preparedStatement.executeUpdate();

            return result > 0; //When successful, greater than 0 rows affected https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html#executeUpdate()
        }
        catch (Exception exception)
        {
            log("Exception at updateOperation: " + exception.getMessage());

            return false;
        }
    }

    public boolean selectOperation(Connection connection)
    {
        String selectQuery = "SELECT * FROM " + DB_PLAYER_AND_GAME_TABLE_NAME;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            playerGamesModelArrayList.clear();

            while(resultSet.next())
            {
                PlayerGamesModel playerGameModel = new PlayerGamesModel(resultSet.getInt(COLUMN_PLAYER_GAME_ID),
                        resultSet.getInt(COLUMN_GAME_ID),
                        resultSet.getInt(COLUMN_PLAYER_ID),
                        resultSet.getDate(COLUMN_PLAYER_PLAYING_DATE),
                        resultSet.getInt(COLUMN_PLAYER_SCORE));

                playerGamesModelArrayList.add(playerGameModel);
            }

            for (PlayerGamesModel playerGameModel : playerGamesModelArrayList)
            {
                System.out.println(playerGameModel.toString());
            }

            return true;

        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());

            playerGamesModelArrayList.clear();
            return false;
        }
    }

    public boolean selectOperation(Connection connection, int playerId)
    {
        String selectQuery = "SELECT * FROM " + DB_PLAYER_AND_GAME_TABLE_NAME + " WHERE " + COLUMN_PLAYER_ID + " = " + playerId;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            playerGamesModelArrayList.clear();

            while(resultSet.next())
            {
                PlayerGamesModel playerGameModel = new PlayerGamesModel(resultSet.getInt(COLUMN_PLAYER_GAME_ID),
                        resultSet.getInt(COLUMN_GAME_ID),
                        resultSet.getInt(COLUMN_PLAYER_ID),
                        resultSet.getDate(COLUMN_PLAYER_PLAYING_DATE),
                        resultSet.getInt(COLUMN_PLAYER_SCORE));

                playerGamesModelArrayList.add(playerGameModel);
            }

            for (PlayerGamesModel playerModel : playerGamesModelArrayList)
            {
                System.out.println(playerModel.toString());
            }

            return true;

        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());

            playerGamesModelArrayList.clear();
            return false;
        }
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.controller.PlayerGamesController>> " + message);
    }
}
