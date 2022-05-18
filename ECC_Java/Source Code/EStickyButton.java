public class EStickyButton extends EButton
{

    public EStickyButton(int x, int y)
    {
        super(x, y);
    }

    @Override
    public void SwitchButtonState()
    {
        buttonState = true;
        ButtonOn();
    }
}
