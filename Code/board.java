package Code;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class board{

    private final int row = 7;
    private final int col = 7;
    private JLabel[][] grid;
    private String[][] pos;
    private boolean colorSwitch = true;

    public board(){
        this.grid = new JLabel[this.row][this.col];
        this.pos  = new String[this.row][this.col];
    }

    private void createBoard(){
        JFrame frame = new JFrame("Java Chess");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(750, 750);
        frame.getContentPane().setBackground(new Color(18, 18, 19));
        JPanel gridPanel = new JPanel(new GridLayout(this.row, this.col, 0, 0));
        gridPanel.setBorder(BorderFactory.createLineBorder(new Color(230,230,230),2));
        gridPanel.setBackground(new Color(18, 18, 19));

        Border boxBorder = BorderFactory.createLineBorder(new Color(58, 58, 60), 1);
        Dimension boxSize = new Dimension(85, 85);

        final String[] rows = {"A","B","C","D","E","F","G"};
        final int[] cols    = {1,2,3,4,5,6,7};

        for(int r = 0; r < this.row; r++){
            for(int c = 0; c < this.col; c++){
                JLabel box = new JLabel("", SwingConstants.CENTER);
                box.setPreferredSize(boxSize);
                box.setMinimumSize(boxSize); 
                box.setBorder(boxBorder);
                box.setForeground(Color.WHITE);
                box.setOpaque(true);
                box.setBackground(new Color(18, 18, 19));
                this.grid[r][c] = box;
                this.pos[r][c] = rows[r] + cols[c];
                gridPanel.add(box);
                setColor(r,c);
            }
        }
        JPanel gridWrapper = new JPanel(new GridBagLayout());
        gridWrapper.setBackground(new Color(18, 18, 19));
        gridWrapper.add(gridPanel);
        frame.add(gridWrapper, BorderLayout.CENTER);
        frame.setVisible(true);
        return;
    }

    private void setColor(int r, int c){
        if(this.colorSwitch){
            this.grid[r][c].setBackground(new Color(0,0,0));
            this.colorSwitch = !this.colorSwitch;
        }else{
            this.grid[r][c].setBackground(new Color(230, 225, 211));
            this.colorSwitch = !this.colorSwitch;
        }
    }

    public String[][] getPositions(){
        return pos;
    }

    public void initBoard(){
        createBoard();
    }
}