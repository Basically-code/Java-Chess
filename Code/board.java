//For the Game board
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
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
import java.awt.Image;

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
    private Engine engine;

    public board(Engine engine){
        this.grid   = new JLabel[this.row][this.col];
        this.pos    = new String[this.row][this.col];
        this.engine = engine;
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
                this.pos[r][c] = cols[c] + rows[size - r] + "-##";
                gridPanel.add(box);
                setColor(r,c);
            }
        }
        loadChessIcons();
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
            this.grid[r][c].setBackground(new Color(133, 124, 123));
            this.colorSwitch = !this.colorSwitch;
        }else{
            this.grid[r][c].setBackground(new Color(230, 225, 211));
            this.colorSwitch = !this.colorSwitch;
        }
    }

    public String[][] getPositions(){
        return this.pos;
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
                board.this.engine.marked(box[0], box[1],board.this.grid,board.this.pos);
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

    //Next chess character sprites
    private ImageIcon loadScaledIcon(String fileName) {
        String imagePath = "Assets/" + fileName;
        Image image = new ImageIcon(imagePath).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    //For the pawns
    private final ImageIcon whitePawn = loadScaledIcon("white-pawn.png");
    private final ImageIcon BlackPawn = loadScaledIcon("black-pawn.png");
    //For the rooks
    private final ImageIcon whiteRook = loadScaledIcon("white-rook.png");
    private final ImageIcon blackRook = loadScaledIcon("black-rook.png");
    //For the knights
    private final ImageIcon whiteKnight = loadScaledIcon("white-knight.png");
    private final ImageIcon blackKnight = loadScaledIcon("black-knight.png");
    //For the bishops
    private final ImageIcon whiteBishop = loadScaledIcon("white-bishop.png");
    private final ImageIcon blackBishop = loadScaledIcon("black-bishop.png");
    //For the kings
    private final ImageIcon whiteKing = loadScaledIcon("white-king.png");
    private final ImageIcon blackKing = loadScaledIcon("black-king.png");
    //For the queens
    private final ImageIcon whiteQueen = loadScaledIcon("white-queen.png");
    private final ImageIcon blackQueen = loadScaledIcon("black-queen.png");


    private void loadChessIcons(){
        
        //For the a1 - h1
        grid[0][0].setIcon(whiteRook);      this.pos[0][0] = this.pos[0][0].substring(0,3) + "wr";
        grid[0][1].setIcon(whiteKnight);    this.pos[0][1] = this.pos[0][1].substring(0,3) + "wkn";
        grid[0][2].setIcon(whiteBishop);    this.pos[0][2] = this.pos[0][2].substring(0,3) + "wb";
        grid[0][3].setIcon(whiteQueen);     this.pos[0][3] = this.pos[0][3].substring(0,3) + "wq";
        grid[0][4].setIcon(whiteKing);      this.pos[0][4] = this.pos[0][4].substring(0,3) + "wk";
        grid[0][5].setIcon(whiteBishop);    this.pos[0][5] = this.pos[0][5].substring(0,3) + "wb";
        grid[0][6].setIcon(whiteKnight);    this.pos[0][6] = this.pos[0][6].substring(0,3) + "wkn";
        grid[0][7].setIcon(whiteRook);      this.pos[0][7] = this.pos[0][7].substring(0,3) + "wr";

        //For a7 -h7
        grid[7][0].setIcon(blackRook);      this.pos[7][0] = this.pos[7][0].substring(0,3) + "br";
        grid[7][1].setIcon(blackKnight);    this.pos[7][1] = this.pos[7][1].substring(0,3) + "bkn";
        grid[7][2].setIcon(blackBishop);    this.pos[7][2] = this.pos[7][2].substring(0,3) + "bb";
        grid[7][3].setIcon(blackQueen);     this.pos[7][3] = this.pos[7][0].substring(0,3) + "bq";
        grid[7][4].setIcon(blackKing);      this.pos[7][4] = this.pos[7][4].substring(0,3) + "bk";
        grid[7][5].setIcon(blackBishop);    this.pos[7][5] = this.pos[7][5].substring(0,3) + "bb";
        grid[7][6].setIcon(blackKnight);    this.pos[7][6] = this.pos[7][6].substring(0,3) + "bkn";
        grid[7][7].setIcon(blackRook);      this.pos[7][7] = this.pos[7][7].substring(0,3) + "br";

        
        for (int i = 0; i < 8; i++){
            this.pos[1][i] = this.pos[1][i].substring(0,3) + "wp";
            grid[1][i].setIcon(whitePawn);  
            this.pos[6][i] = this.pos[6][i].substring(0,3) + "bp";
            grid[6][i].setIcon(BlackPawn);
        }
    }
    public void initBoard(){
        createBoard();
    }
}