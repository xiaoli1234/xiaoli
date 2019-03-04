//package login.example.com.myapplication6;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.Message;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.ZoomControls;
//
//import com.esri.arcgisruntime.data.ServiceFeatureTable;
//import com.esri.arcgisruntime.geometry.Envelope;
//import com.esri.arcgisruntime.geometry.GeometryType;
//import com.esri.arcgisruntime.geometry.SpatialReferences;
//import com.esri.arcgisruntime.layers.FeatureLayer;
//import com.esri.arcgisruntime.mapping.ArcGISMap;
//import com.esri.arcgisruntime.mapping.Basemap;
//import com.esri.arcgisruntime.mapping.Viewpoint;
//import com.esri.arcgisruntime.mapping.view.LocationDisplay;
//import com.esri.arcgisruntime.mapping.view.MapView;
//import com.esri.arcgisruntime.mapping.view.SketchEditor;
//import com.esri.arcgisruntime.mapping.view.SketchStyle;
//
//import org.gdal.ogr.ogr;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static com.esri.arcgisruntime.geometry.GeometryType.POINT;
//import static com.esri.arcgisruntime.geometry.GeometryType.POLYGON;
//import static com.esri.arcgisruntime.geometry.GeometryType.POLYLINE;
//
////图层工具类
//public class Layertool extends MainActivity{
//
//    private MapView mMapView;
//    private LinearLayout name1, name2, name3, name4, name5;
//    private ImageView img1, img2, img3, img4, img5, img6;
//    private FeatureLayer mainShapefileLayer;//点图层
//    private SketchEditor mainSketchEditor;
//    private SketchStyle mainSketchStyle;
//    private LocationDisplay mLocationDisplay;
//    String[] reqPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
//    //定义弹窗变量
//    private LinearLayout coshp,back, demo, btntrue, btnedit;
//    //1弹窗
//    private TextView info1, colayer, info2, newlayer, rulayer, textscale;
//    private RadioButton radioButton_male, radioButton_female;
//    //2弹窗
//    private TextView textname, textname1, info3, info4, area;
//    private ListView listname;
//    private View viewa, viewb, viewc;
//    private PopupWindow window, windowc;
//    private List list = new ArrayList();
//    private MyShowAdapter myadapter;
//    private ListView show;
//    private LinearLayout delete, draw, draw1, change, undo, create, delete_ljt;
//    //导入图层，访问文件
//    private static String TAG = "MainActivity";
//    private static final int REQUEST_CODE = 1;   //请求码
//    private static final int REQUEST_CODE1 = 2;   //请求码
//    private static final int REQUEST_CODE2 = 3;//照相请求码
//    private static final int REQUEST_CODE3 = 4;//定位请求码
//    public static final String EXTRA_FILE_CHOOSER = "file_chooser";
//    public static final String EXTRA_FILE_CHOOSER1 = "file_chooser1";
//    //地图变量
//    private String pptPath;
//    private String pptPath1;
//    private String namegeo;
//    private ServiceFeatureTable mServiceFeatureTable;
//    private ZoomControls zoomCtrl;
//    private GeometryType gt;
//    //弹窗listview页面
//    private ViewGroup btnCancle = null;
//    private ViewGroup btnAdd = null;
//    private Button btnSelectAll = null;
//    private Button btnDelete = null;
//    private ListView lvListView = null;
//    //private DemoAdapter adpAdapter = null;//new DemoAdapter
//    List<DemoBean> demoDatas = new ArrayList<DemoBean>();
//
//    //成员变量
//    private int a = 0;
//    private int b = 0;
//    private int c = 0;
//    private int x = 0;
//    private int d = 0;
//    private int t1 = 0;
//    private int i = 0;
//    private boolean isSelect = false;
//    Map<String, Object> obj = new HashMap<String, Object>();
//    //系统相机拍照
//    private String fileNmae;
//    public static Handler handler;
//    private static String srcPath;
//    //新增SHP模块
//    private String shpPath;
//    private View viewab, viewbb;
//    private PopupWindow windowb, window1b;
//    private LinearLayout backb, btntrueb, add, value, deleteb, update, back1, btntrue1b;
//    private EditText shpname, name1b, big1, editc;
//    private RadioGroup radiogroup, radiogroup1;
//    private RadioButton point, line, ploy, zf1, zf2, zf3, zf4;
//    private String shapename;
//    private String status = "POINT";
//    private Boolean addz = false;
//    private String namea, biga;
//    private int leixinga = ogr.OFTString;
//    private String leixingb = "字符串";
//    private java.util.Map<String, Object> attributes = new HashMap<String, Object>();
//    private int z = 0;
//    //listview
//    private ViewGroup btnCancleb = null;
//    private ViewGroup btnAddb = null;
//    private Button btnSelectAllb = null;
//    private LinearLayout btnDeleteb;
//    private ListView lvListViewb;
//    List<Demo1Bean> demoDatasb = new ArrayList<Demo1Bean>();
//    //private Demo1Adapter adpAdapterb = new Demo1Adapter(mContext,demoDatasb);
//    private TextView tvName, textc;
//    private Context context;
//    private MainActivity activity;
//
//    public Context getContxt() {
//        return context;
//    }
//
//    public void setContxt(Context contxt) {
//        this.context = (MainActivity) contxt;
//    }
//
//    public Activity getActivity() {
//        return (MainActivity) activity;
//    }
//
//    public void setActivity(Activity activity) {
//        this.activity = (MainActivity) activity;
//    }
//
//    public Layert(Context context, MainActivity activity) {
//        this.setContxt(context);
//        this.setActivity(activity);
//        activity=(MainActivity)context;
//        //setContentView(R.layout.activity_main);
//    }
//    private DemoAdapter adpAdapter = new DemoAdapter(context,demoDatas);
//
//
//    //弹出图层框
//    public void showpopupview() {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        viewa = inflater.inflate(R.layout.popupwindow, null);
//        //设置弹出框样式
//        window = new PopupWindow(viewa, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
//        window.setContentView(viewa);
//        window.setOutsideTouchable(true);
//        window.setFocusable(true);
//        //window.setFocusable(true); // 设置PopupWindow可获得焦点
//        window.setTouchable(true); // 设置PopupWindow可触摸
//        // 实例化一个ColorDrawable颜色为半透明
//        window.showAtLocation(viewa, Gravity.BOTTOM, 10, 10);
//        initpopup();
//        // 初始化视图
//        initView();
//        // 初始化控件
//        //initData();
//        //本地栅格管理
//        coshp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent();
//                intent1.setClass(context, FileChooserActivity1.class);
//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    //startActivityForResult(intent1, REQUEST_CODE1);
//                    intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                    activity.startActivityForResult(intent1,REQUEST_CODE1);
//                } else {
//                    Toast.makeText(context, R.string.sdcard_unmonted_hint, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        //导入图层
//        rulayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setClass(context, FileChooserActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//                    activity.startActivityForResult(intent, REQUEST_CODE);
//                } else {
//                    toast(getText(R.string.sdcard_unmonted_hint));
//                }
//            }
//        });
//        //新建shp图层
//        newlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showpopupviewb();
//            }
//        });
//        radioButton_male.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.showLayer1();
//                window.dismiss();
//                toast("导入影像栅格图层成功");
//            }
//        });
//        radioButton_female.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                activity.showLayer();
//                window.dismiss();
//                toast("导入街区栅格图层成功");
//            }
//        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                window.dismiss();
//            }
//        });
//        btntrue.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                window.dismiss();
//            }
//        });
//
//    }
//    private void toast(CharSequence hint){
//        Toast.makeText(context, hint , Toast.LENGTH_SHORT).show();
//    }
//    public void onActivityResult(int requestCode , int resultCode , Intent data){
//        Log.v(TAG, "onActivityResult#requestCode:"+ requestCode  + "#resultCode:" +resultCode);
//        if(resultCode == RESULT_CANCELED){
//            toast(getText(R.string.open_file_none));
//            return ;
//        }
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE2){
//            Bitmap b = BitmapFactory.decodeFile(fileNmae);
//            //拿到图片保存地址
//            srcPath = fileNmae;
//            Message msg = Message.obtain();
//            msg.obj=srcPath;
//            if (handler!=null){
//                handler.sendMessage(msg);
//            }
//            File myCaptureFile =new File(fileNmae);
//            try {
//                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
//                    if(!myCaptureFile.getParentFile().exists()){
//                        myCaptureFile.getParentFile().mkdirs();
//                    }
//                    BufferedOutputStream bos;
//                    bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
//                    b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
//                    bos.flush();
//                    bos.close();
//                    toast("拍照保存成功");
//                    mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
//                }else{
//                    Toast toast= Toast.makeText(this, "保存失败，SD卡无效", Toast.LENGTH_SHORT);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE1){
//            //栅格路径名
//            pptPath1 = data.getStringExtra(EXTRA_FILE_CHOOSER);
//            if(pptPath1!=null){
//                showLayer2();
//                toast("加载本地图层成功");
//            }
//        }
//        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
//            //获取路径名
//            //矢量路径名
//            pptPath = data.getStringExtra(EXTRA_FILE_CHOOSER);
//            Log.v(TAG, "onActivityResult # pptPath : "+ pptPath );
//            if(pptPath != null){
//                //showShapefile();
//                if(gt.equals(POINT)){
//                    adpAdapter.add(new DemoBean("1","SHP1","点模板","point.jpg",true));
//                    adpAdapter.notifyDataSetChanged();
//                    lvListView.setAdapter(adpAdapter);
//                }else if(gt.equals(POLYLINE)){
//                    adpAdapter.add(new DemoBean("2","SHP2","线模板","polyline.jpg",true));
//                    adpAdapter.notifyDataSetChanged();
//                    lvListView.setAdapter(adpAdapter);
//                }else if(gt.equals(POLYGON)){
//                    adpAdapter.add(new DemoBean("3","SHP3","面模板","polygon.jpg",true));
//                    adpAdapter.notifyDataSetChanged();
//                    lvListView.setAdapter(adpAdapter);
//                }
//                toast("导入矢量图层成功");
//            } else {
//                toast(getText(R.string.open_file_failed));
//            }
//        }
//    }
//    public void initpopup() {
//        back = (LinearLayout) viewa.findViewById(R.id.back);
//        demo = (LinearLayout) viewa.findViewById(R.id.demo);
//        btntrue = (LinearLayout) viewa.findViewById(R.id.btntrue);
//        coshp = (LinearLayout) viewa.findViewById(R.id.coshp);
//        newlayer = (TextView) viewa.findViewById(R.id.newlayer);
//        rulayer = (TextView) viewa.findViewById(R.id.rulayer);
//        radioButton_male = (RadioButton) viewa.findViewById(R.id.radioButton_male);
//        radioButton_female = (RadioButton) viewa.findViewById(R.id.radioButton_female);
//        newlayer = (TextView) viewa.findViewById(R.id.newlayer);
//    }
//
//    private void initView() {
//        btnDelete = (Button) viewa.findViewById(R.id.btnDelete);
//        btnSelectAll = (Button) viewa.findViewById(R.id.btnSelectAll);
//        lvListView = (ListView) viewa.findViewById(R.id.lvListView);
//    }
//
//    private void initData() {
//        //模拟假数据
//        demoDatas.add(new DemoBean("1", "SHP1", "点模板", "point.jpg", true));
//        demoDatas.add(new DemoBean("2", "SHP2", "线模板", "line.jpg", true));
//        demoDatas.add(new DemoBean("3", "SHP3", "面模板", "ploy.jpg", true));
//        lvListView.setAdapter(adpAdapter);
//    }
//
//
//}
