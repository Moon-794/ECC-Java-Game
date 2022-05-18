import javax.swing.*;

public class Player
{
    //Player coordinates
    public int x, y;
    public int velocityX, velocityY;

    public int dirX, dirY;

    private final double fireRate = 0.4f;
    private double fireTimer = 0.0f;

    int frameRate = 0;

    PlayerAnimator animator;
    public ImageIcon sprite;

    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;

        animator = new PlayerAnimator(this, 0f);
        animator.Play();
    }

    //Logic Loop
    public void Tick()
    {
        //Move player & check for collisions
        MovePlayer();

        animator.Update();

        FireWeapon();
    }

    private void FireWeapon()
    {
        frameRate++;

        //Basic Timer
        if(fireTimer >= fireRate)
        {
            //Timer is ready, wait for player to shoot
            if(Input.spacePressed)
            {
                fireTimer = 0;

                EProjectile projectile;

                if(velocityX == 0 && velocityY == 0)
                {
                    projectile = new EProjectile(x, y, dirX, dirY);
                }
                else
                {
                    projectile = new EProjectile(x, y, velocityX * 2, velocityY * 2);
                }

                Game.instance.currentScreen.entities.add(projectile);
            }

            frameRate = 0;
        }
        else
        {
            //Keep waiting for timer to be ready
            fireTimer += Time.deltaTime;
        }
    }

    private void MovePlayer()
    {
        velocityX = 0;
        velocityY = 0;

        //Direction of movement this tick according to currently held buttons
        velocityY += (Input.upPressed) ? -2 : 0;
        velocityY += (Input.downPressed) ? 2 : 0;

        velocityX += (Input.rightPressed) ? 2 : 0;
        velocityX += (Input.leftPressed) ? -2 : 0;

        for (Tile t: Game.instance.currentScreen.tiles)
        {
            if(Physics.BoxCollision(x + velocityX, y + velocityY, t.x, t.y))
            {
                if(Physics.BoxCollision(x, y + velocityY, t.x, t.y))
                    velocityY = 0;

                if(Physics.BoxCollision(x + velocityX, y, t.x, t.y))
                    velocityX = 0;
            }
        }

        for (Entity e: Game.instance.currentScreen.entities)
        {
            if(e.stopsPlayer)
            {
                if (Physics.BoxCollision(x + velocityX, y + velocityY,  e.x, e.y, 64, 64, e.sizeX, e.sizeY))
                {
                    if (Physics.BoxCollision(x, y + velocityY,  e.x, e.y, 64, 64, e.sizeX, e.sizeY))
                        velocityY = 0;

                    if (Physics.BoxCollision(x + velocityX, y,  e.x, e.y, 64, 64, e.sizeX, e.sizeY))
                        velocityX = 0;
                }
            }
        }

        y += velocityY;
        x += velocityX;

        if(x < 0)
            ScreenTransition(704, y, -1, 0);

        if(x > 704)
            ScreenTransition(0, y, 1, 0);

        if(y < 0)
            ScreenTransition(x, 576, 0, -1);

        if(y > 576)
            ScreenTransition(x, 0, 0, 1);


        if(velocityY > 0)
        {
            dirY = 4;
            dirX = 0;
        }

        if(velocityY < 0)
        {
            dirY = -4;
            dirX = 0;
        }

        if(velocityX > 0)
        {
            dirX = 4;
            dirY = 0;
        }

        if(velocityX < 0)
        {
            dirX = -4;
            dirY = 0;
        }

    }

    public void ScreenTransition(int newPositionX, int newPositionY, int x, int y)
    {
        var game = Game.instance;
        game.currentScreen = game.screens[game.currentScreen.x + x][game.currentScreen.y + y];

        this.x = newPositionX;
        this.y = newPositionY;
    }
}
