import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener
{
    public static boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    @Override
    public void keyTyped(KeyEvent e)
    {
        //Not used
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyChar() == 'w')
            upPressed = true;

        if(e.getKeyChar() == 'a')
            leftPressed = true;

        if(e.getKeyChar() == 's')
            downPressed = true;

        if(e.getKeyChar() == 'd')
            rightPressed = true;

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = true;

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(e.getKeyChar() == 'w')
            upPressed = false;

        if(e.getKeyChar() == 'a')
            leftPressed = false;

        if(e.getKeyChar() == 's')
            downPressed = false;

        if(e.getKeyChar() == 'd')
            rightPressed = false;

        if(e.getKeyCode() == KeyEvent.VK_SPACE)
            spacePressed = false;

    }
}
