package cz.mateusz.dstructures.scoreboard;

public class GameEntry {

    private static float MAX_SCORE = 999.99f;

    private String name;

    private float score;

    public GameEntry(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public static GameEntry of(String name, float score) {
        if(MAX_SCORE < score)
            throw new IllegalArgumentException("Given score is above the limit");
        return new GameEntry(name,score);
    }

    public String getName() {
        return name;
    }

    public float getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "GameEntry{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
