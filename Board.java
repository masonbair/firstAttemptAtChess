import javax.swing.*;

public class Board {

    public Space[][] board = new Space[8][8];
    public Space lastSpace = null;

    public Board(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = new Space(i, j);
            }
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                if((i+j)%2 != 0) {
                    board[i][j].setColor("Black");
                }else{
                    board[i][j].setColor("White");
                }
            }
        }
    }
}
