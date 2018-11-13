package ui.widget;



import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zhonglian.battleworld.R;


public class CircleBar extends View {
    // 画圆环的画笔
    private Paint ringPaint;
    // 画字体的画笔
    private Paint textPaint;
    // 圆环颜色
    private int ringColor;
    // 字体颜色
    private int textColor;
    // 半径
    private float radius;
    // 圆环宽度
    private float strokeWidth;
    // 字的长度
    private float txtWidth;
    // 字的高度
    private float txtHeight;
    // 总进度
    private int totalProgress = 100;
    // 当前进度
    private int currentProgress;
    // 透明度
    private int alpha = 25;
    private float x;
    private float y;
    // view宽度
    private int width;
    // view高度
    private int height;
    //当前点的实际位置
    private float[] pos;
    //当前点的tangent值
    private float[] tan;
    private Bitmap bitmap;
    //矩阵
    private Matrix matrix;
    //小圆点画笔
    private Paint mBitmapPaint;


    // 默认Padding值20
    private final static int defaultPadding = 20;
    private Matrix mManatrix;
    private RectF mMiddleProgressRect;
    private RectF mManProgressRect;
    private Paint mRingPaint;
    private Paint clearPaint;
    private Paint percentagePaint;
    private double clear;
    private String cacheCount;

    public CircleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleProgressbar, 0 , 0);
        radius = typeArray.getDimension(R.styleable.CircleProgressbar_radius, 90);
        strokeWidth = typeArray.getDimension(R.styleable.CircleProgressbar_strokeWidth, 10);
        ringColor = typeArray.getColor(R.styleable.CircleProgressbar_ringColor, 0xFF0000);
        textColor = typeArray.getColor(R.styleable.CircleProgressbar_textColor, 0xFFFFFF);

    }

    private void initVariable() {
        //上层圆环
        ringPaint = new Paint();
        ringPaint.setAntiAlias(true);
        ringPaint.setDither(true);
        ringPaint.setColor(ringColor);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeCap(Paint.Cap.ROUND);
        ringPaint.setStrokeWidth(strokeWidth);

        //100%进度
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setTextSize(radius/2);
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        txtHeight = fm.descent + Math.abs(fm.ascent);

        //清除中
        clearPaint = new Paint();
        clearPaint.setAntiAlias(true);
        clearPaint.setStyle(Paint.Style.FILL);
        clearPaint.setColor(textColor);
        clearPaint.setTextSize(30);
        Paint.FontMetrics fm1 = textPaint.getFontMetrics();
        txtHeight = fm1.descent + Math.abs(fm1.ascent);

        //%
        percentagePaint = new Paint();
        percentagePaint.setAntiAlias(true);
        percentagePaint.setStyle(Paint.Style.FILL);
        percentagePaint.setColor(textColor);
        percentagePaint.setTextSize(30);
        Paint.FontMetrics fm2 = textPaint.getFontMetrics();
        txtHeight = fm2.descent + Math.abs(fm2.ascent);


        //底层圆
        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);// 抗锯齿效果
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(strokeWidth);
        mRingPaint.setColor(getResources().getColor(R.color.write));// 背景



        //图片
        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(getResources().getColor(R.color.color_tab));
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setStrokeCap(Paint.Cap.ROUND);
        mBitmapPaint.setStrokeWidth(strokeWidth);


        //初始化小圆点图片
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 6;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.drawable_rn, options);
        pos = new float[2];
        tan = new float[2];
        matrix = new Matrix();
        mManatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentProgress >= 0) {
            float x = getWidth() / 2;
            float y = getHeight() / 2;
            canvas.drawCircle(x, y, radius, mRingPaint);




            String txt = currentProgress+"";
            txtWidth = textPaint.measureText(txt, 0, txt.length());
            canvas.drawText(txt, getWidth() / 2 - txtWidth / 2, getHeight() / 2 + txtHeight / 4, textPaint);

            String percentageTxt="%";
            canvas.drawText(percentageTxt, getWidth() / 2 + txtWidth/2, getHeight() / 2 - txtHeight / 3, percentagePaint);



            String clearTxt="清除中";
            float clearWidth  = clearPaint.measureText(clearTxt, 0, clearTxt.length());
            canvas.drawText(clearTxt, getWidth() / 2 - clearWidth / 2, getHeight() / 2 + 120, clearPaint);

            String cacheingTxt=clear+"/"+cacheCount;
            float cacheingWidth  = clearPaint.measureText(cacheingTxt, 0, cacheingTxt.length());
            canvas.drawText(cacheingTxt, getWidth() / 2 - cacheingWidth / 2, getHeight() / 2 + 170, clearPaint);


            Path path = new Path();
            path.addArc(mManProgressRect, 0, ((float) currentProgress / totalProgress) * 360);
            PathMeasure pathMeasure = new PathMeasure(path, false);
            pathMeasure.getPosTan(pathMeasure.getLength() * 1, pos, tan);
            matrix.reset();
            matrix.postTranslate(pos[0] - bitmap.getWidth() / 2, pos[1] - bitmap.getHeight() / 2);
            canvas.drawPath(path, ringPaint);
            //起始角度不为0时候才进行绘制小圆点
            if (((float) currentProgress / totalProgress) * 360 == 0) {
                return;
            }
            canvas.drawCircle(pos[0], pos[1], 20, mBitmapPaint);
            canvas.drawBitmap(bitmap, matrix, mBitmapPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w / 2;
        height = h / 2;
        mMiddleProgressRect = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
        mManProgressRect = new RectF(getWidth() / 2 - radius, getHeight() / 2 - radius, getWidth() / 2 + radius, getHeight() / 2 + radius);
    }

    public void setProgress(int progress) {
        currentProgress = progress;
        postInvalidate();
    }

    public void setClearing(double clearing){
        this.clear=clearing;
    }
    public void setCache(String cache){
        this.cacheCount=cache;
    }

}
