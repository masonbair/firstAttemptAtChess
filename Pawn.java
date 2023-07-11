public class Pawn extends pieces{

    //private int first = 0;
    private int sI;
    private int fI;

    public Pawn(String name, boolean color){

        super(name, color);
        pieceColor = color;

    }
    public boolean move(int i, int j){
        if(bypassMove){
            System.out.println("JJJJ");
            if(j == 1 && i == 1) {
                System.out.println("HHHH");
                return true;
            }
        }
        if((!pieceColor && sI < fI) || (pieceColor && fI < sI)) {
            if (first < 1) {
                if (i <= 2 && j == 0) {
                    first++;
                    return true;
                }

            } else {
                if (i == 1 && j == 0)
                    return true;
            }
            return false;
        }
        return false;
    }

    public String name(){
        return "Pawn";
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

    public boolean pawnCheck(int i, int j, int saveI, int saveJ){
        fI = i;
        sI = saveI;
        if(pieceColor) {
            if ((i < saveI && j < saveJ) || (i < saveI && j > saveJ)){
                return true;
            }
            return false;
        }else{
            if((i > saveI && j > saveJ) || (i > saveI && j < saveJ)){
                return true;
            }
            return false;
        }
    }
}
