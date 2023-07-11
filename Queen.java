public class Queen extends pieces{
    public boolean bypassMove = true;

    public Queen(String name, boolean color){
        super(name, color);
        pieceColor = color;

    }
    public boolean move(int i, int j){
        if((i == j) || (i > 0 && j == 0) || (i == 0 && j > 0)){
            return true;
        }
        return false;
    }

    public String name(){
        return "Queen";
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
