package wonderful.com.oneminute.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import wonderful.com.oneminute.R;

/**
 * Created by wei41 on 2016/10/5.
 */

public class WaitView extends View {

    private Paint paint;
    private int width;
    private int height;
    private Bitmap wait_line_1;
    private Bitmap wait_line_1_temp;
    private Bitmap wait_line_2;
    private Bitmap wait_line_2_temp;
    private Bitmap wait_background;
    private float scaleValueX;
    private Matrix rotateMatrix_line1;
    private Matrix rotateMatrix_line2;
    private Matrix scaleMatrix;
    private float rotate = 0;
    private boolean isTrue = false;
    private boolean isStop = true;

    //初始化
    public WaitView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setAntiAlias(true);
        rotateMatrix_line1 = new Matrix();
        rotateMatrix_line2 = new Matrix();
        scaleMatrix = new Matrix();
        wait_line_1 = BitmapFactory.decodeResource(getResources(), R.drawable.wait_line_1);
        wait_line_2 = BitmapFactory.decodeResource(getResources(), R.drawable.wait_line_2);
        wait_background = BitmapFactory.decodeResource(getResources(), R.drawable.wait_background);
        rotate();
    }

    //开始子线程不断设置旋转角度
    private void rotate() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (!isStop) {
                        rotate++;
                        rotateMatrix_line1.reset();
                        rotateMatrix_line1.setRotate(rotate, wait_line_1.getWidth() / 2, wait_line_1.getHeight() / 2);
                        rotateMatrix_line2.reset();
                        rotateMatrix_line2.setRotate(2 * rotate, wait_line_1.getWidth() / 2, wait_line_1.getHeight() / 2);
                        rotate += 0.5;
                        postInvalidate();
                    }
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(wait_background, 0, 0, paint);
        canvas.drawBitmap(wait_line_1_temp, rotateMatrix_line1, paint);
        canvas.drawBitmap(wait_line_2_temp, rotateMatrix_line2, paint);
    }


    //测量高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        if (!isTrue) {
            setContentScale();
        }
        setMeasuredDimension(width, height);
    }

    //缩放当前的bitmap为合适的宽度
    private void setContentScale() {
        scaleValueX = (float) width / wait_line_1.getWidth();
        scaleMatrix.reset();
        scaleMatrix.setScale(scaleValueX, scaleValueX);
        wait_line_1 = Bitmap.createBitmap(wait_line_1, 0, 0, wait_line_1.getWidth(), wait_line_1.getHeight(), scaleMatrix, true);
        wait_line_2 = Bitmap.createBitmap(wait_line_2, 0, 0, wait_line_2.getWidth(), wait_line_2.getHeight(), scaleMatrix, true);
        wait_background = Bitmap.createBitmap(wait_background, 0, 0, wait_background.getWidth(), wait_background.getHeight(), scaleMatrix, true);
        wait_line_1_temp = wait_line_1;
        wait_line_2_temp = wait_line_2;
        isTrue = true;
        isStop = false;
    }

    //停止此view的转动
    public void stopView() {
        isStop = true;
    }
}
