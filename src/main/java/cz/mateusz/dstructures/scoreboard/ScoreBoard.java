package cz.mateusz.dstructures.scoreboard;

import java.util.StringJoiner;

public class ScoreBoard {

    private int entriesCount = 0;

    private GameEntry[] board;

    public ScoreBoard(int capacity) {
        this.board = new GameEntry[capacity];
    }

    public void put(GameEntry entry) {
        float newScore = entry.getScore();

        if(entriesCount < board.length || newScore > board[entriesCount - 1].getScore()) {
            if(entriesCount < board.length)
                entriesCount++;

            int eIndex = entriesCount - 1;
            while(eIndex > 0 && board[eIndex - 1].getScore() < newScore) {
                board[eIndex] = board[eIndex - 1];
                eIndex--;
            }

            board[eIndex] = entry;
        }
    }

    public void insertionSort() {
        int n = entriesCount;
        for(int k = 1; k < n; k++) {
            GameEntry entry = board[k];
            int j = k;
            while(j > 0 && board[j-1].getScore() > entry.getScore()) {
                board[j] = board[j-1];
                j--;
            }
            board[j] = entry;
        }
    }

    public GameEntry remove(int i) {
        if(i < 0 || i >= entriesCount)
            throw new IllegalArgumentException("There is not game entry at this position");

        GameEntry removedEntry = board[i];

        for(int j = i; j < entriesCount - 1; j++) {
            board[j] = board[j+1];
        }

        board[entriesCount - 1] = null;
        entriesCount--;
        return removedEntry;
    }

    public void displayEntries() {
        StringJoiner entriesJoiner = new StringJoiner(",");
        for(GameEntry entry : this.board)
            entriesJoiner.add(entry == null ? null : entry.toString());
        System.out.println("Entries: [" + entriesJoiner + "]");
    }

    public static void main(String[] args) {
        ScoreBoard board = new ScoreBoard(10);
        board.put(GameEntry.of("m", 300));
        board.put(GameEntry.of("a", 200));
        board.displayEntries();
        board.put(GameEntry.of("b", 400));
        board.displayEntries();
//        board.remove(1);
        board.insertionSort();
        board.displayEntries();
    }
}
