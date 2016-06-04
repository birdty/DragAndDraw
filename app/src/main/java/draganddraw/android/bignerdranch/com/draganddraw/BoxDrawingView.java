package draganddraw.android.bignerdranch.com.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class BoxDrawingView extends View
{
    public static final String TAG = "BoxDrawingView";

    private Box currentBox;

    private ArrayList<Box> boxes = new ArrayList<Box>();

    private Paint boxPaint;

    private Paint backgroundPaint;

    public BoxDrawingView(Context context)
    {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        boxPaint = new Paint();
        boxPaint.setColor(0x22ff0000);

        backgroundPaint = new Paint();
        backgroundPaint.setColor(0xfff8efe0);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        PointF curr = new PointF(event.getX(), event.getY());

        switch ( event.getAction() )
        {
            case MotionEvent.ACTION_DOWN:
                currentBox = new Box(curr);
                boxes.add(currentBox);
                break;
            case MotionEvent.ACTION_MOVE:
                if ( currentBox != null )
                {
                    currentBox.setCurrent(curr);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                currentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                currentBox = null;
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawPaint(backgroundPaint);

        for(Box box : boxes )
        {
            float left = Math.min(box.getOrigin().x, box.getCurrent().x);
            float right = Math.max(box.getOrigin().x, box.getCurrent().x);
            float top = Math.min(box.getOrigin().y, box.getCurrent().y);
            float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

            canvas.drawRect(left, top, right, bottom, boxPaint);
        }
    }
}
