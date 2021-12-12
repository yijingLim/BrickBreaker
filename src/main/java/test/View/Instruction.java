package test.View;

import test.Controller.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Instruction class to display guideline of games
 */
public class Instruction extends JComponent implements MouseListener, MouseMotionListener {

    private static final String BACK_TEXT = "Back";
    private static final String START_TEXT = "Start";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(3, 1, 64);
    private static final Color BG_COLOR = new Color(113, 109, 248);
    private static final Color CLICKED_BUTTON_COLOR = Color.WHITE.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;


    private final GameFrame owner;

    private Rectangle BackButton;
    private Rectangle startButton;

    private boolean startClicked;
    private boolean backClicked;

    private Font instructionFont;


    /**
     * @param owner
     * Instruction class to displau instruction and information before game
     */
    public Instruction(GameFrame owner){
        instructionFont = new Font("TimesRoman",Font.CENTER_BASELINE,TEXT_SIZE);

        this.owner = owner;
        this.initialize();


    }
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){
        Graphics2D g2d =(Graphics2D) g;
        setbackground(g2d);

        drawText(g2d);
        drawButton(g2d);


    }

    /**
     * Set background of the instruction page
     */
    private void setbackground(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }


    /**
     * Write and display the text on the instruction page
     */
    public void drawText(Graphics2D g2d){

        g2d.setFont(instructionFont);
        g2d.setColor(MENU_COLOR);

        g2d.drawString("<Instruction>",180,50);
        g2d.drawString("How to Play",190,80);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        drawString(g2d, "Welcome to the game !\nPRESS A & D to move left & right\nPRESS SPACE to Pause the game", 80, 100);

    }

    private void drawString(Graphics g, String text, int x, int y) {
        int lineHeight = g.getFontMetrics().getHeight();
        for (String line : text.split("\n"))
            g.drawString(line, x, y += lineHeight);
    }


    /**
     * Draw the start and back button on instruction page
     */
    public void drawButton(Graphics2D g2d){

        Dimension btnDim = new Dimension(DEF_WIDTH/5, DEF_HEIGHT / 10);
        startButton = new Rectangle(btnDim);
        BackButton = new Rectangle(btnDim);

        startButton.setLocation(DEF_WIDTH/6*4,DEF_HEIGHT /6*5);

        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,DEF_WIDTH/10*7,DEF_HEIGHT/10*9);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,DEF_WIDTH/10*7,DEF_HEIGHT/10*9);
        }

        BackButton.setLocation(DEF_WIDTH/6,DEF_HEIGHT /6*5);

        if(backClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(BackButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,DEF_WIDTH/5,DEF_HEIGHT/10*9);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(BackButton);
            g2d.drawString(BACK_TEXT,DEF_WIDTH/5,DEF_HEIGHT/10*9);
        }


    }

    /**
     * When mouse is click, the respective page is enabled to display
     */
        @Override
    public void mouseClicked(MouseEvent e) {
            Point p = e.getPoint();
            if(startButton.contains(p)){
                owner.enableGameBoard();

            }
            else if(BackButton.contains(p)){
                owner.enableHomeMenu();
            }
    }


    /**
     * When mouse is pressed, the repaint the button when it is clicked
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(BackButton.contains(p)){
            backClicked = true;
            repaint(BackButton.x,BackButton.y,BackButton.width+1,BackButton.height+1);

        }

    }
    @Override
    public void mouseReleased(MouseEvent e) {
            if(startClicked ){
                startClicked = false;
                repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
            }
            else if(backClicked){
                backClicked = false;
                repaint(BackButton.x,BackButton.y,BackButton.width+1,BackButton.height+1);

            }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(startButton.contains(p) || BackButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
