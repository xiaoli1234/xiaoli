package login.example.com.myapplication6;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;import android.widget.ScrollView;

import android.widget.TextView;

public class CustomView extends View{
	private final static String TAG = CustomView.class.getSimpleName();
	
	private Context _context;
	private int 	_quantity = 5;

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this._context = context;
	}
	
	public CustomView(Context context) {
		super(context);
		this._context = context;
	}
	
	public void setQuantity(int quantity){
		this._quantity = quantity;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	public void draw(Canvas canvas) {
		CustomHorizontalScrollView scrollView = new CustomHorizontalScrollView(_context);
		RelativeLayout relativeLayout = new RelativeLayout(_context);
		LinearLayout linearLayout = new LinearLayout(_context);
		linearLayout.setOrientation(0);//horizontal	: 0,	vertical : 1
		for (int i = 0; i < _quantity; i++) {
			TextView textView = new TextView(_context);
			textView.setTextSize(14);
			LayoutParams params = new LayoutParams(dp2px(_context, 50), dp2px(_context, 50));
			params.gravity = Gravity.CENTER;
			textView.setLayoutParams(params);
			linearLayout.addView(textView);
			Log.e(TAG, "draw textview");
		}
		
		scrollView.addView(relativeLayout);
		scrollView.draw(canvas);
		super.draw(canvas);
	}
	
	public int dp2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public int px2dip(Context context, float pxValue){ 
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale +0.5f);
	}
}
