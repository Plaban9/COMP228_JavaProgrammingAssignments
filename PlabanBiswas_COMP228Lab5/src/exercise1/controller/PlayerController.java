package exercise1.controller;

import exercise1.model.PlayerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PlayerController
{
    private static final String DB_PLAYER_TABLE_NAME = "player";

    private static final String COLUMN_PLAYER_ID = "player_id";
    private static final String COLUMN_PLAYER_FIRST_NAME = "first_name";
    private static final String COLUMN_PLAYER_LAST_NAME = "last_name";
    private static final String COLUMN_PLAYER_ADDRESS = "address";
    private static final String COLUMN_PLAYER_POSTAL_CODE = "postal_code";
    private static final String COLUMN_PLAYER_PROVINCE = "province";
    private static final String COLUMN_PLAYER_PHONE_NUMBER = "phone_number";

    private final ArrayList<PlayerModel> playerModelArrayList;

    public PlayerController()
    {
        playerModelArrayList = new ArrayList<>();
    }

    public boolean insertOperation(Connection connection, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber)
    {
        String insertQuery = "INSERT INTO " + DB_PLAYER_TABLE_NAME +
                " (" +
                COLUMN_PLAYER_ID + ", " +
                COLUMN_PLAYER_FIRST_NAME + ", " +
                COLUMN_PLAYER_LAST_NAME + ", " +
                COLUMN_PLAYER_ADDRESS + ", " +
                COLUMN_PLAYER_POSTAL_CODE + ", " +
                COLUMN_PLAYER_PROVINCE + ", " +
                COLUMN_PLAYER_PHONE_NUMBER +
                ") " +
                "VALUES( ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, postalCode);
            preparedStatement.setString(6, province);
            preparedStatement.setString(7, phoneNumber);

            int result = preparedStatement.executeUpdate();

            return result > 0; //When successful, greater than 0 rows affected https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html#executeUpdate()
        }
        catch (Exception exception)
        {
            log("Exception at insertOperation: " + exception.getMessage());

            return false;
        }
    }

    public boolean updateOperation(Connection connection, int playerId, String firstName, String lastName, String address, String postalCode, String province, String phoneNumber)
    {
        String insertQuery = "UPDATE " + DB_PLAYER_TABLE_NAME +
                " SET " +
                COLUMN_PLAYER_FIRST_NAME + " = ?, " +
                COLUMN_PLAYER_LAST_NAME + " = ?, " +
                COLUMN_PLAYER_ADDRESS + " = ?, " +
                COLUMN_PLAYER_POSTAL_CODE + " = ?, " +
                COLUMN_PLAYER_PROVINCE + " = ?, " +
                COLUMN_PLAYER_PHONE_NUMBER + " = ? " +
                " WHERE " +
                COLUMN_PLAYER_ID + " = ?;";

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, postalCode);
            preparedStatement.setString(5, province);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setInt(7, playerId);

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
        String selectQuery = "SELECT * FROM " + DB_PLAYER_TABLE_NAME;

        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);

            ResultSet resultSet = preparedStatement.executeQuery();

            playerModelArrayList.clear();

            while(resultSet.next())
            {
                PlayerModel playerModel = new PlayerModel(resultSet.getInt(COLUMN_PLAYER_ID),
                        resultSet.getString(COLUMN_PLAYER_FIRST_NAME),
                        resultSet.getString(COLUMN_PLAYER_LAST_NAME),
                        resultSet.getString(COLUMN_PLAYER_ADDRESS),
                        resultSet.getString(COLUMN_PLAYER_POSTAL_CODE),
                        resultSet.getString(COLUMN_PLAYER_PROVINCE),
                        resultSet.getString(COLUMN_PLAYER_PHONE_NUMBER));

                playerModelArrayList.add(playerModel);
            }

//            for (PlayerModel playerModel : playerModelArrayList)
//            {
//                System.out.println(playerModel.toString());
//            }

            return true;

        }
        catch (Exception exception)
        {
            log("Exception at selectOperation: " + exception.getMessage());

            playerModelArrayList.clear();
            return false;
        }
    }

    public ArrayList<PlayerModel> getPlayerModelArrayList()
    {
        return playerModelArrayList;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.controller.PlayerController>> " + message);
    }
}
