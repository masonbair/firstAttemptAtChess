public class King extends pieces{
    public boolean bypassMove = true;
    public King(String name, boolean color){
        super(name, color);
        pieceColor = color;

    }

    public boolean move(int i, int j){
        if(i < 2 && j < 2){
            return true;
        }
        return false;
    }

    public String name(){
        return "King";
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