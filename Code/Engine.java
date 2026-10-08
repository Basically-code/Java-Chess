import chessPiece.*;
import javax.swing.JLabel;

public class Engine {
    private int click = 0;
    private board b;
    private String lastPiece = "";
    private int pressCount = 0;

    //
    private pawn pawns;
    private knight knights;
    private king kings;
    private bishop bishops;

    public Engine(){
        this.b       = new board(this);
        this.pawns   = new pawn();
        this.knights = new knight();
        this.kings   = new king();
        this.bishops = new bishop();
    }

    public void marked(int x, int y, JLabel[][] labels, String[][] list){
        String temp = list[x][y];
        if (!temp.substring(3,temp.length()).equals("##")){
            if(click == 0){
                highlight(x,y,labels,temp,list);
            }
        }
    }

    private void highlight(int x, int y, JLabel[][] label, String piece, String[][] list){
        boolean pos = false;
        if(piece.substring(3,4).equals("w")) {pos = true;}
        
        if (piece.substring(4,(piece.length())).equals("k"))        {this.kings.highlight(x,y,pos,label,list); this.lastPiece = "k";}
        else if (piece.substring(4,(piece.length())).equals("kn"))  {this.knights.highlight(x,y,pos,label,list); this.lastPiece = "kn";}
        else if (piece.substring(4,(piece.length())).equals("q")) ;
        else if (piece.substring(4,(piece.length())).equals("b"))   {this.bishops.highlight(x,y,pos,label,list); this.lastPiece = "b";}
        else if (piece.substring(4,(piece.length())).equals("r")) ;
        else if (piece.substring(4,(piece.length())).equals("p"))   {this.pawns.highlight(x,y,pos,label,list); this.lastPiece = "p";}

        pressCount ++;
    }

    public void removeHighlights(JLabel[][] labels){
        if(pressCount > 0){
            if(lastPiece.equals("p"))       this.pawns.removeHighlights(labels);
            else if(lastPiece.equals("kn")) this.knights.removeHighlights(labels);
            else if(lastPiece.equals("k"))  this.kings.removeHighlights(labels);
            else if(lastPiece.equals("b"))  this.bishops.removeHighlights(labels);
        }
        lastPiece = "";
        
    }
}
