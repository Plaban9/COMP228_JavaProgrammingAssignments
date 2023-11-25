package exercise1.model;

import java.sql.Date;

public class PlayerGamesModel
{
    private int playerGameId;
    private int gameId;
    private int playerId;
    private Date playingDate;
    private int score;

    public PlayerGamesModel(int playerGameId, int gameId, int playerId, Date playingDate, int score)
    {
        setPlayerGameId(playerGameId);
        setGameId(gameId);
        setPlayerId(playerId);
        setPlayingDate(playingDate);
        setScore(score);
    }

    //region GETTERS

    public int getPlayerGameId()
    {
        return playerGameId;
    }

    public int getGameId()
    {
        return gameId;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public Date getPlayingDate()
    {
        return playingDate;
    }

    public int getScore()
    {
        return score;
    }
    //endregion

    //region SETTERS

    public void setPlayerGameId(int playerGameId)
    {
        this.playerGameId = playerGameId;
    }

    public void setGameId(int gameId)
    {
        this.gameId = gameId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public void setPlayingDate(Date playingDate)
    {
        this.playingDate = playingDate;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    //endregion

    @Override
    public String toString()
    {

        return "Player Game ID: " +
                playerGameId +
                ", Game ID: " +
                gameId +
                ", Player ID: " +
                playerId +
                ",  Date: " +
                playingDate.toString() +
                ", Score: " +
                score;
    }

    private static void log(String message)
    {
        System.out.println("<<exercise1.model.PlayerGamesModel>> " + message);
    }
}
