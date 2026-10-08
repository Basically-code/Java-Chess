package chessPiece;

import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;

public class bishop {
    private HashSet<Pair> possibleMoves;
    private HashSet<Pair> possibleAttacks;

    public bishop(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    public record Pair(int x, int y) {}

    private void restart(){
        this.possibleAttacks = new HashSet<>();
        this.possibleMoves   = new HashSet<>();
    }

    private void bishopsMoves(int x, int y,boolean side,String[][] list){
        boolean map1 = true;
        boolean map2 = true;
        int nextRow =  x + 1;
        int nextCol =  y + 1;
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 2; j++){
                while (valid(nextCol, list[0].length) && valid(nextRow, list.length)){
                    if (OpponentChecker(nextRow, nextCol, list, side)){
                        possibleAttacks.add(new Pair(nextRow, nextCol));
                        break;
                    }else if (pieceChecker(nextRow, nextCol, list, side)){
                        possibleMoves.add(new Pair(nextRow, nextCol));
                    }else{
                        break;
                    }
                    nextRow = map1 ? nextRow + 1: nextRow - 1;
                    nextCol = map2 ? nextCol + 1: nextCol - 1;
                }
                map1 = !map1;
            nextRow = map1 ? x + 1 : x - 1;
            nextCol = map2 ? y + 1 : y - 1;
        }
        
        // Flip the Column direction
        map2 = !map2;
        nextRow = map1 ? x + 1 : x - 1;
        nextCol = map2 ? y + 1 : y - 1;
        }
         
    }
    public void highlight(int x, int y,boolean side,JLabel[][] labels, String[][] list){
        bishopsMoves(x, y, side, list);
        for (Pair p: possibleAttacks){
            Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
            labels[p.x()][p.y()].setBorder(border);
        }
        for (Pair p: possibleMoves){
            Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
            labels[p.x()][p.y()].setBorder(border);
        }
    }

    public void removeHighlights(JLabel[][] labels){
        this.possibleAttacks.addAll(possibleMoves);
        for (Pair p: possibleAttacks){
            labels[p.x()][p.y()].setBorder(null);
        }
        restart();
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
}
