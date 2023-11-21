import java.sql.*;

public class Main
{
    private static final String DB_DATABASE_NAME = "java_lab_assignment";
    private static final String DB_PLAYER_TABLE_NAME = "player";
    private static final String DB_GAME_TABLE_NAME = "game";
    private static final String DB_PLAYER_AND_GAME_TABLE_NAME = "player_and_game";
    private static final String TEST_SELECT_QUERY = "SELECT * FROM game ORDER BY game_id;";

    public static void main(String[] args)
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
    }
}