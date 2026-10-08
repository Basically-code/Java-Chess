package chessPiece;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import java.awt.Color;;

public class knight {

    private boolean[] highlights = new boolean[8];
    private int[] lastPoints;
    
    public void highlight2(int x, int y, boolean side, JLabel[][] labels, String[][] list) {
        lastPoints = new int[]{x,y};
        boolean up = true;
        for (int i = 0; i < 2; i++){
            int nextRow = up ? x + 2: x - 2;
            int nextCol = up ? y - 1: y + 1;
            
            if (valid(nextRow,list.length) && valid(nextCol,list[0].length)){
                if(OpponentChecker(nextRow, nextCol, list, side)){
                    Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
                    labels[nextRow][nextCol].setBorder(border);
                    highlights[0] = true;
                    System.out.println("Upper");
                }//Move
                else{
                    Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                    labels[nextRow][nextCol].setBorder(border);
                    highlights[1] = true;
                }//Attack
            }
            up = !up;
        }
        boolean right = true;
        for (int i = 0; i < 2; i++){
            int nextRow = right ? x - 1: x + 1;
            int nextCol = right ? y + 2: y - 2;
            
            if (valid(nextRow,list.length) && valid(nextCol,list[0].length)){
                if (OpponentChecker(nextRow, nextCol, list, side)){
                    Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                    labels[nextRow][nextCol].setBorder(border);
                    highlights[3] = true;
                }//Attack
                if(pieceChecker(nextRow, nextCol, list, side)){
                    Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
                    labels[nextRow][nextCol].setBorder(border);
                    highlights[2] = true;
                }//Move
                
                
            }
            right = !right;
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
        if(highlights[0]) labels[lastPoints[0] + 2][lastPoints[1] + 1].setBorder(null);
        if(highlights[1]) labels[lastPoints[0] + 2][lastPoints[1] - 1].setBorder(null);
        if(highlights[2]) labels[lastPoints[0] - 2][lastPoints[1] + 1].setBorder(null);
        if(highlights[3]) labels[lastPoints[0] - 2][lastPoints[1] - 1].setBorder(null);
        if(highlights[4]) labels[lastPoints[0] - 1][lastPoints[1] + 2].setBorder(null);
        if(highlights[5]) labels[lastPoints[0] - 1][lastPoints[1] - 2].setBorder(null);
        if(highlights[6]) labels[lastPoints[0] + 1][lastPoints[1] + 2].setBorder(null);
        if(highlights[7]) labels[lastPoints[0] + 1][lastPoints[1] - 2].setBorder(null);
        highlights = new boolean[8];
    }

    public void highlight(int x, int y,boolean side,JLabel[][] labels, String[][] list){
        lastPoints = new int[]{x,y};
        boolean up = true;
        for(int i = 0; i < 2; i ++){
            int nextRow = up ? x + 2: x - 2;
            boolean sides = true;
            for(int j = 0; j < 2; j++){
                int nextCol = sides ? y + 1: y - 1;
                if (valid(nextRow,list.length) && valid(nextCol, list[0].length)){
                    if(OpponentChecker(nextRow, nextCol, list, side)){
                        Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                        labels[nextRow][nextCol].setBorder(border);
                    }//Move
                    if (pieceChecker(nextRow, nextCol, list, side)){
                        Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
                        labels[nextRow][nextCol].setBorder(border);
                    }//Attack
                    highlights[j + (i * 2)] = true;
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
                        Border border = BorderFactory.createLineBorder(new Color(204, 35, 22),3);
                        labels[nextRow][nextCol].setBorder(border);
                    }//Move
                    if(pieceChecker(nextRow, nextCol, list, side)){
                        Border border = BorderFactory.createLineBorder(new Color(172, 247, 119),3);
                        labels[nextRow][nextCol].setBorder(border);
                    }//Attack
                    highlights[j + (i * 2) + 4] = true;
                    sides = !sides;
                }
            } right = !right;
        }
    }
}
