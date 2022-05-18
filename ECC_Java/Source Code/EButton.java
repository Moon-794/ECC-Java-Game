import javax.swing.*;

public class EButton extends Entity
{

    public boolean buttonState = false;
    private ImageIcon buttonOff, buttonOn;

    public EButton(int x, int y)
    {
        super(x, y);

        playerCollision = true;

        buttonOff = new ImageIcon(Game.instance.getClass().getResource("Sprites/Button_Off.png"));
        buttonOn = new ImageIcon(Game.instance.getClass().getResource("Sprites/Button_On.png"));

        sprite = buttonOff;
    }

    @Override
    public void Update()
    {
        super.Update();

    }

    private boolean preCollisionCheck;

    @Override
    public void OnPlayerCollide()
    {
        if(!preCollisionCheck)
        {
            SwitchButtonState();
            preCollisionCheck = true;
        }
    }

    @Override
    public void OnPlayerNoCollide()
    {
        if(preCollisionCheck)
        {
            SwitchButtonState();
            preCollisionCheck = false;
        }
    }

    public void SwitchButtonState()
    {
        buttonState = !buttonState;

        if(buttonState)
            ButtonOn();
        else
            ButtonOff();
    }

    public void ButtonOff()
    {
        sprite = buttonOff;
    }

    public void ButtonOn()
    {
        sprite = buttonOn;
    }
}
