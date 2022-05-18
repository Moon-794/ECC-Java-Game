public class EProjectile extends Entity
{
    int velX, velY;

    public EProjectile(int x, int y, int velX, int velY)
    {
        super(x + 16, y + 16, "Projectile");

        if(velX == 0 && velY == 0)
        {
            this.velX = 4;
            this.velY = 0;
        }
        else
        {
            this.velX = velX;
            this.velY = velY;
        }

        collisionTag = "Projectile";

        worldCollision = true;
    }

    @Override
    public void Update()
    {
        super.Update();

        x += velX;
        y += velY;
    }

    @Override
    public void WorldCollision()
    {
        for (Tile t: Game.instance.currentScreen.tiles)
        {
            if(Physics.BoxCollision(x, y, t.x, t.y, 32, 32, 64, 64))
                OnWorldCollide();
        }
    }

    @Override
    public void OnWorldCollide()
    {
        super.OnWorldCollide();
        flagDestroy = true;
    }
}
