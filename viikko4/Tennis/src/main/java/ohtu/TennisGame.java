package ohtu;


import java.util.HashMap;

import static java.lang.Math.abs;

public class TennisGame {
    private String player1Name;
    private String player2Name;

    private HashMap<Integer, String> pointsAsStrings;
    private HashMap<String, Integer> points;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        this.points = new HashMap<String, Integer>();
        this.points.put(player1Name, 0);
        this.points.put(player2Name, 0);

        this.pointsAsStrings = new HashMap<Integer, String>();
        this.pointsAsStrings.put(0, "Love");
        this.pointsAsStrings.put(1, "Fifteen");
        this.pointsAsStrings.put(2, "Thirty");
        this.pointsAsStrings.put(3, "Forty");
    }

    public void wonPoint(String playerName) {
        if (this.points.containsKey(playerName)) {
            int currentPoints = this.points.get(playerName);
            this.points.put(playerName, currentPoints+1);
        }
    }

    public int getPointsForPlayer(String playerName) {
        if (this.points.containsKey(playerName)) {
            return this.points.get(playerName);
        } else {
            return -1;
        }
    }
    public String getPointsAsString(int points) {
        if (this.pointsAsStrings.containsKey(points)) {
            return this.pointsAsStrings.get(points);
        }
        return "Value of points out of bounds!";
    }
    public String getPointsAsString(String playerName) {
        if(this.points.containsKey(playerName)) {
            int currentScore = points.get(playerName);
            return this.pointsAsStrings.get(currentScore);
        }
        return "Value of points out of bounds!";
    }
    public int getMaxScore() {
        if (this.getPointsForPlayer(this.player1Name) > this.getPointsForPlayer(this.player2Name)) {
            return getPointsForPlayer(this.player1Name);
        }
        return this.getPointsForPlayer(this.player2Name);
    }
    public String getPlayerWithMaxScore() {
        if (getPointsForPlayer(player1Name) == getMaxScore()) {
            return player1Name;
        }
        return player2Name;
    }
    public int getScoreDifference() {
        return abs(getPointsForPlayer(this.player1Name) - getPointsForPlayer(this.player2Name));
    }

    public boolean scoreIsEven() {
        if (getPointsForPlayer(player1Name) == getPointsForPlayer(player2Name)) {
            return true;
        }
        return false;
    }
    public String getTieScore() {
        if (scoreIsEven() && getMaxScore() < 4) {
            return getPointsAsString(player1Name) + "-All";
        }
        return "Deuce";
    }

    public boolean hasPlayerWon(String playerName) {
        if (getScoreDifference() > 1  && playerName.equals(getPlayerWithMaxScore()) && getMaxScore() > 3) {
            return true;
        }
        return false;
    }
    public boolean hasAdvantage(String playerName) {
        if (getMaxScore() > 3 && getScoreDifference() == 1 && playerName.equals(getPlayerWithMaxScore())) {
             return true;
        }
        return false;
    }


    public String getScore() {
        String score = "";

        if (hasPlayerWon(player1Name)) {
            score = "Win for " + player1Name;
        } else if (hasPlayerWon(player2Name)) {
            score = "Win for " + player2Name;
        } else if (hasAdvantage(player1Name)) {
            score = "Advantage " + player1Name;
        } else if (hasAdvantage(player2Name)) {
            score = "Advantage " + player2Name;
        } else if (!scoreIsEven()) {
            score = this.getPointsAsString(this.player1Name) + "-" + this.getPointsAsString(this.player2Name);
        } else {
            score = getTieScore();
        }
        return score;
    }
}