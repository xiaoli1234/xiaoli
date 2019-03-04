package login.example.com.myapplication6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoAdapter extends BaseAdapter  {

	/**
	 * 上下文对象
	 */
	private Context context = null;
	private MainActivity context1;
    //颜色选择器
	private ColorPickerDialog dialog;

	/**
	 * 数据集合
	 */
	private List<DemoBean> datas = null;
	public static final int DIALGE_ID = 0;
	private CallBack mCallBack;
	private CallBack1 mCallBack1;
	private CallBack2 mCallBack2;
	private CallBack3 mCallBack3;
	private CallBack4 mCallBack4;

	private View viewc,viewc1,viewd,viewe;
	private PopupWindow windowc,windowc1,windowd,windowe;
	private ImageView tvImg;
	private ViewGroup finalLayout;

	private Button button11;
	private Button button22;
    private Button button33;
	private Button button44;

	private int colora;
	private int colora1;

	/**
	 * CheckBox是否选择的存储集合，key是position，value是该position是否选中
	 */
	private Map<Integer, Boolean> isCheckMap = new HashMap<Integer, Boolean>();

//	public interface Callback {
//        public void click(View v);
//     }
//	public interface Callback1 {
//		public void click1(View v);
//	}

	public DemoAdapter(Context context, List<DemoBean> datas,CallBack callBack,CallBack1 callBack1,CallBack2 callBack2,CallBack3 callBack3,CallBack4 callBack4) {
		this.datas = datas;
		this.context = context;
		// 初始化，默认都没有选中
		configCheckMap(false);
		//回调
		this.mCallBack=callBack;
		this.mCallBack1=callBack1;
		this.mCallBack2=callBack2;
		this.mCallBack3=callBack3;
		this.mCallBack4=callBack4;
	}
	/**
	 *自定义回调接口
	 * @return
	 */
	public interface CallBack{
		void onclick(View v,int id);
	}
	public interface CallBack1{
		void onclick1(View v,int color,int pos1);
	}
	public interface CallBack2{
		void onclick2(View v,int color,int pos2);
	}
	public interface CallBack3{
		void onclick3(View v,int shuzi,int pos3);
	}
	public interface CallBack4{
		void onclick4(View v,int tou,int pos4);
	}
	/**
	 * 首先，默认情况下，所有项目都是没有选中的，这里进行初始化
	 */
	public void configCheckMap(boolean bool) {

		for (int i = 0; i < datas.size(); i++) {
			isCheckMap.put(i, bool);
		}

	}

	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(final int position, final View convertView, ViewGroup parent) {

		ViewGroup layout = null;

		/**
		 * 进行ListView的优化
		 */
		if (convertView == null) {
			layout = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.listview_item_layout, parent, false);
		} else {
			layout = (ViewGroup) convertView;
		}

		DemoBean bean = datas.get(position);

		/*
		 * 获得该item是否允许删除
		 */
		boolean canRemove = bean.isCanRemove();

		/*
		 * 设置每一个item的文本
		 */
//      TextView tvId = (TextView) layout.findViewById(R.id.tvId);
//      tvId.setText(bean.getId());
        TextView tvName = (TextView) layout.findViewById(R.id.tvName);
        tvName.setText(bean.getName());
        DemoBean st = datas.get(position);
        String text = st.getPic();
        tvImg = (ImageView)layout.findViewById(R.id.tvImg);
        ImageView tvImg1 = (ImageView)layout.findViewById(R.id.tvImg1);
        try {
            InputStream in=context.getAssets().open(text+".png");
            Bitmap bmp=BitmapFactory.decodeStream(in);
            tvImg.setImageBitmap(bmp);
			tvImg.setColorFilter(Color.YELLOW);
//			GradientDrawable tvImg = (GradientDrawable) layout.getBackground();
//			tvImg.setStroke(2,Color.RED);
        } catch (Exception e) {
            // TODO: handle exception
        }
		//final ViewGroup finalLayout1 = layout;
		finalLayout = layout;
		tvImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showcolortotal(position);
			}
		});
		tvImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bb = new AlertDialog.Builder(context);
                bb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
						String namer = datas.get(position).getName();
						mCallBack.onclick(finalLayout,position);
                        datas.remove(position);
						configCheckMap(false);
                        notifyDataSetChanged();
                    }
                });
                bb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        
                    }
                });
                bb.setMessage("确定要删除吗");
                bb.setTitle("提示");
                bb.show();
            }
        });
        //tvName.setText(st.getName());
        //ImageView tvImg = (ImageView) layout.findViewById(R.id.tvImg);
        //tvImg.setBackgroundResource(R.drawable.gy_shuihui_jc);
//		TextView tvTitle = (TextView) layout.findViewById(R.id.tvTitle);
//		tvTitle.setText(bean.getTitle());

		/*
		 * 获得单选按钮
		 */
		CheckBox cbCheck = (CheckBox) layout.findViewById(R.id.cbCheckBox);

		/*
		 * 设置单选按钮的选中
		 */
		cbCheck.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				/*
				 * 将选择项加载到map里面寄存
				 */
				isCheckMap.put(position, isChecked);

			}
		});

		if (!canRemove) {
			// 隐藏单选按钮，因为是不可删除的
			cbCheck.setVisibility(View.GONE);
			cbCheck.setChecked(false);
		} else {
			cbCheck.setVisibility(View.VISIBLE);

			if (isCheckMap.get(position) == null) {
				isCheckMap.put(position, false);
			}

			cbCheck.setChecked(isCheckMap.get(position));

			ViewHolder holder = new ViewHolder();

			holder.cbCheck = cbCheck;

//			holder.tvTitle = tvTitle;

			/**
			 * 将数据保存到tag
			 */
			layout.setTag(holder);
		}

		return layout;
	}

	/**
	 * 增加一项的时候
	 */
	public void add(DemoBean bean) {
		this.datas.add(0, bean);

		// 让所有项目都为不选择
		configCheckMap(false);
	}

	// 移除一个项目的时候
	public void remove(int position) {
		this.datas.remove(position);
	}

	public Map<Integer, Boolean> getCheckMap() {
		return this.isCheckMap;
	}

	public static class ViewHolder {

		public TextView tvTitle = null;
		public CheckBox cbCheck = null;
		public Object data = null;

	}

	public List<DemoBean> getDatas() {
		return datas;
	}
	public void showcolor(final int pos1){
		LayoutInflater inflater = LayoutInflater.from(context);
		viewc = inflater.inflate(R.layout.main, null);
		//设置弹出框样式
		windowc = new PopupWindow(viewc, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
		windowc.setContentView(viewc);
		windowc.setOutsideTouchable(true);
		windowc.setFocusable(true);
		//window.setFocusable(true); // 设置PopupWindow可获得焦点
		windowc.setTouchable(true); // 设置PopupWindow可触摸
		// 实例化一个ColorDrawable颜色为半透明
		windowc.showAtLocation(viewc, Gravity.BOTTOM, 10, 10);
		Button cpv1 = (Button)viewc.findViewById(R.id.cpv1);
		Button cpv2 = (Button)viewc.findViewById(R.id.cpv2);
		ColorPickerView cpv=(ColorPickerView)viewc.findViewById(R.id.cpv);
		cpv.setOnColorChangedListenner(new ColorPickerView.OnColorChangedListener() {
			@Override
			public void onColorChanged(int color, int originalColor,float saturation) {
				tvImg.setColorFilter(color);
				button11.setBackgroundColor(color);
			}

		});
		cpv1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				windowc.dismiss();
			}
		});
		cpv2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				windowc.dismiss();
			}
		});

	}
	public void showcolor1(final int pos2){
		LayoutInflater inflater = LayoutInflater.from(context);
		viewd = inflater.inflate(R.layout.main, null);
		//设置弹出框样式
		windowd = new PopupWindow(viewd, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
		windowd.setContentView(viewd);
		windowd.setOutsideTouchable(true);
		windowd.setFocusable(true);
		//window.setFocusable(true); // 设置PopupWindow可获得焦点
		windowd.setTouchable(true); // 设置PopupWindow可触摸
		// 实例化一个ColorDrawable颜色为半透明
		windowd.showAtLocation(viewd, Gravity.BOTTOM, 10, 10);
		Button cpv1 = (Button)viewd.findViewById(R.id.cpv1);
		Button cpv2 = (Button)viewd.findViewById(R.id.cpv2);
		ColorPickerView cpv=(ColorPickerView)viewd.findViewById(R.id.cpv);
		cpv.setOnColorChangedListenner(new ColorPickerView.OnColorChangedListener() {
			@Override
			public void onColorChanged(int color, int originalColor,float saturation) {
//				tvImg.setColorFilter(color);
				button22.setBackgroundColor(color);
				//mCallBack2.onclick2(finalLayout,color,pos2);
			}

		});
		cpv1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				windowd.dismiss();
			}
		});
		cpv2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				windowd.dismiss();
			}
		});

	}
    public void showcolor2(final int pos3){
		final NumPicker np = new NumPicker((Activity) context);
		np.show();
		np.setOnCancelListener(new NumPicker.OnCancelClickListener() {
			@Override
			public void onClick() {
				np.dismiss();
			}
		});
		np.setOnComfirmListener(new NumPicker.onComfirmClickListener() {
			@Override
			public void onClick(int num) {
				Toast.makeText(context, num + "", Toast.LENGTH_SHORT).show();
				np.dismiss();
				button33.setText(String.valueOf(num));
			}
		});
    }
	public void showcolor3(final int pos3){
		final NumPicker np = new NumPicker((Activity) context);
		np.show();
		np.setOnCancelListener(new NumPicker.OnCancelClickListener() {
			@Override
			public void onClick() {
				np.dismiss();
			}
		});
		np.setOnComfirmListener(new NumPicker.onComfirmClickListener() {
			@Override
			public void onClick(int num) {
				Toast.makeText(context, num + "", Toast.LENGTH_SHORT).show();
				np.dismiss();
				button44.setText(String.valueOf(num));
			}
		});
	}
	public void showcolortotal(final int pos){
		LayoutInflater inflater = LayoutInflater.from(context);
		viewc1 = inflater.inflate(R.layout.maintal, null);
		//设置弹出框样式
		windowc1 = new PopupWindow(viewc1, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
		windowc1.setContentView(viewc1);
		windowc1.setOutsideTouchable(true);
		windowc1.setFocusable(true);
		//window.setFocusable(true); // 设置PopupWindow可获得焦点
		windowc1.setTouchable(true); // 设置PopupWindow可触摸
		// 实例化一个ColorDrawable颜色为半透明
		windowc1.showAtLocation(viewc1, Gravity.BOTTOM, 10, 10);
		Button button1 = (Button)viewc1.findViewById(R.id.button1);
		Button button2 = (Button)viewc1.findViewById(R.id.button2);
        Button button3 = (Button)viewc1.findViewById(R.id.button3);
		Button button4 = (Button)viewc1.findViewById(R.id.button4);
        Button buttonx = (Button)viewc1.findViewById(R.id.buttonx);
		Button buttonx1 = (Button)viewc1.findViewById(R.id.buttonx1);
		button11 = (Button)viewc1.findViewById(R.id.button11);
		button22 = (Button)viewc1.findViewById(R.id.button22);
        button33 = (Button)viewc1.findViewById(R.id.button33);
		button44 = (Button)viewc1.findViewById(R.id.button44);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showcolor(pos);
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showcolor1(pos);
			}
		});
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showcolor2(pos);
            }
        });
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showcolor3(pos);
			}
		});
        buttonx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable background=button11.getBackground();
                ColorDrawable colorDrawable = (ColorDrawable) background;
                colora=colorDrawable.getColor();
                mCallBack1.onclick1(finalLayout,colora,pos);
                Drawable background1=button22.getBackground();
                ColorDrawable colorDrawable1 = (ColorDrawable) background1;
                colora1=colorDrawable1.getColor();
                mCallBack2.onclick2(finalLayout,colora1,pos);
				String xc = button33.getText().toString();
				String xc1 = button44.getText().toString();
				if(xc.equals("") || xc1.equals("")){
					Toast.makeText(context,  "宽度和透明度不能为空", Toast.LENGTH_SHORT).show();
				}else{
					mCallBack3.onclick3(finalLayout,Integer.parseInt(xc),pos);
					mCallBack4.onclick4(finalLayout,Integer.parseInt(xc1),pos);
					String path = PathUtil.getStoragePath(context, true) + "/list.sqlite";
					SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
					String sql = "select * from data where id = ?";//list是你的表名
					String  []args=new String[]{String.valueOf(pos+1)};
					Cursor cursorv = db.rawQuery(sql,args);
					ContentValues values3 = new ContentValues();
					//组装第一条数据
					values3.put("bcolor",colora);
					values3.put("lcolor",colora1);
					values3.put("kuan",xc);
					//插入数据
					db.update("data", values3, "id = ?", new String[]{String.valueOf(pos+1)});
					db.close();
					cursorv.close();
					windowc1.dismiss();
				}
            }
        });
		buttonx1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				windowc1.dismiss();
			}
		});
	}

}
