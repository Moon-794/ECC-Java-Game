public class EDoor extends Entity
{

    private EButton[] buttons;

    public EDoor(int x, int y, int sizeX, int sizeY, String filename, EButton[] buttons)
    {
        super(x, y, sizeX, sizeY, filename);

        stopsPlayer = true;
        this.buttons = buttons;
    }

    @Override
    public void Update()
    {
        super.Update();

        for (EButton b: buttons)
        {
            if(!b.buttonState)
                return;
        }

        stopsPlayer = false;
        isVisible = false;
    }
}
