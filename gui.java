import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gui implements ActionListener {

    public static Board game = new Board();

    //This is the main panel that the game is run on
    public JSplitPane split;
    public JLabel winner;
    public Space clicked;
    public boolean checkForPawnMove = true;
    public int t;
    public boolean checkForPiece = false;
    public static boolean turn = false; //white is false, black is true
    public int saveI = 0;
    public int saveJ = 0;
    public JLabel BL;
    public JLabel WL;
    public int usedW;
    public int usedB;
    public JButton[][] bIcons;
    public JButton[][] wIcons;
    public gui(){
        JFrame f = new JFrame("Chess");
        //Top panel for the board
        JPanel p = new JPanel(new GridLayout(0, 8));
        //Split panel for the pieces
        JSplitPane score = new JSplitPane();


        //variables for the black taken
        JSplitPane bScore = new JSplitPane();
        JPanel blackT = new JPanel();
        JPanel blackP = new JPanel(new GridLayout(4, 4));
        bIcons = new JButton[4][4];

        //variables for the white taken
        JSplitPane wScore = new JSplitPane();
        JPanel whiteT = new JPanel();
        JPanel whiteP = new JPanel(new GridLayout(4, 4));
        wIcons = new JButton[4][4];
        usedW = 0;

        split = new JSplitPane();

        //this is the winning label for at the end of the game
        winner = new JLabel("");
        winner.setBounds(300, 200, 100, 100);
        winner.setVisible(false);



        //sets the black taken piece grid
        bScore.setOrientation(JSplitPane.VERTICAL_SPLIT);
        bScore.setDividerLocation(50);
        bScore.setTopComponent(blackT);
        bScore.setBottomComponent(blackP);

        //sets the white taken piece grid
        wScore.setOrientation(JSplitPane.VERTICAL_SPLIT);
        wScore.setDividerLocation(50);
        wScore.setTopComponent(whiteT);
        wScore.setBottomComponent(whiteP);

        //sets both the white and black together
        score.setOrientation(JSplitPane.VERTICAL_SPLIT);
        score.setDividerLocation(270);
        score.setTopComponent(bScore);
        score.setBottomComponent(wScore);

        //finishes the splits with the board
        split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(150);
        split.setTopComponent(score);
        split.setBottomComponent(p);


        p.setBorder(new LineBorder(Color.BLACK));
        BL = new JLabel("Black Pieces: ");
        WL = new JLabel("White Pieces: ");


        Insets buttonIns = new Insets(0, 0, 0, 0);
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++) {
                game.board[i][j].b.setMargin(buttonIns);
                p.add(game.board[i][j].b);
                game.board[i][j].b.addActionListener(this);
            }
        }

        //sets the stage for adding the taken pieces
        Insets bPieceIns = new Insets(0, 0, 0, 0);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
               bIcons[i][j] = new JButton();
               bIcons[i][j].setMargin(bPieceIns);
               blackP.add(bIcons[i][j]);
            }
        }
        //white taken pieces
        Insets wPieceIns = new Insets(0, 0, 0, 0);
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                wIcons[i][j] = new JButton();
                wIcons[i][j].setMargin(wPieceIns);
                whiteP.add(wIcons[i][j]);
            }
        }
        check();

        p.add(new JLabel(""));
        blackT.add(BL);
        whiteT.add(WL);


        f.add(winner);

        f.add(split);

        f.pack();
        f.setMaximumSize(f.getSize());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700, 600);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++) {
                if (e.getSource() == game.board[i][j].b) {
                    check();
                    if (game.board[i][j].piece.pieceColor == turn || game.lastSpace.piece.pieceColor == turn) {

                        if (gui.game.lastSpace != null && !(game.lastSpace.equals(game.board[i][j]))) {

                            clicked = game.board[i][j];
                            clicked.piece = game.board[i][j].piece;

                            if ((game.lastSpace.piece.color.equals(clicked.piece.color))) {
                                break;
                            } else {
                                if (!(game.lastSpace.piece.name().equals("Knight"))) {
                                    pieceDetection(saveI, saveJ, i, j);
                                }
                                game.lastSpace.piece.pawnCheck(i, j, saveI, saveJ);

                                //This section of code checks to see if the pawn can do its diagnal attack
                                if (game.lastSpace.piece.name().equals("Pawn")) {
                                    if ((Math.abs(saveI - i) == 1 && Math.abs(saveJ - j) == 1) && !clicked.piece.name().equals("Blank")) {
                                        if (game.lastSpace.piece.color.equals("black")) {
                                            if (!game.board[saveI - 1][saveJ - 1].piece.name().equals("Blank") || !game.board[saveI - 1][saveJ + 1].piece.name().equals("Blank")) {
                                                game.lastSpace.piece.bypassMove = true;
                                                System.out.println("Hello");
                                            } else if (game.lastSpace.piece.first == 0 && (!game.board[saveI - 2][saveJ - 1].piece.name().equals("Blank") || !game.board[saveI - 2][saveJ + 1].piece.name().equals("Blank"))) {
                                                game.lastSpace.piece.bypassMove = true;
                                                System.out.println("Hello");
                                            } else {
                                                game.lastSpace.piece.bypassMove = false;
                                            }
                                        }
                                        if (game.lastSpace.piece.color.equals("white")) {
                                            if (!game.board[saveI + 1][saveJ + 1].piece.name().equals("Blank") || !game.board[saveI + 1][saveJ - 1].piece.name().equals("Blank")) {
                                                game.lastSpace.piece.bypassMove = true;
                                                System.out.println("Hello");
                                            } else if (game.lastSpace.piece.first == 0 && (!game.board[saveI + 2][saveJ + 1].piece.name().equals("Blank") || !game.board[saveI + 2][saveJ - 1].piece.name().equals("Blank"))) {
                                                game.lastSpace.piece.bypassMove = true;
                                                System.out.println("Hello");
                                            } else {
                                                game.lastSpace.piece.bypassMove = false;
                                            }
                                        }
                                    } else {
                                        game.lastSpace.piece.bypassMove = false;
                                    }
                                }

                                //This section checks to see if the piece is legally allowed to move
                                if (!checkForPiece && game.lastSpace.piece.move(Math.abs(saveI - i), Math.abs(saveJ - j))) {
                                    if (!(clicked.piece.color.equals(game.lastSpace.piece.color)) && (!clicked.piece.name().equals("Blank"))) {
                                        //this checks to make sure the pawn takes the piece correctly
                                        System.out.println("Before");

                                        if (game.lastSpace.piece.name().equals("Pawn")) {
                                            System.out.println("Check1");

                                            checkForPawnMove = game.lastSpace.piece.pawnCheck(i, j, saveI, saveJ);
                                            System.out.println("Check");
                                            System.out.println(checkForPawnMove);

                                        } else {
                                            checkForPawnMove = true;
                                        }
                                        if (checkForPawnMove) {
                                            if (clicked.piece.color.equals("white")) {
                                                wIcons[usedW / 4][usedW % 4].setIcon(setButtonIcon(game.board[i][j].piece));

                                                usedW++;
                                            } else if (clicked.piece.color.equals("black")) {
                                                bIcons[usedB / 4][usedB % 4].setIcon(setButtonIcon(game.board[i][j].piece));
                                                usedB++;
                                            }
                                            game.board[i][j].piece.taken = true;
                                        } else {
                                            break;
                                        }
                                    }


                                    //move
                                    if (game.lastSpace.piece.name().equals("Pawn") && game.lastSpace.piece.color.equals("black") && i == 0) {
                                        clicked.setPiece(new Queen("Queen", true));
                                    } else if (game.lastSpace.piece.name().equals("Pawn") && game.lastSpace.piece.color.equals("white") && i == 7) {
                                        clicked.setPiece(new Queen("Queen", false));
                                    } else {
                                        clicked.setPiece(game.lastSpace.piece);
                                    }

                                    //placeP  = game.lastSpace.piece;
                                    game.lastSpace.piece = new Blank("Blank", true);


                                    clicked.setIcn(clicked.row, clicked.colm);
                                    game.lastSpace.setIcn(i, j);

                                    game.lastSpace.setColor(game.lastSpace.getColor());
                                    game.lastSpace = null;


                                    if (turn) {
                                        turn = false;
                                    } else {
                                        turn = true;
                                    }
                                    check();

                                }
                            }


                        } else if (gui.game.lastSpace != null && game.lastSpace.equals(game.board[i][j])) {
                            game.lastSpace.setColor(game.lastSpace.getColor());
                            gui.game.lastSpace = null;
                        } else {
                            //save
                            gui.game.lastSpace = game.board[i][j];
                            System.out.println("HEEEE");

                            saveI = i;
                            saveJ = j;
                            game.lastSpace.b.setBackground(Color.MAGENTA);

                        }
                    }
                }
            }
        }
    }

    public void win(String winningColor){
        split.setVisible(false);
        winner.setVisible(true);
        if(winningColor.equals("black")){
            winner.setText("White Wins!");
        }else{
            winner.setText("Black Wins!");
        }
    }

    public void check(){
        reset();
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                if(!game.board[i][j].piece.name().equals("Blank")){
                    for(int n = 0; n < game.board.length; n++){
                        for(int m = 0; m < game.board.length; m++){
                            if(game.board[i][j].piece.move(Math.abs(n-i), Math.abs(m-j))){
                                pieceDetection(i, j, n, m);
                                if(checkForPiece == false){
                                    if(turn) {
                                        if (game.board[i][j].piece.color.equals("white")) {
                                            game.board[n][m].attack(true, false);
                                            game.board[n][m].b.setBackground(Color.YELLOW);
                                        } else {
                                            game.board[n][m].setColor(game.board[n][m].getColor());
                                            game.board[n][m].attacked = false;
                                        }
                                    }else{
                                        if (game.board[i][j].piece.color.equals("black")) {
                                            game.board[n][m].attack(true, true);
                                            game.board[n][m].b.setBackground(Color.GREEN);
                                        } else {
                                            game.board[n][m].setColor(game.board[n][m].getColor());
                                            game.board[n][m].attacked = false;
                                        }

                                    }

                                }else if(!game.board[n][m].attacked){
                                    game.board[n][m].setColor(game.board[n][m].getColor());
                                }
                            }
                        }
                    }
                }
            }
        }


    }

    public void reset(){
        for(int i = 0; i < game.board.length; i++){
            for(int j = 0; j < game.board.length; j++){
                game.board[i][j].attacked = false;
            }
        }
    }


    public ImageIcon setButtonIcon(pieces p){
        if(p.name().equals("King")){
            win(p.color);
        }
        Image i = new ImageIcon(p.I).getImage();
        Image newImage = i.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        return new ImageIcon(newImage);
    }
    public void pieceDetection(int oldI, int oldJ, int newI, int newJ) {
        if(Math.abs(newI-oldI) == 1 || Math.abs(newJ-oldJ) == 1){
            checkForPiece = false;
        }else if (oldJ == newJ) {
                if(oldI < newI) {
                    for (int g = oldI + 1; g < newI; g++) {
                        if (!(game.board[g][oldJ].piece.name().equals("Blank"))) {
                            checkForPiece = true;
                            break;
                        } else {
                            checkForPiece = false;
                        }
                    }
                }else{
                    for (int g = oldI-1; g > newI; g--) {
                        if (!(game.board[g][oldJ].piece.name().equals("Blank"))) {
                            checkForPiece = true;
                            break;
                        } else {
                            checkForPiece = false;
                        }
                    }
                }
            } else if (oldI == newI) {
                if(oldJ < newJ) {
                    for (int g = oldJ + 1; g < newJ-1; g++) {
                        if (!(game.board[oldI][g].piece.name().equals("Blank"))) {
                            checkForPiece = true;
                            break;
                        } else {
                            checkForPiece = false;
                        }
                    }
                }else{
                    for (int g = oldJ - 1; g > newJ; g--) {
                        if (!(game.board[oldI][g].piece.name().equals("Blank"))) {
                            checkForPiece = true;
                            break;
                        } else {
                            checkForPiece = false;
                        }
                    }
                }
            } else if (newI > oldI && newJ > oldJ) {
                t = oldJ+1;
                for (int g = oldI+1; g < newI; g++) {
                        if (!(game.board[g][t].piece.name().equals("Blank"))) {
                            checkForPiece = true;
                            break;
                        } else {
                            checkForPiece = false;
                        }
                        t++;
                }
            }else if (newI < oldI && newJ < oldJ) {
                t = oldJ-1;
                for (int g = oldI-1; g > newI; g--) {
                    if (!(game.board[g][t].piece.name().equals("Blank"))) {
                        checkForPiece = true;
                        break ;
                    } else {
                        checkForPiece = false;
                    }
                    t--;
                }
            } else if (newI < oldI && newJ > oldJ) {
                t = oldJ+1;
                for (int g = oldI-1; g > newI; g--) {
                    if (!(game.board[g][t].piece.name().equals("Blank"))) {
                        checkForPiece = true;
                        break;
                    } else {
                        checkForPiece = false;
                    }
                    t++;
                }
            }else if (newI > oldI && newJ < oldJ) {
                t = oldJ-1;
                for (int g = oldI+1; g < newI; g++) {
                    if (!(game.board[g][t].piece.name().equals("Blank"))) {
                        checkForPiece = true;
                        break;
                    } else {
                        checkForPiece = false;
                    }
                    t--;
                }
            }
    }
}

