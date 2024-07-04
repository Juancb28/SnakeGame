package ec.edu.player;

public class PlayerGame {

    // Attributes
    private String namePlayer;
    private int score;

    // Constructor
    public PlayerGame(String name, int score) {
        setNamePlayer(name);
        setScore(0);
    }

    // Getters & Setters
    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        if (namePlayer == null)
            namePlayer = "Anonymous";
        else
            this.namePlayer = toUpperNamePlayer(namePlayer);
    }

    private String toUpperNamePlayer(String namePlayer) {
        char[] auxChar = namePlayer.toCharArray();
        for (char c : auxChar) {
            c = Character.toUpperCase(c);
        }
        return String.valueOf(auxChar);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void calculateScore(int getPressedTimes){
        int auxScore = 50;
        if (getPressedTimes <= 2) {
            setScore(getScore() + auxScore);
        } else {
            for (int i = 0; i < getPressedTimes; i++) {
                if (auxScore > 2) {
                    auxScore -= 3;
                }
            }
            setScore(getScore() + auxScore);
        }
    }

}
