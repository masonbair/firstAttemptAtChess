public class Rook extends pieces{
    public boolean bypassMove = true;

    public Rook(String name, boolean color){
        super(name, color);
        this.pieceColor = color;

    }
    public boolean move(int i, int j){
        if((i > 0 && j == 0) || (i == 0 && j > 0)){
            return true;
        }
        return false;
    }

    public String name(){
        return "Rook";
    }

    public boolean hasPiece(){
        return true;
    }

    public boolean checkColor(String s){
        if(s.equals(color)){
            return true;
        }
        return false;
    }
}