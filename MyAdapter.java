package login.example.com.myapplication6;

import java.util.HashMap;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import login.example.com.myapplication6.R;

public class MyAdapter extends BaseAdapter {
	private Context context;
	HashMap<Integer, String> saveMap;//这个集合用来存储对应位置上Editext中的文本内容
	public MyAdapter(Context context) {
		super();
		this.context = context;
		saveMap = new HashMap<Integer, String>();
	}
	@Override
	public int getCount() {
		return 30;
	}
	@Override
	public Object getItem(int position) {
		return null;
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			vh = new ViewHolder();
			convertView = View.inflate(context, R.layout.item_editext, null);
			vh.et_number = (EditText) convertView.findViewById(R.id.et_number);
			vh.tv_position = (TextView) convertView.findViewById(R.id.tv_position);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.tv_position.setText("第" + position + "个:");
		vh.et_number.setTag(position);//设置editext一个标记
		vh.et_number.clearFocus();//清除焦点  不清除的话因为item复用的原因   多个Editext同时改变
		vh.et_number.setText(saveMap.get(position));//将对应保存在集合中的文本内容取出来  并显示上去

		final EditText tempEditText=vh.et_number;
		vh.et_number.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				Integer tag= (Integer) tempEditText.getTag();
				saveMap.put(tag, s.toString());//在这里根据position去保存文本内容
			}
		});
		Log.e("TAG", saveMap.toString());
		Toast.makeText(context,saveMap.toString(),Toast.LENGTH_SHORT).show();
		return convertView;
	}
	public class ViewHolder {
		EditText et_number;
		TextView tv_position;
	}
}
