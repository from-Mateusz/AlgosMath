package cz.mateusz.dstructures.arrays;

public class EnchantedForest {

    private int players;

    int meetings[][];

    int rememberedMeetings = 0;

    EnchantedForest(int players) {
        this.players = players;
        this.meetings = new int[players * (players - 1)][2];
    }

    public void meet(int p1, int p2) {
        if(p1 == p2) return;
        if(meetings[0][0] == 0) {
            this.meetings[0][0] = p1;
            this.meetings[0][1] = p2;
        }
        else if(meetings[0][0] == p1 && meetings[0][1] == p2 || meetings[0][1] == p2 && meetings[0][1] == p1) return;
        else if(alreadyMet(p1, p2)) return;
        else {
            meetings[rememberedMeetings][0] = p1;
            meetings[rememberedMeetings][1] = p2;
        }
        rememberedMeetings++;
    }

    boolean isWinner(int player) {
        int metCount = 0;
        for(int i = 0; i < rememberedMeetings; i++) {
            if(meetings[i][0] == player || meetings[i][1] == player)
                metCount++;
        }
        return metCount == players - 1;
    }

    public boolean alreadyMet(int p1, int p2) {
        for(int i = 0; i < rememberedMeetings; i++) {
            if((meetings[i][0] == p1 && meetings[i][1] == p2) || (meetings[0][1] == p2 && meetings[0][1] == p1)) {
                return true;
            }
        }
        return false;
    }

    public int getPlayers() {
        return players;
    }

    public void previewMeetings() {
        System.out.print("[ ");
        for(int i = 0; i < meetings.length; i++) {
            System.out.print("[" + meetings[i][0] + "," + meetings[i][1] + "], ");
        }
        System.out.println(" ]");
    }

    public static void main(String ...args) {
        EnchantedForest game = new EnchantedForest(5);
        game.previewMeetings();
        for(int i = 2; i <= 5; i++) {
            game.meet(1, i);
        }
        game.previewMeetings();
        System.out.println("Player #1 has won a game: " + game.isWinner(1));
    }
}
