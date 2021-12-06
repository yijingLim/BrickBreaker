/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.io.*;
import java.util.Scanner;


public class GameBoard<scores> extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String MAINMENU = "Main Menu";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(10, 209, 245);


    private static final int DEF_WIDTH = 600;
    public static final int DEF_HEIGHT = 450;
    public static final int FIRSTPLACE_HEIGHT = 150;

    private static final Color BG_COLOR = new Color(244, 244, 245);
    private static final Color LEADERBOARDBG_COLOR = new Color(114, 114, 212);

    private Timer gameTimer;

    private Wall wall;

    private String message;
    private String Level;
    private String Start;

    private boolean showPauseMenu;

    private Font menuFont;
    private Font LevelBarFont;
    private Font LeaderboardFont;
    private Rectangle PauseButton;
    private boolean PauseClicked;

    private Rectangle continueButtonRect;
    private Rectangle MainMenuButtonRect;
    private Rectangle restartButtonRect;
    private Rectangle exitButtonRect;
    private int strLen;
    private GameFrame owner;

    private DebugConsole debugConsole;

    SortHighScore highScore;
    private boolean NewHighScore = false;

    public int score1;
    public int score2;
    public int score3;
    private File file;
    private String name1;
    private String name2;
    private String name3;

    public GameBoard(GameFrame owner){

        super();
        this.owner=owner;
        createFile();
        readFile(file);
        strLen = 0;
        showPauseMenu = false;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);
        LevelBarFont = new Font("Noto Mono",Font.BOLD,25);
        LeaderboardFont = new Font("Noto Mono",Font.BOLD,30);

        this.initialize();
        message = "";
        Start = "Enter to Start";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,380));

        debugConsole = new DebugConsole(owner,wall,this);

        //initialize the first level
        wall.nextLevel();
        wall.resetPlayer();


        Level = String.format("Level %d", wall.getLevelCount());

        gameTimer = new Timer(10,e ->{

            wall.move();
            wall.findImpacts();

            wall.findImpacts1();
            wall.PowerDropDown();


            Start = "";
            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            Level = String.format("Level %d", wall.getLevelCount());

            if(wall.isBallLost()){
                if(wall.ballEnd()) {
                    wall.wallReset();
                    wall.resetPlayer();
                    message = "Game over";
                    highScore = new SortHighScore(wall.Score,score1,score2,score3, name1, name2, name3);
                    NewHighScore = true;
                    writeFile(file);
                    wall.Score = 0;
                }
                wall.ballReset();
                gameTimer.stop();
                readFile(file);
            }
            else if(wall.isDone()) {
                if (wall.hasLevel()) {
                    message = "Go to Next Level";
                    gameTimer.stop();
                    wall.getBallExtraPoint();
                    if (wall.getBallExtraPoint() != 0) {
                        wall.Score += wall.getBallExtraPoint();
                        System.out.println("My Total Score :" + wall.Score);
                        wall.Score = 0;
                    }
                    wall.ballReset();
                    wall.resetPlayer();
                    wall.wallReset();
                    wall.nextLevel();

                } else {
                    message = "ALL WALLS DESTROYED";
                    wall.getBallExtraPoint();
                    if (wall.getBallExtraPoint() != 0) {
                        wall.Score += wall.getBallExtraPoint();
                        System.out.println("My Total Score :" + wall.Score);
                        wall.Score = 0;
                    }
                    gameTimer.stop();
                }
                highScore = new SortHighScore(wall.Score,score1,score2,score3, name1, name2, name3);
                NewHighScore = true;
                writeFile(file);
                wall.Score = 0;

            }

            repaint();
        });

    }


    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        drawBall(wall.ball,g2d);
            if(wall.extraball!=null){
                drawBall(wall.extraball, g2d);// the extra ball
            }

        for(Brick b : wall.bricks)
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.player,g2d);
        drawLevelBar(g2d);

        wall.drawSpecialCharacteristic(g2d);
        if(NewHighScore == true){
            drawLeaderboardBoard(g2d);
        }

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());

        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
        g2d.drawString(Start, 270, 360);

    }

    private void drawLevelBar(Graphics2D g2d){//putting game board on the below of the page

        g2d.setColor(Color.blue);
        Stroke stroke1 = new BasicStroke(2f);
        g2d.setStroke(stroke1);

        g2d.setColor(Color.black);
        g2d.fillRect(0, 400, 630, 50);

        g2d.setColor(Color.BLUE);
        g2d.setFont(LevelBarFont);
        g2d.drawString(message,200,435);
        g2d.drawString(Level ,20,435);

        g2d.setColor(Color.BLUE);
        drawPauseButton(g2d);

    }
    private void drawPauseButton(Graphics2D g2d){
        Dimension btnDim = new Dimension(DEF_WIDTH/6, DEF_HEIGHT/13);
        PauseButton = new Rectangle(btnDim);
        PauseButton.setLocation(470, 410);

        if(PauseClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(Color.WHITE.brighter());
            g2d.draw(PauseButton);
            g2d.setColor(Color.white);
            g2d.setFont(LevelBarFont);
            g2d.drawString("PAUSE",475,435);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(PauseButton);
            g2d.drawString("PAUSE",475,435);
        }

    }

    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.70f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 1.9;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 1.5;

        if(MainMenuButtonRect == null){
            MainMenuButtonRect = (Rectangle) continueButtonRect.clone();
            MainMenuButtonRect.setLocation(x,y-MainMenuButtonRect.height);
        }

        g2d.drawString(MAINMENU,x,y);

        y *= 1.3;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);


        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public void createFile(){
        try {
            File file = new File("src\\Resources\\HighScore.txt");
            this.file = file;
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeFile(File file){
        try {
            FileWriter myWriter = new FileWriter("src\\Resources\\HighScore.txt");
            myWriter.write(highScore.HighScore);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void readFile(File file){
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String data1[] = data.split(" ");

                String V1[] = data1[0].split(",");
                this.score1 = Integer.parseInt(V1[0]);
                this.name1 = V1[1];
                String V2[] = data1[1].split(",");
                this.score2 = Integer.parseInt(V2[0]);
                this.name2 = V2[1];
                String V3[] = data1[2].split(",");
                this.score3 = Integer.parseInt(V3[0]);
                this.name3 = V3[1];
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void drawLeaderboardBoard(Graphics2D g2d){
        setLeaderBoardBackground(g2d);

        g2d.setColor(Color.white);
        g2d.setFont(LeaderboardFont);
        g2d.drawString("LeaderBoard",200,50);
        g2d.drawString("Rank",50,100);
        g2d.drawString("Name",180,100);
        g2d.drawString("Score",450,100);
        g2d.drawString("1ST", 50, FIRSTPLACE_HEIGHT);
        g2d.drawString(SplitgetName(highScore.firstPlace), 180, FIRSTPLACE_HEIGHT);
        g2d.drawString(SplitgetScore(highScore.firstPlace), 450, FIRSTPLACE_HEIGHT);

        int y = FIRSTPLACE_HEIGHT+50;
        g2d.drawString("2ND", 50, y);
        g2d.drawString(SplitgetName(highScore.secondPlace), 180, y);
        g2d.drawString(SplitgetScore(highScore.secondPlace), 450, y);

        int y1 = FIRSTPLACE_HEIGHT+100;
        g2d.drawString("3RD", 50, y1);
        g2d.drawString(SplitgetName(highScore.thirdPlace), 180, y1);
        g2d.drawString(SplitgetScore(highScore.thirdPlace), 450, y1);

    }
    private String SplitgetName(String Place){
        String Name = Place.split(",")[1];
        return Name;
    }
    private String SplitgetScore(String Place){
        String Score = Place.split(",")[0];
        return Score;
    }


    private void setLeaderBoardBackground(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(LEADERBOARDBG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }



    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                wall.player.moveLeft();
                break;
            case KeyEvent.VK_D:
                wall.player.movRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.player.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.player.stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(PauseButton.contains(p)){
            showPauseMenu = !showPauseMenu;
            repaint();
            gameTimer.stop();
        }

        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            wall.ballReset();
            wall.wallReset();
            showPauseMenu = false;
            repaint();
        }
        else if(MainMenuButtonRect.contains(p)){
            showPauseMenu =false;
            owner.enableHomeMenu();
        }
        else if(exitButtonRect.contains(p)){
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(PauseButton.contains(p)){
            PauseClicked = true;
            repaint(PauseButton.x,PauseButton.y,PauseButton.width+1,PauseButton.height+1);

        }

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(PauseClicked){
            PauseClicked = false;
            repaint(PauseButton.x,PauseButton.y,PauseButton.width+1,PauseButton.height+1);
        }

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(MainMenuButtonRect != null && showPauseMenu) {
            if (MainMenuButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus(){
        gameTimer.stop();
        message = "Focus Lost";
        repaint();
    }

}
