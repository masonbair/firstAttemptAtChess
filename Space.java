import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Space{
    private int size = 20;
    private String spaceC;
    public int row;
    public int colm;
    public JButton b;
    public boolean attacked = false;
    public boolean attackedByColor = false;


    //tells us what peice we are using
    public pieces piece;


    public Space(int c, int r){
        row = r;
        colm = c;
        spaceC = "";
        b = new JButton();
        //b.setSize(1, 1);
        //b.addActionListener(this);

        piece = new Blank("Blank", false);

        //sets the white pieces
        if(colm == 0){
            switch(row){
                case 0:
                case 7:
                    piece = new Rook("Rook", false);
                    break;
                case 1:
                case 6:
                    piece = new Knight("Knight", false);
                    break;
                case 2:
                case 5:
                    piece = new Bishop("Bishop", false);
                    break;
                case 3:
                    piece = new King("King", false);
                    break;
                case 4:
                    piece = new Queen("Queen", false);
                    break;
                default:
                    break;
            }
        }
        if(colm == 1){
            piece = new Pawn("Pawn", false);
        }

        //sets the black pieces
        if(colm == 7){
            switch(row){
                case 0:
                case 7:
                    piece = new Rook("Rook", true);
                    break;
                case 1:
                case 6:
                    piece = new Knight("Knight", true);
                    break;
                case 2:
                case 5:
                    piece = new Bishop("Bishop", true);
                    break;
                case 3:
                    piece = new King("King", true);
                    break;
                case 4:
                    piece = new Queen("Queen", true);
                    break;
                default:
                    break;
            }
        }
        if(colm == 6){
            piece = new Pawn("Pawn", true);
        }
        setIcn(row, colm);
    }

    public void setColor(String c){
        spaceC = c;
        if(spaceC.equals("Black"))
            b.setBackground(new Color(186, 104, 200));
        if(spaceC.equals("White"))
            b.setBackground(Color.WHITE);
    }

    public String getColor(){
        return spaceC;
    }

    public void setIcn(int row, int col){
        if(piece!=null){
            Image i = new ImageIcon(piece.I).getImage();
            Image newImage = i.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

            b.setIcon(new ImageIcon(newImage));
        }else{
            b.setIcon(null);
        }
    }

    public void setPiece(pieces p){
        piece = p;
    }

    public void attack(boolean h, boolean color){
        attacked = h;
        attackedByColor = color;

    }


}
