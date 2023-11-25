package exercise1.model;

public class GameModel
{
    private int gameId;
    private String gameTitle;

    public GameModel(int gameId, String gameTitle)
    {
        setGameId(gameId);
        setGameTitle(gameTitle);
    }

    //region SETTERS
    public void setGameId(int gameId)
    {
        this.gameId = gameId;
    }

    public void setGameTitle(String gameTitle)
    {
        this.gameTitle = gameTitle;
    }
    //endregion

    //region GETTERS
    public int getGameId()
    {
        return gameId;
    }

    public String getGameTitle()
    {
        return gameTitle;
    }
    //endregion


    @Override
    public String toString()
    {
        return "Game ID: " + gameId + ", Game Title: " + gameTitle;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.model.GameModel>> " + message);
    }
}
