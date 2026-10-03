//For the Game board
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

public class board{

    private final int row = 8;
    private final int col = 8;
    private JLabel[][] grid;
    private String[][] pos;
    private boolean colorSwitch = true;
    private int x_Cord = 0;
    private int y_Cord = 0;
    private int box_width  = 80;
    private int box_height = 80;
    private int frame_width  = 700;
    private int frame_height = 700;
    private int border_thickness = 3; 

    public board(){
        this.grid = new JLabel[this.row][this.col];
        this.pos  = new String[this.row][this.col];
    }

    private void createBoard(){
        JFrame frame = new JFrame("Java Chess");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(this.frame_width, this.frame_height);
        frame.getContentPane().setBackground(new Color(18, 18, 19));
        JPanel gridPanel = new JPanel(new GridLayout(this.row, this.col, 0, 0));
        gridPanel.setBorder(BorderFactory.createLineBorder(new Color(135, 45, 35), this.border_thickness));
        gridPanel.setBackground(new Color(18, 18, 19));

        Border boxBorder = BorderFactory.createLineBorder(new Color(58, 58, 60), 1);
        Dimension boxSize = new Dimension(this.box_width, this.box_height);

        final String[] cols = {"A","B","C","D","E","F","G","H"};
        final int[] rows   = {1,2,3,4,5,6,7,8};
        final int size = 7;

        for(int r = 0; r < this.row; r++){
            this.colorSwitch = !this.colorSwitch;
            for(int c = 0; c < this.col; c++){
                JLabel box = new JLabel("", SwingConstants.CENTER);
                box.setPreferredSize(boxSize);
                box.setMinimumSize(boxSize); 
                box.setBorder(boxBorder);
                box.setForeground(Color.WHITE);
                box.setOpaque(true);
                box.setBackground(new Color(18, 18, 19));
                this.grid[r][c] = box;
                this.pos[r][c] = cols[c] + rows[size - r];
                gridPanel.add(box);
                setColor(r,c);
            }
        }
        mouseClickListener(frame);
        JPanel gridWrapper = new JPanel(new GridBagLayout());
        gridWrapper.setBackground(new Color(18, 18, 19));
        gridWrapper.add(gridPanel);
        frame.add(gridWrapper, BorderLayout.CENTER);
        frame.setResizable(false);
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

    private void mouseClickListener(JFrame frame){
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // e.getX() and e.getY() give coordinates relative to the top-left (0,0) of the frame
                board.this.x_Cord = e.getX();
                board.this.y_Cord = e.getY();
                int[] box = boxFinder();
                System.out.println("The box is: " + pos[box[0]][box[1]]);
            }
        });
    }
    //Calculate the space between the frame and the grid
    final int padding_width  = ((this.frame_width - (this.box_width*8))- (this.border_thickness*2)) / 2;
    final int padding_height = (((this.frame_height - (this.box_height*8))- (this.border_thickness*2)) / 2) + 10;
    //Constants due to constant window (non-resizable)

    //The four Corners
    Point top_left     = new Point(padding_width, padding_height);
    Point top_right    = new Point(padding_width + (this.box_width*8) , padding_height);
    Point bottom_left  = new Point(padding_width, padding_height + (this.box_width*8));
    Point bottom_right = new Point(padding_width + (this.box_width*8) , padding_height + (this.box_width*8));

    private int[] boxFinder(){
        //For the row
        int row = (this.y_Cord - padding_height) / this.box_height;
        //For the column
        int col = (this.x_Cord - padding_width) / this.box_width;
        return new int[]{row,col};
    }


    public void initBoard(){
        createBoard();
    }
}