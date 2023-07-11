import javax.swing.*;

public abstract class pieces {
    public String I;
    public int pieceCode = 0;
    public String color;
    public boolean bypassMove = true;
    public boolean taken = false;
    public int first = 0;



    public pieces(String name, boolean clor){ // true is black, false is white
        if(name.equals("Pawn")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_pawn.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_pawn.png";
                color = "white";
            }
            pieceCode = 1;
        }else if(name.equals("Rook")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_rook.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_rook.png";
                color = "white";
            }
            pieceCode = 2;
        }else if(name.equals("Knight")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_knight.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_knight.png";
                color = "white";
            }
            pieceCode = 3;
        }else if(name.equals("Bishop")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_bishop.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_bishop.png";
                color = "white";
            }
            pieceCode = 4;
        }else if(name.equals("Queen")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_queen.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_queen.png";
                color = "white";
            }
            pieceCode = 5;
        }else if(name.equals("King")){
            if(clor) {
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/Black_king.png";
                color = "black";
            }else{
                I = "/home/mason/IdeaProjects/Chess/src/Pieces/White_king.png";
                color = "white";
            }
            pieceCode = 6;
        }else if(name.equals("Blank")){
            I = null;
            color = "";
        }

    }
    public abstract boolean move(int i, int j);
    public abstract String name();
    public abstract boolean hasPiece();

    public abstract boolean checkColor(String s);

    public boolean pieceColor = !gui.turn;
    public boolean pawnCheck(int i, int j, int saveI, int saveJ){
        return true;
    }

}
