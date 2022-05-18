import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Defines dungeon rooms, entities are per-screen and are stored here in an arrayList
public class Screen
{
    public int x, y;

    public ImageIcon sprite;

    public ArrayList<Tile> tiles = new ArrayList<Tile>();
    public ArrayList<Entity> entities = new ArrayList<Entity>();

    public Screen(int xPos, int yPos)
    {
        x = xPos;
        y = yPos;
    }

    public void UpdateEntities()
    {
        //Loop through every entity existing on the current screen and "Tick" their logic
        for (int i = 0; i < entities.size(); i++)
        {
            entities.get(i).Update();

            //If an entity is flagged for deletion, remove it now.
            //This is done here so each entity does not need to be aware of its screen.
            //Makes things a bit cleaner.
            if(entities.get(i).flagDestroy)
                entities.remove(i);
        }
    }

    public static Screen[][] GenerateWorldMap(int WIDTH, int HEIGHT)
    {
        //This function is used in setup to take 2 images, a render map and a collision map
        //The render map will be full size tiles, whereas each tile on the collision map will be 1 pixel
        Screen[][] worldMap = new Screen[WIDTH][HEIGHT];

        BufferedImage renderImage;
        BufferedImage bufferedCollisionImage;

        //File exception handling
        try
        {
            renderImage = ImageIO.read(Game.instance.getClass().getResource("Sprites/RenderMap.png"));
            bufferedCollisionImage = ImageIO.read(Game.instance.getClass().getResource("Sprites/CollisionMap.png"));
        }
        catch (IOException e)
        {
            System.out.println("couldnt find ");
            return null;
        }

        //Loop through WIDTH x HEIGHT to deal with each image set separately.
        for (int w = 0; w < WIDTH; w++)
        {
            for (int h = 0; h < HEIGHT; h++)
            {
                //Create new screen
                worldMap[w][h] = new Screen(w, h);

                BufferedImage image = new BufferedImage(64 * 12, 64 * 10, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics = (Graphics2D) image.getGraphics();

                //Looking at this 12x10 screen, loop through each tile on the collision map (also 12*10) and get data
                for (int x = 0; x < 12; x++)
                {
                    for (int y = 0; y < 10; y++)
                    {
                        //Get tile collision info from bufferedCollisionImage
                        int collisionPixel = bufferedCollisionImage.getRGB(x + (w * 12), y + (h * 10));
                        Color c = new Color(collisionPixel, true);

                        //Map red channel to 0 - 1 Range
                        //Round value to get either 0 or 1, floor or wall

                        double redNormal = c.getRed() / 255f;
                        int tileValue = (int)Math.round(redNormal);

                        //Add tile if the pixel is black i.e a wall
                        if(tileValue != 1)
                            worldMap[w][h].tiles.add(new Tile(x * 64, y * 64));

                        //------------------------------------------------------------------------
                        //Create a procedural image using the pixel data from the RenderMap

                        //NOTE: Whilst each tile is 64x64 pixels, I painted them at a 8x8 resolution, so 8 is a fine detail level if needed
                        for (int i = 0; i < 64; i++)
                        {
                            for (int j = 0; j < 64; j++)
                            {
                                int pixel = renderImage.getRGB(i + (x + (w * 12)) * 64, j + (y + (h * 10)) * 64);

                                graphics.setColor(new Color(pixel, true));
                                graphics.fillRect(i + (x * 64), j + (y * 64), 1, 1);
                            }
                        }

                        worldMap[w][h].sprite = new ImageIcon(image);
                    }
                }
            }
        }

        return worldMap;
    }
}
