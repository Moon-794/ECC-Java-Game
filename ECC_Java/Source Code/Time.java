public class Time
{
    private static long lastTime = System.nanoTime();
    private static final double ns = 1000000000;
    public static double deltaTime = 0;

    public static double GetDeltaTime()
    {
        long currentTime = System.nanoTime();
        deltaTime += (currentTime - lastTime) / ns;
        lastTime = currentTime;
        return deltaTime;
    }

    public static void ResetDeltaTime()
    {
        deltaTime = 0;
    }
}
