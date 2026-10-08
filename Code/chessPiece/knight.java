package chessPiece;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;
import java.util.HashSet;

public class knight {
    private HashSet<Pair> possibleMoves;
    private HashSet<Pair> possibleAttacks;

    public knight(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    public record Pair(int x, int y) {}

    private void knightsMoves(int x, int y,boolean side,String[][] list){

        boolean up = true;
        for(int i = 0; i < 2; i ++){
            int nextRow = up ? x + 2: x - 2;
            boolean sides = true;
            for(int j = 0; j < 2; j++){
                int nextCol = sides ? y + 1: y - 1;
                if (valid(nextRow,list.length) && valid(nextCol, list[0].length)){
                    if(OpponentChecker(nextRow, nextCol, list, side)){
                        this.possibleAttacks.add(new Pair(nextRow, nextCol));
                    }//Move
                    if (pieceChecker(nextRow, nextCol, list, side)){
                        this.possibleMoves.add(new Pair(nextRow, nextCol));
                    }//Attack
                    sides = !sides;
                }
            } up = !up;
        }
        boolean right = true;
        for(int i = 0; i < 2; i ++){
            int nextRow = right ? x - 1: x + 1;
            boolean sides = true;
            for(int j = 0; j < 2; j++){
                int nextCol = sides ? y + 2: y - 2;
                if (valid(nextRow,list.length) && valid(nextCol, list[0].length)){
                    if(OpponentChecker(nextRow, nextCol, list, side)){
                        this.possibleAttacks.add(new Pair(nextRow, nextCol));
                    }//Move
                    if (pieceChecker(nextRow, nextCol, list, side)){
                        this.possibleMoves.add(new Pair(nextRow, nextCol));
                    }//Attack
                    sides = !sides;
                }
            } right = !right;
        }
    }
    
    public void highlight(int x, int y,boolean side,JLabel[][] labels, String[][] list){
        knightsMoves(x, y, side, list);
        for (Pair p: possibleAttacks){
            Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
            labels[p.x()][p.y()].setBorder(border);
        }
        for (Pair p: possibleMoves){
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[p.x()][p.y()].setBorder(border);
        }
    }

    private boolean OpponentChecker(int x, int y, String[][] lists, boolean side){
        String temp = lists[x][y];
        if(side == false && temp.substring(3,4).equals("w")) return true;
        else if(side && temp.substring(3,4).equals("b")) return true;
        return false;
    }

    private boolean pieceChecker(int x, int y, String[][] lists, boolean side){
        String temp = lists[x][y];
        if (temp.substring(3,temp.length()).equals("##")) return true;
        return false;
    }

    private boolean valid(int n, int max){
        return n >= 0 && n < max;
    }

    public void removeHighlights(JLabel[][] labels){
        this.possibleAttacks.addAll(possibleMoves);
        for (Pair p: possibleAttacks){
            labels[p.x()][p.y()].setBorder(null);
        }
    }

}
