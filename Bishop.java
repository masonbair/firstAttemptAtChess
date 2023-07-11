public class Bishop extends pieces{
    public boolean bypassMove = true;

    public Bishop(String name, boolean color){
        super(name, color);
        pieceColor = color;

    }

    public boolean move(int i, int j){
        if(i == j){
            return true;
        }
        return false;
    }

    public String name(){
        return "Bishop";
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