import javax.swing.*;

public abstract class Entity
{
    //Position
    public String collisionTag;

    public int x, y;
    public int sizeX, sizeY;

    public ImageIcon sprite;

    public boolean isVisible = true;
    public boolean flagDestroy;

    public Entity(int x, int y, String fileName)
    {
        this.x = x;
        this.y = y;

        sprite = new ImageIcon(Game.instance.getClass().getResource("Sprites/".concat(fileName).concat(".png")));
    }

    public Entity(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Entity(int x, int y, int sizeX, int sizeY, String fileName)
    {
        this.x = x;
        this.y = y;

        this.sizeX = sizeX * 64;
        this.sizeY = sizeY * 64;

        sprite = new ImageIcon(Game.instance.getClass().getResource("Sprites/".concat(fileName).concat(".png")));
    }

    public boolean worldCollision, playerCollision, stopsPlayer, entityCollision;

    public void Update()
    {
        //Perform Entity Logic Here
        if(worldCollision)
            WorldCollision();

        if(playerCollision)
            PlayerCollision();

        if(entityCollision)
            EntityCollision();
    }

    public void EntityCollision()
    {
        for (Entity e: Game.instance.currentScreen.entities)
        {
            if(e.collisionTag != "")
            {
                if(Physics.BoxCollision(x, y, e.x, e.y, 64, 64, 64, 64))
                    OnEntityCollide(e.collisionTag);
            }
        }
    }

    public void OnEntityCollide(String collisionTag)
    {

    }

    public void WorldCollision()
    {
        for (Tile t: Game.instance.currentScreen.tiles)
        {
            if(Physics.BoxCollision(x, y, t.x, t.y))
                OnWorldCollide();
        }
    }

    public void PlayerCollision()
    {
        if(Physics.BoxCollision(x, y, Game.instance.player.x, Game.instance.player.y))
            OnPlayerCollide();
        else
            OnPlayerNoCollide();
    }

    public void OnWorldCollide()
    {
        //What should the entity do if it bumps into the walls?
    }

    public void OnPlayerCollide()
    {
        //What should the entity do if it hits the player?
    }

    public void OnPlayerNoCollide()
    {
        //Run when entity is not colliding with player
    }
}
