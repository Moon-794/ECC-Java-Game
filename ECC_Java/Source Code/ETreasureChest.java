public class ETreasureChest extends Entity
{
    public ETreasureChest(int x, int y, String fileName)
    {
        super(x, y, fileName);

        playerCollision = true;
    }

    @Override
    public void OnPlayerCollide()
    {
        super.OnPlayerCollide();
        Game.instance.gameState = GameState.Victory;
    }
}
