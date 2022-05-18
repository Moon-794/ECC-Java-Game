public class Game
{
    //Singleton :(
    public static Game instance;
    public GameState gameState = GameState.Introduction;

    //Window
    Board gameBoard;

    //Game World
    Screen[][] screens;
    Screen currentScreen;

    //Player
    Player player;

    public Game()
    {
        Setup();
    }

    //Initial logic setup, managers and whatnot
    public void Setup()
    {
        //Setup singleton
        Game.instance = this;

        //Create player
        player = new Player(512, 400);

        System.out.println("About to make screenage happen");
        screens = Screen.GenerateWorldMap(3, 3);

        if (screens != null)
        {
            currentScreen = screens[0][0];
        }

        PopulateWorldScreens();

        //Lastly, setup window
        gameBoard = new Board(this);

        Update();
    }

    private void PopulateWorldScreens()
    {
        //Screen 0_0
        EButton b = new EStickyButton(10*64, 2 * 64);
        EButton b2 = new EStickyButton(10 * 64, 7 * 64);

        screens[0][0].entities.add(b);
        screens[0][0].entities.add(b2);
        screens[0][0].entities.add(new EDoor(704, 256, 1, 2, "door", new EButton[]{b, b2}));

        //Screen 1_0
        screens[1][0].entities.add(new EGhost(7 * 64, 4 * 64));

        //Screen 2_0
        EButton b3 = new EStickyButton(2 * 64, 7 * 64);
        EButton b4 = new EStickyButton(2 * 64, 2 * 64);
        EButton b5 = new EStickyButton(9 * 64, 2 * 64);
        EButton b6 = new EStickyButton(9 * 64, 7 * 64);

        screens[2][0].entities.add(b3);
        screens[2][0].entities.add(b4);
        screens[2][0].entities.add(b5);
        screens[2][0].entities.add(b6);
        screens[2][0].entities.add(new EDoor(5 * 64, 9 * 64, 2, 1, "door2", new EButton[]{b3, b4, b5, b6}));

        //Screen 2_1
        EButton b7 = new EStickyButton(64, 64);
        EButton b8 = new EStickyButton(64, 8 * 64);

        screens[2][1].entities.add(b7);
        screens[2][1].entities.add(b8);

        screens[2][1].entities.add(new EDoor(7 * 64, 256, 1, 2, "door", new EButton[]{b7, b8}));

        EButton b9 = new EStickyButton(10 * 64, 4 * 64);
        EButton b10 = new EStickyButton(10 * 64, 5 * 64);

        screens[2][1].entities.add(b9);
        screens[2][1].entities.add(b10);

        screens[2][1].entities.add(new EDoor(0, 256, 1, 2, "door", new EButton[]{b9, b10}));

        //Screen 1_1

        EGhost g2 = new EGhost(3 * 64,3 *  64);
        EGhost g3 = new EGhost(3 * 64, 6 * 64);

        screens[1][1].entities.add(g2);
        screens[1][1].entities.add(g3);

        //Screen 1_2 + 2_2
        EButton b11 = new EStickyButton(6 * 64, 4 * 64);
        EButton b12 = new EStickyButton(6 * 64, 5 * 64);

        screens[2][2].entities.add(b11);
        screens[2][2].entities.add(b12);

        screens[1][2].entities.add(new EDoor(0, 256, 1, 2, "door", new EButton[]{b11, b12}));

        //Screen 0_1
        ETreasureChest treasure = new ETreasureChest(5 * 64, 5 * 64, "Treasure");
        screens[0][1].entities.add(treasure);
    }

    public void Update()
    {
        //Show the player the screen with controls info
        //Include a "press any key to start" button
        while(gameState == GameState.Introduction)
        {
            if(Input.spacePressed)
            {
                gameState = GameState.Running;
            }
        }

        //Main Game loop, runs through all logic
        while(gameState == GameState.Running)
        {
            //A deltaTime value of 1 represents 1/60th of a second.
            if(Time.GetDeltaTime() >= 1f/144f)
            {
                Tick();
                Time.ResetDeltaTime();
            }
        }

        //The player won! Show the victory screen
        if(gameState == GameState.Victory)
        {
            while(true)
            {
                if(Input.spacePressed)
                {
                    return;
                }
            }
        }
        //The player was defeated by an enemy, show lose screen then close program.
        else if (gameState == GameState.GameOver)
        {
            while(true)
            {
                if(Input.spacePressed)
                {
                    return;
                }
            }
        }
    }

    //Ticks occur every 1/144 seconds
    public void Tick()
    {
        //Handle Player Logic
        player.Tick();

        //Handle Screen / Entity Logic
        currentScreen.UpdateEntities();

        //Render screen & entities
        gameBoard.repaint();
    }

}
