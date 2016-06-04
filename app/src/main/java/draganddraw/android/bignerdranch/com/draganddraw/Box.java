package draganddraw.android.bignerdranch.com.draganddraw;

import android.graphics.PointF;

public class Box
{
    private PointF origin;
    private PointF current;

    public PointF getOrigin() {
        return origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF newCurrent)
    {
        current = newCurrent;
    }

    public Box(PointF newOrigin)
    {
        origin = current = newOrigin;
    }
}
