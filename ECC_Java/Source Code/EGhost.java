public class EGhost extends Entity
{

    int ticks;

    public EGhost(int x, int y)
    {
        super(x, y, "Ghost");
        entityCollision = true;
        playerCollision = true;
    }

    @Override
    public void Update()
    {
        super.Update();

        ticks++;

        if(ticks > 3)
        {
            ticks = 0;

            if(y < Game.instance.player.y - 16)
            {
                y++;
            }
            else if (y > Game.instance.player.y - 16)
            {
                y--;
            }

            if(x < Game.instance.player.x - 16)
            {
                x++;
            }
            else if (x > Game.instance.player.x - 16)
            {
                x--;
            }
        }

    }

    @Override
    public void OnEntityCollide(String collisionTag)
    {
        if(collisionTag == "Projectile")
            flagDestroy = true;
    }

    @Override
    public void OnPlayerCollide()
    {
         Game.instance.gameState = GameState.GameOver;
    }
}
