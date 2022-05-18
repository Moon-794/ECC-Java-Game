public class Physics
{
    public static boolean BoxCollision(int x1, int y1, int x2, int y2)
    {
        boolean xAlignment = (x1 < x2 + 64 && x1 + 64 > x2);
        boolean yAlignment = (y1 < y2 + 64 && y1 + 64 > y2);

        return xAlignment && yAlignment;
    }

    public static boolean BoxCollision(int x1, int y1, int x2, int y2, int width1, int height1, int width2, int height2)
    {
        boolean xAlignment = (x1 < x2 + width2 && x1 + width1 > x2);
        boolean yAlignment = (y1 < y2 + height2 && y1 + height1 > y2);

        return xAlignment && yAlignment;
    }
}
