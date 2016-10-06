package io.github.zhaoyu1995.reading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View {
    private Paint bg_paint;//背景画笔
    private Paint fg_paint;//前景画笔
    private int total_pages;//总页数
    private int current_pages;//当前已看页数
    private int width;
    private int height;
    private int padding = 10;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //以上构造函数不能删除


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.width = w;
        this.height = h;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(padding, height/8*3, width-padding, height/8*5, bg_paint);
        canvas.drawRect(padding, height/8*3,
                padding+(width-2*padding)*current_pages/total_pages, height/8*5, fg_paint);
    }

    private void init() {
        bg_paint = new Paint();
        fg_paint = new Paint();

        bg_paint.setColor(Color.GRAY);
        fg_paint.setColor(Color.BLUE);

    }

    public void drawProgress(int total, int cur) {
        this.total_pages = total;
        this.current_pages = cur;
        if (cur > total) {
            cur = total;
        }
        invalidate();
    }
}
