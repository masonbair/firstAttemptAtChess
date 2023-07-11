public class Blank extends pieces{
    public boolean bypassMove = true;

    public Blank(String name, boolean color){
        super(name, color);


    }
    public boolean move(int i, int j){
        return false;
    }
    public String name(){
        return "Blank";
    }
    public boolean hasPiece(){
        return false;
    }

    public boolean checkColor(String s){
        if(s.equals(color)){
            return true;
        }
        return false;
    }
}