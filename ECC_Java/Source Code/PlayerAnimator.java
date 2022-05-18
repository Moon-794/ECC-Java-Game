import javax.swing.*;

public class PlayerAnimator extends Animator
{
    ImageIcon[] playerRight;
    ImageIcon[] playerLeft;

    Player player;

    public PlayerAnimator(Player player, double frameRate)
    {
        super(frameRate);
        this.player = player;

        playerRight = Animator.GenerateSpriteArray("Player", 1);
        playerLeft = Animator.GenerateSpriteArray("Player_2", 1);

        currentState = playerRight;

        Tick();
    }

    @Override
    public void Update()
    {
        super.Update();

       if(player.velocityX > 0)
            currentState = playerRight;

        if(player.velocityX < 0)
            currentState = playerLeft;

    }

    @Override
    public void Tick()
    {
        player.sprite = currentState[currentFrame];

        currentFrame++;

        if(currentFrame == currentState.length)
            currentFrame = 0;
    }
}
