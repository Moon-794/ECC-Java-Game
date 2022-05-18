import javax.swing.*;
import java.awt.*;

public class Board extends JPanel
{
    private final Game game;

    private final int SCREEN_WIDTH = 12, SCREEN_HEIGHT = 10;
    private final int TILE_SIZE = 64;

    public Board(Game game)
    {
        this.game = game;
        SetupWindow();
    }

    public void SetupWindow()
    {
        //Create Window
        JFrame window = new JFrame();
        //Exit program when window is closed.
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Disable resizing the screen and set a fixed size
        window.setResizable(false);
        window.setSize(SCREEN_WIDTH * TILE_SIZE + 16, SCREEN_HEIGHT * TILE_SIZE + 39);

        //Show window
        window.add(this);
        window.addKeyListener(new Input());
        window.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        switch (Game.instance.gameState)
        {
            case Introduction: DrawIntro(g);   break;
            case Running:      DrawRunning(g); break;
            case GameOver:     DrawDefeat(g);  break;
            case Victory:      DrawVictory(g); break;
        }

    }

    public void DrawIntro(Graphics g)
    {
        //Game.instance.getClass().getResource("Intro.png");
        g.drawImage(new ImageIcon(Game.instance.getClass().getResource("Sprites/Intro.png")).getImage(), 0, 0, null);
    }

    public void DrawRunning(Graphics g)
    {
        g.drawImage(Game.instance.currentScreen.sprite.getImage(), 0, 0, null);

        for (Entity e: game.currentScreen.entities)
        {
            if(e.sprite != null && e.isVisible)
                g.drawImage(e.sprite.getImage(), e.x, e.y, null);
        }

        g.drawImage(game.player.sprite.getImage(), game.player.x, game.player.y, null);

        g.drawImage(new ImageIcon(Game.instance.getClass().getResource("Sprites/Vignette.png")).getImage(), 0, 0, null);
    }

    public void DrawVictory(Graphics g)
    {
        g.drawImage(new ImageIcon(Game.instance.getClass().getResource("Sprites/Win.png")).getImage(), 0, 0, null);
    }

    public void DrawDefeat(Graphics g)
    {
        g.drawImage(new ImageIcon(Game.instance.getClass().getResource("Sprites/Defeat.png")).getImage(), 0, 0, null);
    }
}
