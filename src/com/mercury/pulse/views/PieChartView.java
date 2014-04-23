package com.mercury.pulse.views;

import com.mercury.pulse.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PieChartView extends View {

	private static final String				TAG = PieChartView.class.getSimpleName();

	private int								mIssues = 0;
	private Paint							mCentreCirclePaint, mCentreTextPaint, mCentreNumberPaint;
	private RectF							mRect;
	private int[]							mColours;

	public PieChartView(Context context) {
		super(context);
		init(context);
	}

	public PieChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context c){
		Log.d(TAG,"Init called");
		// Create the colour array
		mColours = new int[3];
		mColours[0] = Color.parseColor("#99CC00");
		mColours[1] = Color.parseColor("#FFBB33");
		mColours[2] = Color.parseColor("#FF4444");

		mCentreCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCentreCirclePaint.setColor(Color.parseColor("#F3F3F3"));
		mCentreCirclePaint.setStyle(Paint.Style.FILL);

		mCentreTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mCentreTextPaint.setTypeface(Typeface.create("sans-serif-condensed", Typeface.NORMAL));
		mCentreTextPaint.setColor(mColours[1]);
		mCentreTextPaint.setTextAlign(Align.CENTER);
		mCentreTextPaint.setTextSize(80);
		mCentreNumberPaint = new Paint(mCentreTextPaint);
		mCentreNumberPaint.setTextSize(190);
		mCentreNumberPaint.setColor(c.getResources().getColor(R.color.text_main));
		mCentreNumberPaint.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
		mRect = new RectF();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	protected void onDraw(Canvas canvas) {
		Log.d(TAG, "onDraw Called");
		super.onDraw(canvas);
		mRect.set(0, 0, getWidth(), getHeight());
		//Do the outy bit
		canvas.drawArc(mRect, (float) 0f, (float) 360.00, true, paintPieChart());

		//Do the middle bit
		canvas.drawCircle(getWidth()/2, getHeight()/2, (float)(getWidth()*0.42), mCentreCirclePaint);
		canvas.drawText(Integer.toString(mIssues), getWidth()/2,
				(getHeight()/2)+60, mCentreNumberPaint);
		canvas.save();
	}

	private Paint paintPieChart(){
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		if (mIssues == 0) {
			paint.setColor(mColours[0]);
		} else if (mIssues == 1) {
			paint.setColor(mColours[1]);
		} else {
			paint.setColor(mColours[2]);
		}
		return paint;
	}

}