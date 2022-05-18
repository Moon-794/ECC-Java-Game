import javax.swing.*;

public abstract class Animator
{
    public Entity entity;

    public double frameRate;
    public double nextFrameProgress = 0.0f;

    public ImageIcon[] currentState;
    public int currentFrame;

    public boolean isPlaying;

    public Animator(double frameRate)
    {
        this.frameRate = frameRate;
    }

    public void Update()
    {
        if(nextFrameProgress >= frameRate && isPlaying)
        {
            Tick();
            nextFrameProgress = 0;
        }
        else
        {
            nextFrameProgress += Time.deltaTime;
        }
    }

    public void Tick()
    {
        entity.sprite = currentState[currentFrame];

        currentFrame++;

        if(currentFrame == currentState.length)
            currentFrame = 0;
    }

    public void Play()
    {
        isPlaying = true;
    }

    public void Pause()
    {
        isPlaying = false;
    }

    public void Reset()
    {
        currentFrame = 0;
    }

    public static ImageIcon[] GenerateSpriteArray(String entityName, int frameCount)
    {
        ImageIcon[] sprites = new ImageIcon[frameCount];

        for (int i = 0; i < frameCount; i++)
        {
            sprites[i] = new ImageIcon(Game.instance.getClass().getResource("Sprites/" + entityName + "/" + i + ".png"));
        }

        return sprites;
    }
}
