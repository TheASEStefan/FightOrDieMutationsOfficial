package net.teamabyssal.handlers;

public class ScoreHandler {

    private static int score = 0;

    public static int getScore() {
        return ScoreHandler.score;
    }
    public static void setScore(int score) {
        ScoreHandler.score = score;
    }

    // 0 score = phase 0
    // 1000 score = phase 1
    // 5000 score = phase 2
    // 10000 score = phase 3
    // 25000 score = phase 4
    // 50000 score = phase 5
}
