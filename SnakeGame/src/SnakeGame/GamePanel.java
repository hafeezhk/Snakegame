package SnakeGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private int snakeXLength[] = new int[750];
    private int snakeYLength[] = new int[750];
    private int[] xPos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] yPos={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
    private Random random = new Random();
    private int foodX,foodY;
    private int lengthOfSnake = 3;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean gameOver = false;

    int moves = 0;
    int score =0;

    private ImageIcon snaketitle = new ImageIcon(getClass().getResource("snakeGameTitle.jpeg"));
    private ImageIcon leftMouth = new ImageIcon(getClass().getResource("leftMouth.jpeg"));
    private ImageIcon rightMouth = new ImageIcon(getClass().getResource("rightMouth.jpeg"));
    private ImageIcon upMouth = new ImageIcon(getClass().getResource("upMouth.jpeg"));
    private ImageIcon downMouth = new ImageIcon(getClass().getResource("downMouth.jpeg"));
    private ImageIcon snakeImage = new ImageIcon(getClass().getResource("snakeImage.jpeg"));
    private ImageIcon enemy = new ImageIcon(getClass().getResource("enemy .jpeg"));
    private Timer timer;
    private int delay = 100;

    GamePanel() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeys(true);

        timer = new Timer(delay, this);
        timer.start();
        newFood();

    }

    private void newFood() {
        foodX=xPos[random.nextInt(34)];
        foodY=yPos[random.nextInt(23)];
        for(int i=lengthOfSnake-1;i>=0;i--){
            if(snakeXLength[i]==foodX&&snakeYLength[i]==foodY){
                newFood();
            }
        }
    }

    private void setFocusTraversalKeys(boolean b) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.drawRect(24, 10, 851, 55);
        g.drawRect(24, 74, 851, 576);
        snaketitle.paintIcon(this, g, 25, 11);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        if (moves == 0) {
            snakeXLength[0] = 100;
            snakeXLength[1] = 75;
            snakeXLength[2] = 50;
            snakeYLength[0] = 100;
            snakeYLength[1] = 100;
            snakeYLength[2] = 100;
            moves++;
        }
        if (left) {
            leftMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        }
        if (right) {
            rightMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        }
        if (up) {
            upMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        }
        if (down) {
            downMouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);

        }
        for (int i = 0; i < lengthOfSnake; i++) {
            snakeImage.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
        }
        enemy.paintIcon(this,g,foodX,foodY);
        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,30));
            g.drawString("Game Over",300,300);
            g.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,10));
            g.drawString("Press Space to restart",330,360);

        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial",Font.PLAIN,15));
        g.drawString("Score : "+score,750,30);
        g.drawString("Length: "+lengthOfSnake,750,50);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = lengthOfSnake - 1; i > 0; i--) {
            snakeXLength[i] = snakeXLength[i - 1];
            snakeYLength[i] = snakeYLength[i - 1];
        }
        if (left) {
            snakeXLength[0] = snakeXLength[0] - 25;
        }
        if (right) {
            snakeXLength[0] = snakeXLength[0] + 25;
        }
        if (up) {
            snakeYLength[0] = snakeYLength[0] - 25;
        }
        if (down) {
            snakeYLength[0] = snakeYLength[0] + 25;
        }
        if (snakeXLength[0] > 850) {
            snakeXLength[0] = 25;
        }
        if (snakeXLength[0] < 25) {
            snakeXLength[0] = 850;
        }
        if (snakeYLength[0] > 625) {
            snakeYLength[0] = 75;
        }
        if (snakeYLength[0] < 75) {
            snakeYLength[0] = 625;
        }
        collidesWithBody();
        collidesWithEnemy();
        repaint();
    }

    private void collidesWithBody() {
        for (int i = lengthOfSnake-1; i >0 ; i--) {
            if (snakeXLength[i]==snakeXLength[0]&&snakeYLength[i]==snakeYLength[0]){
                timer.stop();
                gameOver=true;
            }

        }
    }

    private void collidesWithEnemy() {
        if(snakeXLength[0]==foodX&&snakeYLength[0]==foodY){
            newFood();
            lengthOfSnake++;
            snakeXLength[lengthOfSnake-1]=snakeXLength[lengthOfSnake-2];
            snakeYLength[lengthOfSnake-1]=snakeYLength[lengthOfSnake-2];

            score++;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&gameOver){
            restart();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT &&(!right)) {
            left = true;
            right = false;
            down = false;
            up = false;
            moves++;
        }

    if(e.getKeyCode()==KeyEvent.VK_RIGHT&&(!left))

    {
        left = false;
        right = true;
        down = false;
        up = false;
        moves++;

    }
        if(e.getKeyCode()==KeyEvent.VK_UP&&(!down))

        {
            left = false;
            right = false;
            down = false;
            up = true;
            moves++;

        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN&&(!up))

        {
            left = false;
            right = false;
            down = true;
            up = false;
            moves++;

        }

}

    private void restart() {
        gameOver=false;
        moves=0;
        score=0;
        lengthOfSnake=3;
        left=false;
        right=true;
        up=false;
        down=false;
        timer.start();
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
