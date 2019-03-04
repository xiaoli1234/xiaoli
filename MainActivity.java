package login.example.com.myapplication6;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;


import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.ArcGISFeature;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.FeatureQueryResult;
import com.esri.arcgisruntime.data.QueryParameters;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.data.ShapefileFeatureTable;
import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Geometry;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.GeometryType;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.PointCollection;
import com.esri.arcgisruntime.geometry.Polygon;
import com.esri.arcgisruntime.geometry.Polyline;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.RasterLayer;
import com.esri.arcgisruntime.layers.WmsLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.Viewpoint;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.LocationDisplay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.mapping.view.SketchCreationMode;
import com.esri.arcgisruntime.mapping.view.SketchEditor;
import com.esri.arcgisruntime.mapping.view.SketchStyle;
import com.esri.arcgisruntime.raster.Raster;
import com.esri.arcgisruntime.symbology.SimpleFillSymbol;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;
import com.esri.arcgisruntime.util.ListenableList;
import com.gisinfo.android.lib.base.findview.FindViewUtils;

import org.gdal.gdal.gdal;
import org.gdal.ogr.DataSource;
import org.gdal.ogr.FeatureDefn;
import org.gdal.ogr.FieldDefn;
import org.gdal.ogr.Layer;
import org.gdal.ogr.ogr;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import cn.sddman.arcgistool.common.Variable;
import cn.sddman.arcgistool.view.MeasureToolView;
import login.example.com.myapplication6.DemoAdapter.CallBack;
import login.example.com.myapplication6.DemoAdapter.CallBack1;
import login.example.com.myapplication6.DemoAdapter.CallBack2;
import login.example.com.myapplication6.DemoAdapter.CallBack3;
import login.example.com.myapplication6.DemoAdapter.CallBack4;

import static android.view.View.VISIBLE;
import static com.esri.arcgisruntime.geometry.GeometryType.POINT;
import static com.esri.arcgisruntime.geometry.GeometryType.POLYGON;
import static com.esri.arcgisruntime.geometry.GeometryType.POLYLINE;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener,CallBack ,CallBack1,CallBack2 ,CallBack3,CallBack4{

   // private Layert layert;
    private MapView mMapView;
    private LinearLayout namet,name1,name2,name3,name4,name5,yijian,drawg,caozuo,qiege,hebing,dline,baocun,infot,deletet;
    private ImageView img1,img2,img3,img4,img5,img6,img7,imgzp,ceju,cemian,createg;
    private FeatureLayer mainShapefileLayer,mainShapefileLayer1,mainShapefileLayer2,mainShapefileLayer3,mainShapefileLayer4,mainShapefileLayer5;//点图层
    private SketchEditor mainSketchEditor;
    private SketchStyle mainSketchStyle;
    private LocationDisplay mLocationDisplay;
    String[] reqPermissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    //定义弹窗变量
    private LinearLayout back,demo,btntrue,btnedit,rulayer,newlayer,back11,btntrue11,info4;
    //1弹窗
    private TextView info1,colayer,info2,textscale,textplp;
    private Button radioButton_male,radioButton_female,coshp;
    //2弹窗
    private TextView textname,info3,area,textnamea,textnameb,textnamec,textnamed,textnamee,textnamef;
    private EditText textname1,textnamea1,textnameb1,textnamec1,textnamed1,textnamee1,textnamef1;
    private View viewa,viewb,viewc,viewplp,viewczp;
    private PopupWindow window,windowc,windowplp,windowczp;
    private List lista1;
    private MyShowAdapter myadapter;
    private ListView show;
    private LinearLayout drawpoint,draw1p,changep,undop,createp,delete_ljtp;
    private LinearLayout drawline,draw1l,changel,undol,createl,delete_ljtl;
    private LinearLayout delete,draw,draw1,change,undo,create,delete_ljt,edita1;//点线面
    private LinearLayout backplp,btntrueplp;//点线面
    java.util.Map<String, Object> attributespolygon = new HashMap<String, Object>();
    //导入图层，访问文件
    private static String TAG = "MainActivity";
    private static final int REQUEST_CODE = 1;   //请求码
    private static final int REQUEST_CODE1 = 2;   //请求码
    private static final int REQUEST_CODE2 = 3;//照相请求码
    private static final int REQUEST_CODE3 = 4;//定位请求码
    public static final String EXTRA_FILE_CHOOSER = "file_chooser";
    public static final String EXTRA_FILE_CHOOSER1 = "file_chooser1";
    //ArrayList<String> lista1 = new ArrayList<String>();
    //地图变量
    private String pptPath,shpPath1;
    private String pptPath1;
    private String namegeo,namegeo1;
    private ServiceFeatureTable mServiceFeatureTable;
    private ZoomControls zoomCtrl;
    private GeometryType gt,gt1;
    //弹窗listview页面
    private ViewGroup btnCancle = null;
    private ViewGroup btnAdd = null;
    private Button btnSelectAll = null;
    private Button btnDelete = null;
    private ListView lvListView = null;
    //private DemoAdapter adpAdapter = null;//new DemoAdapter
    List<DemoBean> demoDatas = new ArrayList<DemoBean>();
    private ArrayList arr = new ArrayList<ArrayList>();
    private DemoAdapter adpAdapter = new DemoAdapter(this,demoDatas,this,this,this,this,this);
    //成员变量
    private int a=0;
    private int b=0;
    private int c=0;
    private int x=0;
    private int d=0;
    private int t1=0;
    private int i=0;
    private int lca =0;
    private int gj =0;
    private int plp=0;
    private int yuan=0;
    private int txta=0;
    private int caozuos=0;
    private boolean isSelect = false;
    Map<String,Object> obj=new HashMap<String,Object>();
    Map<String, String> mapplp = new HashMap<String, String>();
    Map<String, String> map1 = new LinkedHashMap<String, String>();
    Map<String, String> mapa = new LinkedHashMap<String, String>();
    //系统相机拍照
    private String fileNmae,fileNmae1;
    public static Handler handler ;
    private static String srcPath;
    //新增SHP模块
    private String shpPath;
    private View viewab,viewbb;
    private PopupWindow windowb,window1b;
    private LinearLayout backb,btntrueb,add,value,deleteb,update,back1,btntrue1b;
    private EditText shpname,name1b,big1,editc;
    private RadioGroup radiogroup,radiogroup1;
    private RadioButton point,line,ploy,zf1,zf2,zf3,zf4;
    private String shapename;
    private String status = "POINT";
    private Boolean addz = false;
    private String namea,biga;
    private int leixinga =ogr.OFTString;
    private String leixingb="字符串";
    private java.util.Map<String, Object> attributes = new HashMap<String, Object>();
    private int z=0;
    //listview
    private ViewGroup btnCancleb = null;
    private ViewGroup btnAddb = null;
    private Button btnSelectAllb = null;
    private LinearLayout btnDeleteb;
    private ListView lvListViewb ;
    List<Demo1Bean> demoDatasb = new ArrayList<Demo1Bean>();
    private Demo1Adapter adpAdapterb = new Demo1Adapter(this,demoDatasb);
    private TextView tvName,textc;
    private Context mContext;
    private ArcGISFeature mSelectedArcGISFeature;
    private int mp=0;
    private String namexx;
    private SimpleFillSymbol fillSymbol;
    private SimpleLineSymbol lineSymbol;
    private int zxc = 0;
    private Map<String, Object> attributesx;
    private ShapefileFeatureTable shapefileFeatureTable;
    private int bcolor = Color.YELLOW;
    private int lcolor = Color.RED;
    private int shuzi = 1;
    private String fid;
    private Feature feature;
    private int gongju=0;
    private MeasureToolView measureToolView;
    //切割合并模块
    private GraphicsOverlay mGraphicsOverlay;
    private PointCollection mPointCollection = new PointCollection(SpatialReference.create(4530));
    private SimpleLineSymbol simpleLineSymbol;
    private Graphic pointGraphic,graphicline,canadaSide,usSide;
    private Geometry geometry;
    private Graphic polygonGraphic,polygonGraphic1;
    private String json,json1;
    private List<Geometry> parts;
    private ArrayList parts1 = new ArrayList<>();//GraphicsOverlay所有图层
    private ArrayList parts2 = new ArrayList<>();//feature所有图层
    private Map<String, Object> mapx = new LinkedHashMap<>();
    private Map<String, Object> mapx1 = new LinkedHashMap<>();
    private Map<String, Object> mapxh = new LinkedHashMap<>();
    private Envelope envelope;
    private int caozuoa=0;
    private int caozuob=0;
    private int caozuoc=0;
    private int caozuod=1;
    private int baocunh=0;
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.mapView);
        measureToolView=(MeasureToolView)findViewById(R.id.measure_tool);
        mLocationDisplay = mMapView.getLocationDisplay();
        //layert = new Layert(this,MainActivity.this);
        ArcGISRuntimeEnvironment.setLicense("uKODxqYTOom1UXa9");
        String[] reqPermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        int requestCode = 2;
        if (ContextCompat.checkSelfPermission(this, reqPermission[0]) == PackageManager.PERMISSION_GRANTED) {
            mMapView.setAttributionTextVisible(false);
        } else {
            ActivityCompat.requestPermissions(this, reqPermission, requestCode);
        }
        FindViewUtils.getInstance(mContext).findViews(this, this);
        init();
        mGraphicsOverlay = new GraphicsOverlay();
        mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
        init1();
        initlocaltion();
        showLayer2();
        //initsjk();
        //gongjua();
    }
    public void gongjua(){
        measureToolView.init(mMapView);
        measureToolView.setButtonWidth(55);
        measureToolView.setButtonHeight(35);
        measureToolView.setMeasureBackground(R.color.colorAccent);
        measureToolView.setSohwText(true);
        measureToolView.setFontSize(12);
        measureToolView.setFontColor(R.color.color444);
        measureToolView.setMeasurePrevStr("撤销");
        measureToolView.setMeasureNextStr("恢复");
        measureToolView.setMeasureLengthStr("测距");
        measureToolView.setMeasureAreaStr("测面积");
        measureToolView.setMeasureClearStr("清除");
        measureToolView.setMeasureEndStr("完成");
        measureToolView.setMeasurePrevImage(R.drawable.sddman_measure_prev);
        measureToolView.setMeasureNextImage(R.drawable.sddman_measure_next);
        measureToolView.setMeasureLengthImage(R.drawable.sddman_measure_length);
        measureToolView.setMeasureAreaImage(R.drawable.sddman_measure_area);
        measureToolView.setMeasureClearImage(R.drawable.sddman_measure_clear);
        measureToolView.setMeasureEndImage(R.drawable.sddman_measure_end);
        measureToolView.setSpatialReference(SpatialReference.create(3857));
        measureToolView.setLengthType(Variable.Measure.KM);
        measureToolView.setAreaType(Variable.Measure.KM2);
    }
    public void initsjk(){
        String pathi=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
        SQLiteDatabase dbi = MySQLiteHelper.getDatabase(pathi);
        String sql = "select * from data ";//list是你的表名
        Cursor cursori = dbi.rawQuery(sql,new String[]{});
        if(cursori!= null){
            while(cursori.moveToNext()) {
                String namedata = cursori.getString(cursori.getColumnIndex("name"));
                String namedata1 = cursori.getString(cursori.getColumnIndex("bcolor"));
                String namedata2 = cursori.getString(cursori.getColumnIndex("lcolor"));
                String namedata3 = cursori.getString(cursori.getColumnIndex("kuan"));
                if(namedata1==null || namedata2==null || namedata3==null){
                    bcolor = Color.YELLOW;
                    lcolor = Color.RED;
                    shuzi = 1;
                }else{
                    bcolor = Integer.parseInt(namedata1);
                    lcolor = Integer.parseInt(namedata2);
                    shuzi = Integer.parseInt(namedata3);
                }
                pptPath = PathUtil.getStoragePath(MainActivity.this,true) +"/" + namedata + ".shp";
                showShapefile();
            }
            dbi.close();
            cursori.close();
        }else{

        }
    }
    public void initlocaltion(){
        mLocationDisplay.addDataSourceStatusChangedListener(new LocationDisplay.DataSourceStatusChangedListener() {
            @Override
            public void onStatusChanged(LocationDisplay.DataSourceStatusChangedEvent dataSourceStatusChangedEvent) {
                // If LocationDisplay started OK, then continue.
                if (dataSourceStatusChangedEvent.isStarted())
                    return;
                // No error is reported, then continue.
                if (dataSourceStatusChangedEvent.getError() == null)
                    return;
                // If an error is found, handle the failure to start.
                // Check permissions to see if failure may be due to lack of permissions.
                boolean permissionCheck1 = ContextCompat.checkSelfPermission(MainActivity.this, reqPermissions[0]) ==
                        PackageManager.PERMISSION_GRANTED;
                boolean permissionCheck2 = ContextCompat.checkSelfPermission(MainActivity.this, reqPermissions[1]) ==
                        PackageManager.PERMISSION_GRANTED;

                if (!(permissionCheck1 && permissionCheck2)) {
                    // If permissions are not already granted, request permission from the user.
                    ActivityCompat.requestPermissions(MainActivity.this, reqPermissions, REQUEST_CODE3);
                } else {
                    // Report other unknown failure types to the user - for example, location services may not
                    // be enabled on the device.
                    String message = String.format("Error in DataSourceStatusChangedListener: %s", dataSourceStatusChangedEvent
                            .getSource().getLocationDataSource().getError().getMessage());
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void init1() {
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                //缩放回调
                double mScale = mMapView.getMapScale();
                DecimalFormat df = new DecimalFormat("#####");
                String str = df.format(mScale);
                textscale.setText(str+"米");
                return super.onScale(detector);
            }
        });
    }
    /**
     * 初始化控件
     */
    private void initView() {
        lvListView = (ListView)viewa.findViewById(R.id.lvListView);
        lvListView.setOnItemClickListener(this);
    }
    /**
     * 初始化控件
     */
    private void initpopup1() {
          textname = (TextView)viewb.findViewById(R.id.textname);
          textname.setOnClickListener(this);
          textnamea = (TextView)viewb.findViewById(R.id.textnamea);
          textnamea.setOnClickListener(this);
          textnameb = (TextView)viewb.findViewById(R.id.textnameb);
          textnameb.setOnClickListener(this);
          textnamec = (TextView)viewb.findViewById(R.id.textnamec);
          textnamec.setOnClickListener(this);
          textnamed = (TextView)viewb.findViewById(R.id.textnamed);
          textnamed.setOnClickListener(this);
          textnamee = (TextView)viewb.findViewById(R.id.textnamee);
          textnamee.setOnClickListener(this);
          textnamef = (TextView)viewb.findViewById(R.id.textnamef);
          textnamef.setOnClickListener(this);
          textname1 = (EditText)viewb.findViewById(R.id.textname1);
          textname1.setOnClickListener(this);
          textnamea1 = (EditText)viewb.findViewById(R.id.textnamea1);
          textnamea1.setOnClickListener(this);
          textnameb1 = (EditText)viewb.findViewById(R.id.textnameb1);
          textnameb1.setOnClickListener(this);
          textnamec1 = (EditText)viewb.findViewById(R.id.textnamec1);
          textnamec1.setOnClickListener(this);
          textnamed1 = (EditText)viewb.findViewById(R.id.textnamed1);
          textnamed1.setOnClickListener(this);
          textnamee1 = (EditText)viewb.findViewById(R.id.textnamee1);
          textnamee1.setOnClickListener(this);
          textnamef1 = (EditText)viewb.findViewById(R.id.textnamef1);
          textnamef1.setOnClickListener(this);
          //delete = (LinearLayout)viewb.findViewById(R.id.delete);
          demo = (LinearLayout)viewb.findViewById(R.id.demo);
          info4 = (LinearLayout)viewb.findViewById(R.id.info4);
          area = (TextView)viewb.findViewById(R.id.area);
          edita1 = (LinearLayout)viewb.findViewById(R.id.edita1);
          back11 = (LinearLayout)viewb.findViewById(R.id.back);
          btntrue11 = (LinearLayout)viewb.findViewById(R.id.btntrue);
    }

    /**
     * 初始化视图
     */
    private void initData() {
            String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
            SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
            int number=0;
            Cursor c = db.rawQuery("select * from data", null);
            number=c.getCount();
            if(number==0){
                //模拟假数据
//                demoDatas.add(new DemoBean( "默认点层","point",true));
//                demoDatas.add(new DemoBean( "默认线层","line",true));
//                demoDatas.add(new DemoBean(  "默认面层","ploy",true));
//                lvListView.setAdapter(adpAdapter);
            }else{
                demoDatas.clear();
                String pathx=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                SQLiteDatabase dbx = MySQLiteHelper.getDatabase(pathx);
                String sql = "select * from data";//list是你的表名
                Cursor cursorx = dbx.rawQuery(sql,new String[]{});
                while(cursorx.moveToNext()) {
                    String namedata = cursorx.getString(cursorx.getColumnIndex("name"));
                    String pic = cursorx.getString(cursorx.getColumnIndex("pic"));
                    DemoBean dbe = new DemoBean(namedata,pic,true);
                    demoDatas.add(dbe);
                    lvListView.setAdapter(adpAdapter);
                }
                dbx.close();
                cursorx.close();
            }
            db.close();
            c.close();
    }
    public void init() {
        name1 = (LinearLayout) findViewById(R.id.name1);
        name1.setOnClickListener(this);
        name2 = (LinearLayout) findViewById(R.id.name2);
        name2.setOnClickListener(this);
        name4 = (LinearLayout) findViewById(R.id.name4);
        name4.setOnClickListener(this);
        name5 = (LinearLayout) findViewById(R.id.name5);
        name5.setOnClickListener(this);
        textscale = (TextView) findViewById(R.id.textscale);
        textscale.setOnClickListener(this);
        draw = (LinearLayout) findViewById(R.id.draw);
        drawpoint = (LinearLayout) findViewById(R.id.drawpoint);
        drawline = (LinearLayout) findViewById(R.id.drawline);
        draw1 = (LinearLayout) findViewById(R.id.draw1);
        draw1.setOnClickListener(this);
        change = (LinearLayout) findViewById(R.id.change);
        change.setOnClickListener(this);
        undo = (LinearLayout) findViewById(R.id.undo);
        undo.setOnClickListener(this);
        create = (LinearLayout) findViewById(R.id.create);
        create.setOnClickListener(this);
        delete_ljt = (LinearLayout) findViewById(R.id.delete_ljt);
        delete_ljt.setOnClickListener(this);
        draw1p = (LinearLayout) findViewById(R.id.draw1p);
        draw1p.setOnClickListener(this);
        changep = (LinearLayout) findViewById(R.id.changep);
        changep.setOnClickListener(this);
        undop = (LinearLayout) findViewById(R.id.undop);
        undop.setOnClickListener(this);
        createp = (LinearLayout) findViewById(R.id.createp);
        createp.setOnClickListener(this);
        delete_ljtp = (LinearLayout) findViewById(R.id.delete_ljtp);
        delete_ljtp.setOnClickListener(this);
        draw1l = (LinearLayout) findViewById(R.id.draw1l);
        draw1l.setOnClickListener(this);
        changel = (LinearLayout) findViewById(R.id.changel);
        changel.setOnClickListener(this);
        undol = (LinearLayout) findViewById(R.id.undol);
        undol.setOnClickListener(this);
        createl = (LinearLayout) findViewById(R.id.createl);
        createl.setOnClickListener(this);
        delete_ljtl = (LinearLayout) findViewById(R.id.delete_ljtl);
        delete_ljtl.setOnClickListener(this);
        caozuo = (LinearLayout) findViewById(R.id.caozuo);
        caozuo.setOnClickListener(this);
        infot = (LinearLayout) findViewById(R.id.infot);
        infot.setOnClickListener(this);
        deletet = (LinearLayout) findViewById(R.id.deletet);
        deletet.setOnClickListener(this);
        qiege = (LinearLayout) findViewById(R.id.qiege);
        qiege.setOnClickListener(this);
        hebing = (LinearLayout) findViewById(R.id.hebing);
        hebing.setOnClickListener(this);
        dline = (LinearLayout) findViewById(R.id.dline);
        dline.setOnClickListener(this);
        baocun = (LinearLayout) findViewById(R.id.baocun);
        baocun.setOnClickListener(this);
        img1 = (ImageView) findViewById(R.id.img1);
        img1.setOnClickListener(this);
        img2 = (ImageView) findViewById(R.id.img2);
        img2.setOnClickListener(this);
        img3 = (ImageView) findViewById(R.id.img3);
        img3.setOnClickListener(this);
        img4 = (ImageView) findViewById(R.id.img4);
        img4.setOnClickListener(this);
        img5 = (ImageView) findViewById(R.id.img5);
        img5.setOnClickListener(this);
        img6 = (ImageView) findViewById(R.id.img6);
        img6.setOnClickListener(this);
    }
    public void initpopup(){
        back = (LinearLayout)viewa.findViewById(R.id.back);
        demo = (LinearLayout)viewa.findViewById(R.id.demo);
        btntrue = (LinearLayout)viewa.findViewById(R.id.btntrue);
        coshp = (Button)viewa.findViewById(R.id.coshp);
        newlayer = (LinearLayout)viewa.findViewById(R.id.newlayer);
        rulayer = (LinearLayout)viewa.findViewById(R.id.rulayer);
    }
    //加载在线街区地图
    public void showLayer(){
        mServiceFeatureTable = new ServiceFeatureTable("https://sampleserver6.arcgisonline.com/arcgis/rest/services/USA/MapServer");
        // create the feature layer using the service feature table
        FeatureLayer mFeaturelayer = new FeatureLayer(mServiceFeatureTable);
        final ArcGISMap map = new ArcGISMap(Basemap.createTopographic());
        map.setInitialViewpoint(new Viewpoint(new Envelope(-1131596.019761, 3893114.069099, 3926705.982140, 7977912.461790,SpatialReferences.getWebMercator())));
        // set the map to be displayed in the mapview
        mMapView.setMap(map);
        //mMapView.setAttributionTextVisible(false);
        map.getOperationalLayers().add(mFeaturelayer);
    }
    //加载在线影像地图
    public void showLayer1(){
        ArcGISMap map = new ArcGISMap(Basemap.Type.IMAGERY, 5.0, 15.0, 2);
        // set the map to be displayed in this view
        mMapView.setMap(map);
        // hold a list of uniquely-identifying WMS layer names to display
        List<String> wmsLayerNames = new ArrayList<>();
        wmsLayerNames.add("0");
        // create a new WMS layer displaying the specified layers from the service
        WmsLayer wmsLayer = new WmsLayer(getString(R.string.sample_service_url1), wmsLayerNames);
        // add the layer to the map
        map.getOperationalLayers().add(wmsLayer);
    }
    //加载离线影像地图
    public void showLayer2(){
        String pptPath1 = PathUtil.getStoragePath(MainActivity.this,true)+"/测试数据/yx1.tif";
        Raster raster=new Raster(pptPath1);
        RasterLayer rasterLayer=new RasterLayer(raster);
        ArcGISMap map = new ArcGISMap();
        map.getOperationalLayers().add(rasterLayer);
        mMapView.setMap(map);
    }
    //弹出图层框()
    public void showpopupview(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewa = inflater.inflate(R.layout.popupwindow, null);
        //设置弹出框样式
        window = new PopupWindow(viewa, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window.setContentView(viewa);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        window.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        window.showAtLocation(viewa, Gravity.BOTTOM, 10, 10);
        initpopup();
        // 初始化视图
        initView();
        // 初始化控件
        initData();
        //本地栅格管理
        coshp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, FileChooserActivity1.class);
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        startActivityForResult(intent1, REQUEST_CODE1);
                    }else {
                        toast(getText(R.string.sdcard_unmonted_hint));
                    }
            }
        });
        rulayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, FileChooserActivity.class);
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        startActivityForResult(intent, REQUEST_CODE);
                    }else {
                        toast(getText(R.string.sdcard_unmonted_hint));
                    }
            }
        });
        btntrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
                db.execSQL("delete from data");
                ContentValues values=new ContentValues();
                for(int ix = 0;ix < demoDatas.size(); ix ++){
                    values.put("name",demoDatas.get(ix).getName());
                    values.put("pic",demoDatas.get(ix).getPic());
                    db.insert("data", null, values);
                }
                db.close();
                toast("保存成功");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        //新建shp图层
        newlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpopupviewb();
            }
        });

    }
    //弹出图层框
    public void showpopupviewb(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewab = inflater.inflate(R.layout.popupwindowb, null);
        //设置弹出框样式
        windowb = new PopupWindow(viewab, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        windowb.setContentView(viewab);
        windowb.setOutsideTouchable(true);
        windowb.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        windowb.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        windowb.showAtLocation(viewab, Gravity.BOTTOM, 10, 10);
        initpopupb();
        if(demoDatasb != null && demoDatasb.size() != 0){

        }else{
            initDatab();
        }
        backb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowb.dismiss();
            }
         });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId==R.id.point){
                    Toast.makeText(getApplicationContext(), "您选择的是点",Toast.LENGTH_SHORT).show();
                    status="POINT";
                }else if(checkedId==R.id.line){
                    Toast.makeText(getApplicationContext(), "您选择的是线", Toast.LENGTH_SHORT).show();
                    status="POLYLINE";
                }else if(checkedId==R.id.ploy){
                    Toast.makeText(getApplicationContext(), "您选择的是面", Toast.LENGTH_SHORT).show();
                    status="POLYGON";
                }
            }
        });
        btntrueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // 尝试写入shp文件，含属性内容
                    shapename = shpname.getText().toString();
                    if(shapename.isEmpty()) {
                        Toast.makeText(MainActivity.this, "请填写文件名称", Toast.LENGTH_LONG).show();
                    }else if(addz==false){
                        Toast.makeText(MainActivity.this, "请添加字段和字段值", Toast.LENGTH_LONG).show();
                    }else{
//                        FindViewUtils.getInstance(mContext).findViews(MainActivity.this, this);
//                        File projectFolder = AppPath.getAppProjectFolder(mContext);
//                        if (!projectFolder.exists()) {
//                            projectFolder.mkdirs();
//                        }
                        shpPath1 = PathUtil.getStoragePath(MainActivity.this,true)+"/"+shapename+".shp";
                    }
                    if(status.equals("POINT")){
                        writeShpPoint();  //创建点shape
                        //adpAdapter.add(new DemoBean("1","SHP1","点模板","point.jpg",true));
//                        adpAdapter.notifyDataSetChanged();
//                        lvListView.setAdapter(adpAdapter);
                    }else if(status.equals("POLYLINE")){
                        writeShpPolyline();//创建线shape
                        //adpAdapter.add(new DemoBean("2","SHP2","线模板","line.jpg",true));
//                        adpAdapter.notifyDataSetChanged();
//                        lvListView.setAdapter(adpAdapter);
                    }else if(status.equals("POLYGON")){
                        writeShpPolygon();//创建面shape
                        showShapefile1();
//                        if(gt1.equals(POINT)){
//                            adpAdapter.add(new DemoBean(namegeo1,"point",true));
//                            Collections.reverse(demoDatas);
//                            adpAdapter.notifyDataSetChanged();
//                            lvListView.setAdapter(adpAdapter);
//                        }else if(gt1.equals(POLYLINE)){
//                            adpAdapter.add(new DemoBean(namegeo1,"line",true));
//                            Collections.reverse(demoDatas);
//                            adpAdapter.notifyDataSetChanged();
//                            lvListView.setAdapter(adpAdapter);
//                        }else if(gt1.equals(POLYGON)){
                            adpAdapter.add(new DemoBean(shapename,"ploy",true));
                            Collections.reverse(demoDatas);
                            adpAdapter.notifyDataSetChanged();
                            lvListView.setAdapter(adpAdapter);
//                        }
                        windowb.dismiss();
                        toast("新建矢量图层成功");
                        //draw.setVisibility(VISIBLE);
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                showpopupview1b();
            }
        });
        yijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    public void run(){
                        String path1=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                        SQLiteDatabase db1 = MySQLiteHelper.getDatabase(path1);
                        if (db1.isOpen()) {
                            db1.execSQL("delete from list");
                            db1.close();
                        }
                    }
                }.start();
                final String zd1f= "QSDWMC";
                final String zd2f = "字符型";
                final String zd3f = "60";
                adpAdapterb.add(new Demo1Bean(zd1f,zd2f,zd3f,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1e= "QSDWDM";
                final String zd2e = "字符型";
                final String zd3e = "19";
                adpAdapterb.add(new Demo1Bean(zd1e,zd2e,zd3e,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1d= "DLMC";
                final String zd2d = "字符型";
                final String zd3d = "60";
                adpAdapterb.add(new Demo1Bean(zd1d,zd2d,zd3d,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1c= "DLBM";
                final String zd2c = "字符型";
                final String zd3c = "4";
                adpAdapterb.add(new Demo1Bean(zd1c,zd2c,zd3c,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1b= "YSDM";
                final String zd2b = "字符型";
                final String zd3b = "10";
                adpAdapterb.add(new Demo1Bean(zd1b,zd2b,zd3b,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1a= "BSM";
                final String zd2a = "长整型";
                final String zd3a = "9";
                adpAdapterb.add(new Demo1Bean(zd1a,zd2a,zd3a,true));
                adpAdapterb.notifyDataSetChanged();
                final String zd1= "FID";
                final String zd2 = "整型";
                final String zd3 = "50";
                adpAdapterb.add(new Demo1Bean(zd1,zd2,zd3,true));
                adpAdapterb.notifyDataSetChanged();

                new Thread(){
                    public void run(){
                        String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                        SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
                        ContentValues values=new ContentValues();
                        ContentValues values1=new ContentValues();
                        ContentValues values2=new ContentValues();
                        ContentValues values3=new ContentValues();
                        ContentValues values4=new ContentValues();
                        ContentValues values5=new ContentValues();
                        ContentValues values6=new ContentValues();
                        values.put("name",zd1);
                        values.put("leixing",zd2);
                        values.put("big",zd3);

                        values1.put("name",zd1a);
                        values1.put("leixing",zd2a);
                        values1.put("big",zd3a);

                        values2.put("name",zd1b);
                        values2.put("leixing",zd2b);
                        values2.put("big",zd3b);

                        values3.put("name",zd1c);
                        values3.put("leixing",zd2c);
                        values3.put("big",zd3c);

                        values4.put("name",zd1d);
                        values4.put("leixing",zd2d);
                        values4.put("big",zd3d);

                        values5.put("name",zd1e);
                        values5.put("leixing",zd2e);
                        values5.put("big",zd3e);

                        values6.put("name",zd1f);
                        values6.put("leixing",zd2f);
                        values6.put("big",zd3f);

                        db.insert("list", null, values);
                        db.insert("list", null, values1);
                        db.insert("list", null, values2);
                        db.insert("list", null, values3);
                        db.insert("list", null, values4);
                        db.insert("list", null, values5);
                        db.insert("list", null, values6);
                        db.close();
                    }
                }.start();
                addz=true;
            }
        });

    }
    public void initpopupb(){
        backb = (LinearLayout)viewab.findViewById(R.id.back);
        btntrueb = (LinearLayout)viewab.findViewById(R.id.btntrue);
        add = (LinearLayout)viewab.findViewById(R.id.add);
//      value = (LinearLayout)viewab.findViewById(R.id.value);
        deleteb = (LinearLayout)viewab.findViewById(R.id.delete);
        yijian = viewab.findViewById(R.id.yijian);
//      update = (LinearLayout)viewab.findViewById(R.id.update);
        shpname = (EditText)viewab.findViewById(R.id.shpname);
        radiogroup = (RadioGroup)viewab.findViewById(R.id.radiogroup);
        point = (RadioButton)viewab.findViewById(R.id.point);
        line = (RadioButton)viewab.findViewById(R.id.line);
        ploy = (RadioButton)viewab.findViewById(R.id.ploy);
        //listview列表
        btnDeleteb = (LinearLayout)viewab.findViewById(R.id.delete);
        btnDeleteb.setOnClickListener(this);
        lvListViewb = (ListView)viewab.findViewById(R.id.lvListView);
//      lvListViewb.setOnItemClickListener(this);
    }
    //弹出图层框
    public void showpopupview1b(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewbb = inflater.inflate(R.layout.popupwindow1b, null);
        //设置弹出框样式
        window1b = new PopupWindow(viewbb, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window1b.setContentView(viewbb);
        window1b.setOutsideTouchable(true);
        window1b.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        window1b.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        window1b.showAtLocation(viewbb, Gravity.BOTTOM, 10, 10);
        initpopup1b();
        btntrue1b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namea = name1b.getText().toString();
                biga = big1.getText().toString();
                if(namea.isEmpty()||biga.isEmpty()){
                    Toast.makeText(MainActivity.this, "名称或大小不能为空", Toast.LENGTH_LONG).show();
                }else{
                    adpAdapterb.add(new Demo1Bean(namea,leixingb,biga,true));
                    adpAdapterb.notifyDataSetChanged();
                    new Thread(){
                        public void run(){
                            String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                            SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
                            ContentValues values=new ContentValues();
                            values.put("name",namea);
                            values.put("leixing",leixingb);
                            values.put("big",biga);
                            db.insert("list", null, values);
                            db.close();
                        }
                    }.start();
                    window1b.dismiss();
                    addz=true;
                }
            }
        });
        radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if(checkedId==R.id.zf1){
                    Toast.makeText(getApplicationContext(), "您选择的是字符串",Toast.LENGTH_SHORT).show();
                    leixinga=ogr.OFTString;
                    leixingb="字符串";
                }else if(checkedId==R.id.zf2){
                    Toast.makeText(getApplicationContext(), "您选择的是整型", Toast.LENGTH_SHORT).show();
                    leixinga=ogr.OFTInteger;
                    leixingb="整型";
                }else if(checkedId==R.id.zf3){
                    Toast.makeText(getApplicationContext(), "您选择的是布尔型", Toast.LENGTH_SHORT).show();
                    leixinga=ogr.OFTReal;
                    leixingb="布尔型";
                }else if(checkedId==R.id.zf4){
                    Toast.makeText(getApplicationContext(), "您选择的是日期型", Toast.LENGTH_SHORT).show();
                    leixinga=ogr.OFTDate;
                    leixingb="日期型";
                }
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window1b.dismiss();
            }
        });
    }
    public void initpopup1b(){
        back1 = (LinearLayout)viewbb.findViewById(R.id.back1);
        btntrue1b = (LinearLayout)viewbb.findViewById(R.id.btntrue1);
        name1b = (EditText)viewbb.findViewById(R.id.name1);
        radiogroup1 = (RadioGroup)viewbb.findViewById(R.id.radiogroup1);
        zf1 = (RadioButton)viewbb.findViewById(R.id.zf1);
        zf2 = (RadioButton)viewbb.findViewById(R.id.zf2);
        zf3 = (RadioButton)viewbb.findViewById(R.id.zf3);
        zf4 = (RadioButton)viewbb.findViewById(R.id.zf4);
        big1 = (EditText)viewbb.findViewById(R.id.big1);
    }
    //弹出属性框
    public void showpopupview1(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewb = inflater.inflate(R.layout.popupwindow1, null);
        //设置弹出框样式
        window = new PopupWindow(viewb, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        window.setContentView(viewb);
        window.setOutsideTouchable(true);
        window.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        window.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        window.showAtLocation(viewb, Gravity.BOTTOM, 10, 10);
        initpopup1();
        double areaPolygon=GeometryEngine.area(envelope);
        area.setText(String.valueOf(areaPolygon));
        for(String key:mapx1.keySet()) {
            //System.out.println(key + "==>" + mapx1.get(key));
            if(key.equals("FID")){
                textname1.append(String.valueOf(mapx1.get(key)));
                fid = String.valueOf(mapx1.get("FID"));
            }else if(key.equals("BSM")){
                textnamea1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,mapx1.get(key));
            }else if(key.equals("YSDM")){
                textnameb1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,String.valueOf(mapx1.get(key)));
            }else if(key.equals("DLBM")){
                textnamec1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,String.valueOf(mapx1.get(key)));
            }else if(key.equals("DLMC")){
                textnamed1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,String.valueOf(mapx1.get(key)));
            }else if(key.equals("QSDWDM")){
                textnamee1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,String.valueOf(mapx1.get(key)));
            }else if(key.equals("QSDWMC")){
                textnamef1.append(String.valueOf(mapx1.get(key)));
                mapx.put(key,String.valueOf(mapx1.get(key)));
            }else if(key.equals("Id")||key.equals("BGRQ")||key.equals("OBJECTID") || String.valueOf(attributes.get(key)).equals("")){

            }else if(key.equals("TKXS") || key.equals("TBMJ")||key.equals("XZDWMJ")||key.equals("LXDWMJ")||key.equals("TKMJ")||key.equals("TBDLMJ")||key.equals("Shape_Leng")||key.equals("Shape_Area")){
                mapx.put(key,mapx1.get(key));
                Log.e("xyh" + key, "ga"+mapx1.get(key));
            }else{
                mapx.put(key,String.valueOf(mapx1.get(key)));
                Log.e("xyh1" + key, String.valueOf(mapx1.get(key)));
            }
        }
        back11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        btntrue11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = textnamea1.getText().toString();
                final String text1 = textnameb1.getText().toString();
                final String text2 = textnamec1.getText().toString();
                final String text3 = textnamed1.getText().toString();
                final String text4 = textnamee1.getText().toString();
                final String text5 = textnamef1.getText().toString();

                final ListenableFuture<FeatureQueryResult> selectResult = mainShapefileLayer.getSelectedFeaturesAsync();
                selectResult.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (Feature featurex : selectResult.get()) {
                                featurex.getAttributes().put("BSM", text);
                                featurex.getAttributes().put("YSDM", text1);
                                featurex.getAttributes().put("DLBM", text2);
                                featurex.getAttributes().put("DLMC", text3);
                                featurex.getAttributes().put("QSDWDM", text4);
                                featurex.getAttributes().put("QSDWMC", text5);
                                mainShapefileLayer.getFeatureTable().updateFeatureAsync(featurex);
                            }
                            toast("更改成功");
                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                });
                mainSketchEditor.stop();
                window.dismiss();
                //toast("保存成功");
            }
        });
        back11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//                String date = dateFormat.format(new Date());
                fileNmae = Environment.getExternalStorageDirectory().toString()+File.separator+"Pictures/"+fid+".jpg";
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(fileNmae)));
                startActivityForResult(it, REQUEST_CODE2);
            }
        });
        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileNmae1 = Environment.getExternalStorageDirectory().toString()+File.separator+"Pictures/"+fid+".jpg";
                File mFile=new File(fileNmae1);
                //若该文件存在
                if (mFile.exists()) {
                    showpopupviewzp();
                    Bitmap bitmap=BitmapFactory.decodeFile(fileNmae1);
                    imgzp.setImageBitmap(bitmap);
                }else{
                    toast("缺少图片");
                }
            }
        });
        edita1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txta==0){
                    //textname1.setEnabled(true);
                    textnamea1.setEnabled(true);
                    textnameb1.setEnabled(true);
                    textnamec1.setEnabled(true);
                    textnamed1.setEnabled(true);
                    textnamee1.setEnabled(true);
                    textnamef1.setEnabled(true);
                    txta=1;
                }else if(txta==1){
                    textnamea1.setEnabled(false);
                    textnameb1.setEnabled(false);
                    textnamec1.setEnabled(false);
                    textnamed1.setEnabled(false);
                    textnamee1.setEnabled(false);
                    textnamef1.setEnabled(false);
                    txta=0;
                }
            }
        });
    }
    public void showpopupviewzp(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewczp = inflater.inflate(R.layout.popupwindowzp, null);
        //设置弹出框样式
        windowczp = new PopupWindow(viewczp, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        windowczp.setContentView(viewczp);
        windowczp.setOutsideTouchable(true);
        windowczp.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        windowczp.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        windowczp.showAtLocation(viewczp,Gravity.BOTTOM, 10, 10);
        imgzp = (ImageView)viewczp.findViewById(R.id.imgzp);
    }
    private void initDatab() {
        //模拟假数据
        List<Demo1Bean> demoDatasb = new ArrayList<Demo1Bean>();
        adpAdapterb = new Demo1Adapter(this, demoDatasb);
        lvListViewb.setAdapter(adpAdapterb);
    }
    //加载面已存在的shp
    private void showShapefile() {
        File file = new File(pptPath);
        if (!file.exists()) {
            Toast.makeText(this, "shp文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        //加载方式1
        shapefileFeatureTable = new ShapefileFeatureTable(pptPath);
        shapefileFeatureTable.loadAsync();
        shapefileFeatureTable.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
                gt= shapefileFeatureTable.getGeometryType();
                namegeo = shapefileFeatureTable.getTableName();
                lista1 = shapefileFeatureTable.getFields();
                    mainShapefileLayer = new FeatureLayer(shapefileFeatureTable);
                if (mainShapefileLayer.getFullExtent() != null) {
                    mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
                } else {
                    mainShapefileLayer.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
                        }
                    });
                }
                mMapView.getMap().getOperationalLayers().add(mainShapefileLayer);
                //mapa.put(mainShapefileLayer,namegeo);
                //mainShapefileLayer.setVisible(false);
                startDrawing();
                plp = 1;
            }
        });
        if(gt.equals(POINT)){
            SimpleMarkerSymbol pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.SQUARE, Color.YELLOW, 8.0f);
            final SimpleRenderer renderer = new SimpleRenderer(pointSymbol);
            mainShapefileLayer.setRenderer(renderer);
            mainShapefileLayer.setSelectionColor(Color.GREEN);
            mainShapefileLayer.setSelectionWidth(5);
        }else if(gt.equals(POLYLINE)){
            SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.YELLOW, 2.0f);
            final SimpleRenderer renderer = new SimpleRenderer(lineSymbol);
            mainShapefileLayer.setRenderer(renderer);
            mainShapefileLayer.setSelectionColor(Color.GREEN);
            mainShapefileLayer.setSelectionWidth(5);
        }else if(gt.equals(POLYGON)){
            lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, lcolor, shuzi);
            fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, bcolor, lineSymbol);
            final SimpleRenderer renderer = new SimpleRenderer(fillSymbol);
            mainShapefileLayer.setRenderer(renderer);
            mainShapefileLayer.setSelectionColor(Color.GREEN);
            mainShapefileLayer.setSelectionWidth(5);
            mainShapefileLayer.setOpacity(0.5f);
        }

    }
    public void showpopupviewedit(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewc = inflater.inflate(R.layout.popupwindowedit, null);
        //设置弹出框样式
        windowc = new PopupWindow(viewc, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        windowc.setContentView(viewc);
        windowc.setOutsideTouchable(true);
        windowc.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        windowc.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        windowc.showAtLocation(viewc, Gravity.BOTTOM, 10, 10);
        initpopupc();
        final List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
        SQLiteDatabase db2 = MySQLiteHelper.getDatabase(path);
        String sql = "select * from list";//list是你的表名
        Cursor cursor = db2.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()) {
            int nameColumnIndex1 = cursor.getColumnIndex("name");
            final String nameae=cursor.getString(nameColumnIndex1);
            textc.append(nameae+"\n");
            editc.append("null"+"\n");
            z++;
        }
        db2.close();
        cursor.close();
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Layout layout = editc.getLayout();
                String text = editc.getText().toString();
                int start = 0;
                int end;
                //循环遍历打印每一行
                for (int i = 0; i < editc.getLineCount(); i++ ) {
                    end = layout.getLineEnd(i);
                    String line = text.substring(start, end); //指定行的内容
                    start = end;
                    attributes.put("haha",line);
                }
                final Feature addedFeature = mainShapefileLayer.getFeatureTable().createFeature(attributes,(Polygon)mainSketchEditor.getGeometry());
                final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature);
                Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                isSelect = true;
                mainSketchEditor.stop();
                windowc.dismiss();
                //删除表
                new Thread(){
                   public void run(){
                      String path1=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                      SQLiteDatabase db2 = MySQLiteHelper.getDatabase(path1);
                      if (db2.isOpen()) {
                          db2.execSQL("delete from list");
                          db2.close();
                      }
                   }
                }.start();
                System.out.println("\n删除原数据成功！\n");
            }
        });
    }
    public void initpopupc(){
        btnedit = (LinearLayout)viewc.findViewById(R.id.btnedit);
        textc = (TextView)viewc.findViewById(R.id.textc);
        editc = (EditText)viewc.findViewById(R.id.editc);
    }
    //加载新shp
    private void showShapefile1() {
        File file = new File(shpPath1);
        if (!file.exists()) {
            Toast.makeText(this, "shp文件", Toast.LENGTH_SHORT).show();
            return;
        }
        //加载方式1
        final ShapefileFeatureTable shapefileFeatureTable1 = new ShapefileFeatureTable(shpPath1);
        shapefileFeatureTable1.loadAsync();
        shapefileFeatureTable1.addDoneLoadingListener(new Runnable() {
            @Override
            public void run() {
//                gt1= shapefileFeatureTable1.getGeometryType();
//                namegeo1 = shapefileFeatureTable1.getTableName();
                mainShapefileLayer = new FeatureLayer(shapefileFeatureTable1);
                if (mainShapefileLayer.getFullExtent() != null) {
                    mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
                } else {
                    mainShapefileLayer.addDoneLoadingListener(new Runnable() {
                        @Override
                        public void run() {
                            //mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
                        }
                    });
                }
                mMapView.getMap().getOperationalLayers().add(mainShapefileLayer);
                startDrawing();
                plp=2;
            }
        });
        SimpleLineSymbol lineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.RED, 1.0f);
        SimpleFillSymbol fillSymbol = new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, Color.YELLOW, lineSymbol);
        SimpleRenderer renderer = new SimpleRenderer(fillSymbol);
        mainShapefileLayer.setRenderer(renderer);
        mainShapefileLayer.setSelectionColor(Color.GREEN);
        mainShapefileLayer.setSelectionWidth(5);
    }
    private void toast(CharSequence hint){
        Toast.makeText(this, hint , Toast.LENGTH_SHORT).show();
    }
    public void onActivityResult(int requestCode , int resultCode , Intent data){
        Log.v(TAG, "onActivityResult#requestCode:"+ requestCode  + "#resultCode:" +resultCode);
        if(resultCode == RESULT_CANCELED){
           toast(getText(R.string.open_file_none));
            return ;
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE2){
            Bitmap b = BitmapFactory.decodeFile(fileNmae);
            //拿到图片保存地址
            srcPath = fileNmae;
            Message msg = Message.obtain();
            msg.obj=srcPath;
            if (handler!=null){
                handler.sendMessage(msg);
            }
            File myCaptureFile =new File(fileNmae);
            try {
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    if(!myCaptureFile.getParentFile().exists()){
                        myCaptureFile.getParentFile().mkdirs();
                    }
                    BufferedOutputStream bos;
                    bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                    b.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                    bos.flush();
                    bos.close();
                    toast("拍照保存成功");
                    mMapView.setViewpointGeometryAsync(mainShapefileLayer.getFullExtent());
                }else{
                    Toast toast= Toast.makeText(this, "保存失败，SD卡无效", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE1){
            //栅格路径名
            pptPath1 = data.getStringExtra(EXTRA_FILE_CHOOSER);
            if(pptPath1!=null){
                showLayer2();
                toast("加载本地图层成功");
            }
        }
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            //获取路径名
            //矢量路径名
            pptPath = data.getStringExtra(EXTRA_FILE_CHOOSER);
            Log.v(TAG, "onActivityResult # pptPath : "+ pptPath );
            if(pptPath != null){
//                if(zxc){
//
//                }
                showShapefile();
                if(gt.equals(POINT)){
                    adpAdapter.add(new DemoBean(namegeo,"point",true));
                    Collections.reverse(demoDatas);
                    adpAdapter.notifyDataSetChanged();
                    lvListView.setAdapter(adpAdapter);
                }else if(gt.equals(POLYLINE)){
                    adpAdapter.add(new DemoBean(namegeo,"line",true));
                    Collections.reverse(demoDatas);
                    adpAdapter.notifyDataSetChanged();
                    lvListView.setAdapter(adpAdapter);
                }else if(gt.equals(POLYGON)){
                    adpAdapter.add(new DemoBean(namegeo,"ploy",true));
                    Collections.reverse(demoDatas);
                    adpAdapter.notifyDataSetChanged();
                    lvListView.setAdapter(adpAdapter);
                }
                toast("导入矢量图层成功");
            } else {
                toast(getText(R.string.open_file_failed));
            }
        }
    }
    public void saveDraw() {
        if (mainSketchEditor.getGeometry() != null) {
            java.util.Map<String, Object> attributes = new HashMap<String, Object>();
            attributes.put("NAME", "自己画的省份");
            Feature addedFeature = mainShapefileLayer.getFeatureTable().createFeature(attributes, (Polygon) mainSketchEditor.getGeometry());
            final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature);
            mainSketchEditor.stop();
        }
    }
    public void saveDrawploynew() {
        showpopupviewedit();
//        if (mainSketchEditor.getGeometry() != null) {
//            java.util.Map<String, Object> attributes = new HashMap<String, Object>();
//            for (String key : attributes.keySet()) {
//                attributes.put("qwe", "哈哈");
//            }
//            Feature addedFeature = mainShapefileLayer.getFeatureTable().createFeature(attributes, (Polygon) mainSketchEditor.getGeometry());
//            final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature);
//            mainSketchEditor.stop();
//        }
    }
    public void saveDrawployplp() {
        if (mainSketchEditor.getGeometry() != null) {
            java.util.Map<String, Object> attributes = new HashMap<String, Object>();
            if(gt.equals(POINT)){
                Feature addedFeature = mainShapefileLayer.getFeatureTable().createFeature(attributes, (Point) mainSketchEditor.getGeometry());
                final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature);
            }else if(gt.equals(POLYLINE)){
                Feature addedFeature1 = mainShapefileLayer.getFeatureTable().createFeature(attributes, (Polyline) mainSketchEditor.getGeometry());
                final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature1);
            }else if(gt.equals(POLYGON)){
                Feature addedFeature2 = mainShapefileLayer.getFeatureTable().createFeature(attributes, (Polygon) mainSketchEditor.getGeometry());
                final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature2);
            }
            mainSketchEditor.stop();
        }
        toast("保存成功");
    }
    public void saveDrawployplp1() {
        showpopupviewedit();
        toast("保存成功");
    }
    // 写入shp文件
    private void writeShpPoint() throws UnsupportedEncodingException {

        ogr.RegisterAll();
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");
        gdal.SetConfigOption("SHAPE_ENCODING", "CP936");
//      gdal.SetConfigOption("SHAPE_ENCODING", "UTF-8");
        String strDriverName = "ESRI Shapefile";
        org.gdal.ogr.Driver oDriver = ogr.GetDriverByName(strDriverName);
        if (oDriver == null) {
            System.out.println(strDriverName + " 驱动不可用！\n");
            return;
        }
        DataSource oDS = oDriver.CreateDataSource(shpPath1, null);
        if (oDS == null) {
            System.out.println("创建矢量文件【" + shpPath1 + "】失败！\n");
            return;
        }
        org.gdal.osr.SpatialReference osr = new org.gdal.osr.SpatialReference();
        osr.SetWellKnownGeogCS("WGS84");
        Layer oLayer = oDS.CreateLayer("TestPOINT", osr, ogr.wkbPoint, null);
        if (oLayer == null) {
            System.out.println("图层创建失败！\n");
            return;
        }
        // 下面创建属性表
        // 先创建一个叫FieldID的整型属性
        String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
        SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
        String sql = "select * from list";//list是你的表名
        Cursor cursor = db.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()) {
            int nameColumnIndex1 = cursor.getColumnIndex("name");
            String namea=cursor.getString(nameColumnIndex1);
            int nameColumnIndex2 = cursor.getColumnIndex("leixing");
            String leixingb=cursor.getString(nameColumnIndex2);
            if(leixingb=="字符串"){
                leixinga=ogr.OFTString;
            }else if(leixingb=="整型"){
                leixinga=ogr.OFTInteger;
            }else if(leixingb=="布尔型"){
                leixinga=ogr.OFTReal;
            }else if(leixingb=="日期型"){
                leixinga=ogr.OFTDate;
            }
            int nameColumnIndex3 = cursor.getColumnIndex("big");
            String biga=cursor.getString(nameColumnIndex3);
            FieldDefn oFieldID = new FieldDefn(namea,leixinga);
            oFieldID.SetWidth(Integer.parseInt(biga));
            oLayer.CreateField(oFieldID);
        }
        db.close();
        cursor.close();
        FeatureDefn oDefn = oLayer.GetLayerDefn();
        // 创建类型要素
        org.gdal.ogr.Feature oFeatureTriangle = new org.gdal.ogr.Feature(oDefn);
        org.gdal.ogr.Geometry geomTriangle = org.gdal.ogr.Geometry.CreateFromWkt("POINT");
        oFeatureTriangle.SetGeometry(geomTriangle);
        oLayer.CreateFeature(oFeatureTriangle);
        try {
            oLayer.SyncToDisk();
            oDS.SyncToDisk();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n点数据集创建完成！\n");
        new Thread(){
            public void run(){
                String path1=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                SQLiteDatabase db1 = MySQLiteHelper.getDatabase(path1);
                if (db1.isOpen()) {
                    db1.execSQL("delete from list");
                    db1.close();
                }
            }
        }.start();
        System.out.println("\n删除原数据成功！\n");
        windowb.dismiss();

    }
    private void writeShpPolyline() throws UnsupportedEncodingException {

        ogr.RegisterAll();
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");
        gdal.SetConfigOption("SHAPE_ENCODING", "UTF-8");

        String strDriverName = "ESRI Shapefile";
        org.gdal.ogr.Driver oDriver = ogr.GetDriverByName(strDriverName);
        if (oDriver == null) {
            System.out.println(strDriverName + " 驱动不可用！\n");
            return;
        }
        DataSource oDS = oDriver.CreateDataSource(shpPath, null);
        if (oDS == null) {
            System.out.println("创建矢量文件【" + shpPath + "】失败！\n");
            return;
        }
        org.gdal.osr.SpatialReference osr = new org.gdal.osr.SpatialReference();
        osr.SetWellKnownGeogCS("WGS84");
        Layer oLayer = oDS.CreateLayer("TestPolyline", osr, ogr.wkbLineString, null);
        if (oLayer == null) {
            System.out.println("图层创建失败！\n");
            return;
        }
        // 下面创建属性表
        // 先创建一个叫FieldID的整型属性
        String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
        SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
        String sql = "select * from list";//list是你的表名
        Cursor cursor = db.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()) {
            int nameColumnIndex1 = cursor.getColumnIndex("name");
            String namea=cursor.getString(nameColumnIndex1);
            int nameColumnIndex2 = cursor.getColumnIndex("leixing");
            String leixingb=cursor.getString(nameColumnIndex2);
            if(leixingb=="字符串"){
                leixinga=ogr.OFTString;
            }else if(leixingb=="整型"){
                leixinga=ogr.OFTInteger;
            }else if(leixingb=="布尔型"){
                leixinga=ogr.OFTReal;
            }else if(leixingb=="日期型"){
                leixinga=ogr.OFTDate;
            }
            int nameColumnIndex3 = cursor.getColumnIndex("big");
            String biga=cursor.getString(nameColumnIndex3);
            FieldDefn oFieldID = new FieldDefn(namea,leixinga);
            oFieldID.SetWidth(Integer.parseInt(biga));
            oLayer.CreateField(oFieldID);
        }
        db.close();
        cursor.close();
        FeatureDefn oDefn = oLayer.GetLayerDefn();
        // 创建类型要素
        org.gdal.ogr.Feature oFeatureTriangle = new org.gdal.ogr.Feature(oDefn);
        org.gdal.ogr.Geometry geomTriangle = org.gdal.ogr.Geometry.CreateFromWkt("POLYLINE");
        oFeatureTriangle.SetGeometry(geomTriangle);
        oLayer.CreateFeature(oFeatureTriangle);
        try {
            oLayer.SyncToDisk();
            oDS.SyncToDisk();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n点数据集创建完成！\n");
        new Thread(){
            public void run(){
                String path1=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
                SQLiteDatabase db1 = MySQLiteHelper.getDatabase(path1);
                if (db1.isOpen()) {
                    db1.execSQL("delete from list");
                    db1.close();
                }
            }
        }.start();
        System.out.println("\n删除原数据成功！\n");
    }
    private void writeShpPolygon() throws UnsupportedEncodingException {

        ogr.RegisterAll();
        gdal.SetConfigOption("GDAL_FILENAME_IS_UTF8", "NO");
        gdal.SetConfigOption("SHAPE_ENCODING", "UTF-8");

        String strDriverName = "ESRI Shapefile";
        org.gdal.ogr.Driver oDriver = ogr.GetDriverByName(strDriverName);
        if (oDriver == null) {
            System.out.println(strDriverName + " 驱动不可用！\n");
            return;
        }
        DataSource oDS = oDriver.CreateDataSource(shpPath1, null);
        if (oDS == null) {
            System.out.println("创建矢量文件【" + shpPath1 + "】失败！\n");
            return;
        }
        org.gdal.osr.SpatialReference osr = new org.gdal.osr.SpatialReference();
        osr.SetWellKnownGeogCS("WGS84");
        Layer oLayer = oDS.CreateLayer("TestPolygon", osr, ogr.wkbPolygon, null);
        if (oLayer == null) {
            System.out.println("图层创建失败！\n");
            return;
        }
        // 下面创建属性表
        // 先创建一个叫FieldID的整型属性
        String path=PathUtil.getStoragePath(MainActivity.this,true)+"/list.sqlite";
        SQLiteDatabase db = MySQLiteHelper.getDatabase(path);
        String sql = "select * from list";//list是你的表名
        Cursor cursor = db.rawQuery(sql,new String[]{});
        while(cursor.moveToNext()) {
            int nameColumnIndex1 = cursor.getColumnIndex("name");
            String namea=cursor.getString(nameColumnIndex1);
            int nameColumnIndex2 = cursor.getColumnIndex("leixing");
            String leixingb=cursor.getString(nameColumnIndex2);
            if(leixingb=="字符型"){
                leixinga=ogr.OFTString;
            }else if(leixingb=="整型"){
                leixinga=ogr.OFTInteger;
            }else if(leixingb=="布尔型"){
                leixinga=ogr.OFTReal;
            }else if(leixingb=="日期型"){
                leixinga=ogr.OFTDate;
            }
            int nameColumnIndex3 = cursor.getColumnIndex("big");
            String biga=cursor.getString(nameColumnIndex3);
            FieldDefn oFieldID = new FieldDefn(namea,leixinga);
            oFieldID.SetWidth(Integer.parseInt(biga));
            oLayer.CreateField(oFieldID);
        }
        db.close();
        cursor.close();
        FeatureDefn oDefn = oLayer.GetLayerDefn();
        // 创建类型要素
        org.gdal.ogr.Feature oFeature= new org.gdal.ogr.Feature(oDefn);
        oFeature.SetField(0, "初始值");
        org.gdal.ogr.Geometry geomTriangle = org.gdal.ogr.Geometry.CreateFromWkt("POLYGON ((0 0,20 0,10 15,0 0))");
        oFeature.SetGeometry(geomTriangle);
        oLayer.CreateFeature(oFeature);
        try {
            oLayer.SyncToDisk();
            oDS.SyncToDisk();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n面数据集创建完成！\n");
    }
    //增加画点线面保存
    public void showpopupviewplp(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        viewplp = inflater.inflate(R.layout.popupwindowplp, null);
        //设置弹出框样式
        windowplp = new PopupWindow(viewplp, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        windowplp.setContentView(viewplp);
        windowplp.setOutsideTouchable(true);
        windowplp.setFocusable(true);
        //window.setFocusable(true); // 设置PopupWindow可获得焦点
        windowplp.setTouchable(true); // 设置PopupWindow可触摸
        // 实例化一个ColorDrawable颜色为半透明
        windowplp.showAtLocation(viewplp, Gravity.BOTTOM, 10, 10);
        initpopupplp();
        backplp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                windowplp.dismiss();
            }
        });
    }
    public void initpopupplp(){
       backplp=(LinearLayout)viewplp.findViewById(R.id.backplp);
       btntrueplp=(LinearLayout)viewplp.findViewById(R.id.btntrueplp);
       textplp=(TextView)viewplp.findViewById(R.id.textplp);
        for (int i = 0; i < lista1.size(); i++) {
             textplp.append(lista1.get(i).toString()+"\n");
        }
    }
    //画线
    private void drawPolyline() {
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Point point = mMapView.screenToLocation(new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY())));
                mPointCollection.add(point);
                Polyline polyline = new Polyline(mPointCollection);
                //点
                SimpleMarkerSymbol simpleMarkerSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.RED, 10);
                pointGraphic = new Graphic(point, simpleMarkerSymbol);
                mGraphicsOverlay.getGraphics().add(pointGraphic);
                //线
                simpleLineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.parseColor("#FC8145"), 3);
                graphicline = new Graphic(polyline, simpleLineSymbol);
                mGraphicsOverlay.getGraphics().add(graphicline);
                caozuoa=1;
                caozuob=1;
                return super.onSingleTapConfirmed(e);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name1:
                showpopupview();
                break;
            case R.id.name2:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else if (gt == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载矢量图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    if (caozuos == 0) {
                        isSelect = true;
                        mainSketchEditor.stop();
                        caozuo.setVisibility(View.VISIBLE);
                        name2.setBackgroundColor(Color.parseColor("#FF8C00"));
                        caozuos = 1;
                    } else {
                        if (mGraphicsOverlay != null) {
                            ListenableList<Graphic> graphics = mGraphicsOverlay.getGraphics();
                            if (graphics.size() > 0) {
                                graphics.removeAll(graphics);
                            }
                        }
                        isSelect = false;
                        caozuo.setVisibility(View.INVISIBLE);
                        name2.setBackgroundColor(Color.parseColor("#FFFAFA"));
                        caozuos = 0;
                    }

                }
                break;
//            case R.id.name3:
//                if(mMapView.getMap() == null) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setTitle("系统提示");
//                    builder.setMessage("请加载底图图层");
//                    builder.setPositiveButton("确定", null);
//                    builder.show();
//                }else if(gt==null){
//                    AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
//                    builder.setTitle("系统提示" ) ;
//                    builder.setMessage("请加载矢量图层" ) ;
//                    builder.setPositiveButton("确定" ,  null );
//                    builder.show();
//                }else {
//                    isSelect = true;
//                    mainSketchEditor.stop();
//                }
//                break;
            case R.id.name4:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else if (gt == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载矢量图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    if (gj == 0) {
                        if (gt.equals(POINT)) {
                            drawpoint.setVisibility(View.VISIBLE);
                            drawline.setVisibility(View.INVISIBLE);
                            draw.setVisibility(View.INVISIBLE);
                        } else if (gt.equals(POLYLINE)) {
                            drawpoint.setVisibility(View.INVISIBLE);
                            drawline.setVisibility(View.VISIBLE);
                            draw.setVisibility(View.INVISIBLE);
                        } else if (gt.equals(POLYGON)) {
                            drawpoint.setVisibility(View.INVISIBLE);
                            drawline.setVisibility(View.INVISIBLE);
                            draw.setVisibility(View.VISIBLE);
                        }
                        gj = 1;
                    } else {
                        drawpoint.setVisibility(View.INVISIBLE);
                        drawline.setVisibility(View.INVISIBLE);
                        draw.setVisibility(View.INVISIBLE);
                        gj = 0;
                    }
                }
                break;
            case R.id.name5:
                if (gongju == 0) {
                    measureToolView.setVisibility(View.VISIBLE);
                    gongjua();
                    gongju = 1;
                } else {
                    measureToolView.setVisibility(View.INVISIBLE);
                    //measureToolView.setMeasureClearStr("清除");
                    gongju = 0;
                    init1();
                    startDrawing();
                }
                break;
            case R.id.draw1:
                isSelect = false;
                mainShapefileLayer.clearSelection();
                mainSketchEditor.stop();
                mainSketchEditor.start(SketchCreationMode.POLYGON);
                break;
            case R.id.change:
                if (mainSketchEditor.canUndo()) {
                    mainSketchEditor.undo();
                }
                break;
            case R.id.undo:
                if (mainSketchEditor.canRedo()) {
                    mainSketchEditor.redo();
                }
                break;
            case R.id.create:
                //startDrawingnewpoly();
                //showpopupviewedit();
                if (plp == 1) {
                    saveDrawployplp();
                } else if (plp == 2) {
                    saveDrawployplp1();
                }
                plp = 0;
                //showpopupviewplp();
                //startDrawing1();
                break;
            case R.id.delete_ljt:
                final ListenableFuture<FeatureQueryResult> selectResult = mainShapefileLayer.getSelectedFeaturesAsync();
                selectResult.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResult.get());
                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                });
                break;
            case R.id.draw1p:
                isSelect = false;
                mainShapefileLayer.clearSelection();
                mainSketchEditor.stop();
                mainSketchEditor.start(SketchCreationMode.POINT);
                break;
            case R.id.changep:
                if (mainSketchEditor.canUndo()) {
                    mainSketchEditor.undo();
                }
                break;
            case R.id.undop:
                if (mainSketchEditor.canRedo()) {
                    mainSketchEditor.redo();
                }
                break;
            case R.id.createp:
                //startDrawingnewpoly();
                //showpopupviewedit();
                saveDrawployplp();
                break;
            case R.id.delete_ljtp:
                final ListenableFuture<FeatureQueryResult> selectResult1 = mainShapefileLayer.getSelectedFeaturesAsync();
                selectResult1.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResult1.get());
                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                });
                break;
            case R.id.draw1l:
                isSelect = false;
                mainShapefileLayer.clearSelection();
                mainSketchEditor.stop();
                mainSketchEditor.start(SketchCreationMode.POLYLINE);
                break;
            case R.id.changel:
                if (mainSketchEditor.canUndo()) {
                    mainSketchEditor.undo();
                }
                break;
            case R.id.undol:
                if (mainSketchEditor.canRedo()) {
                    mainSketchEditor.redo();
                }
                break;
            case R.id.createl:
                //showpopupviewplp();
                //startDrawing1();
                saveDrawployplp();
                break;
            case R.id.delete_ljtl:
                final ListenableFuture<FeatureQueryResult> selectResult2 = mainShapefileLayer.getSelectedFeaturesAsync();
                selectResult2.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResult2.get());
                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                });
                break;
            case R.id.img1:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    ArcGISMap map = new ArcGISMap(Basemap.Type.IMAGERY, 5.0, 15.0, 2);
                    // set the map to be displayed in this view
                    mMapView.setMap(map);
                }
                break;
            case R.id.img2:
                if (i == 0) {
                    img2.setImageResource(R.drawable.v1_view_left);
                    img1.setVisibility(View.INVISIBLE);
                    img5.setImageResource(R.drawable.v1_pan);
                    img4.setVisibility(View.INVISIBLE);
                    img3.setVisibility(View.INVISIBLE);
                    img6.setVisibility(View.INVISIBLE);
                    i = 1;
                } else {
                    img1.setVisibility(VISIBLE);
                    img2.setImageResource(R.drawable.v1_view_right);
                    img3.setVisibility(VISIBLE);
                    img4.setVisibility(VISIBLE);
                    img5.setImageResource(R.drawable.m_zoomoutx);
                    img6.setVisibility(VISIBLE);
                    i = 0;
                }
                break;
            case R.id.img3:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    double mScale = mMapView.getMapScale();
                    mMapView.setViewpointScaleAsync(mScale * 0.5);
                }
                break;
            case R.id.img4:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    if (lca == 0) {
                        mLocationDisplay.setAutoPanMode(LocationDisplay.AutoPanMode.RECENTER);
                        if (!mLocationDisplay.isStarted())
                            mLocationDisplay.startAsync();
                        img4.setImageResource(R.drawable.m_zoom_gps_unlock);
                        lca = 1;
                    } else {
                        if (mLocationDisplay.isStarted())
                            mLocationDisplay.stop();
                        lca = 0;
                        img4.setImageResource(R.drawable.m_zoom_gps_lock);
                    }
                }
                break;
            case R.id.img5:
                if (mMapView.getMap() == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("系统提示");
                    builder.setMessage("请加载底图图层");
                    builder.setPositiveButton("确定", null);
                    builder.show();
                } else {
                    double mScale1 = mMapView.getMapScale();
                    mMapView.setViewpointScaleAsync(mScale1 * 2);
                }
                break;
            case R.id.img6:
                break;
            case R.id.infot:
                showpopupview1();
                break;
            case R.id.deletet:
                final ListenableFuture<FeatureQueryResult> selectResultt = mainShapefileLayer.getSelectedFeaturesAsync();
                selectResultt.addDoneListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResultt.get());
                            toast("删除成功");
                            //window.dismiss();
                        } catch (Exception e) {
                            e.getCause();
                        }
                    }
                });
                break;
            case R.id.qiege:
                caozuoc = 1;
                if (caozuoa == 0) {
                    toast("请选择面或画线");
                } else {
                    parts = GeometryEngine.cut(polygonGraphic.getGeometry(), (Polyline) graphicline.getGeometry());
                    canadaSide = new Graphic(parts.get(0), new SimpleFillSymbol(SimpleFillSymbol.Style.BACKWARD_DIAGONAL,
                            0xFF00FF00, new SimpleLineSymbol(SimpleLineSymbol.Style.NULL, 0xFFFFFFFF, 0)));
                    usSide = new Graphic(parts.get(1), new SimpleFillSymbol(SimpleFillSymbol.Style.FORWARD_DIAGONAL,
                            0xFFFFFF00, new SimpleLineSymbol(SimpleLineSymbol.Style.NULL, 0xFFFFFFFF, 0)));
                    mGraphicsOverlay.getGraphics().addAll(Arrays.asList(canadaSide, usSide));
                }
                break;
            case R.id.hebing:
                startDrawing1();
                hebing.setBackgroundColor(Color.parseColor("#FF8C00"));
                caozuoc = 2;
                break;
            case R.id.dline:
                drawPolyline();
                break;
            case R.id.baocun:
                mGraphicsOverlay.getGraphics().clear();
                if (caozuoc == 1) {
                    if (caozuob == 0) {
                        toast("请正确切割");
                    } else {
                        final ListenableFuture<FeatureQueryResult> selectResultq = mainShapefileLayer.getSelectedFeaturesAsync();
                        selectResultq.addDoneListener(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResultq.get());
                                } catch (Exception e) {
                                    e.getCause();
                                }
                            }
                        });
                        mGraphicsOverlay.getGraphics().clear();
                        mPointCollection.clear();
                        final Graphic polygon1 = new Graphic(parts.get(0),
                                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x220000FF,
                                        new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 2)));
                        //mGraphicsOverlay.getGraphics().add(polygon1);
                        final Graphic polygon2 = new Graphic(parts.get(1),
                                new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x220000FF,
                                        new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 2)));
                        //feature.getAttributes().put("NAME", "测试点");
                        Feature addedFeature = mainShapefileLayer.getFeatureTable().createFeature(mapx1, parts.get(0));
                        final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature);
                        Feature addedFeature1 = mainShapefileLayer.getFeatureTable().createFeature(mapx1, parts.get(1));
                        final ListenableFuture<Void> addFeatureFuture1 = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeature1);
                        startDrawing();
                        caozuoa = 0;
                        caozuob = 0;
                    }
                } else if (caozuoc == 2) {
                    mGraphicsOverlay.getGraphics().clear();
                    Geometry geometryh = GeometryEngine.union(parts1);
                    //mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(geometryh.getExtent());
                    Graphic graphich = new Graphic(geometryh,
                            new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x220000FF,
                                    new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 2)));
                    mGraphicsOverlay.getGraphics().add(graphich);
                    mGraphicsOverlay.getGraphics().clear();
                    Feature addedFeaturex = mainShapefileLayer.getFeatureTable().createFeature(mapxh,geometryh);
                    final ListenableFuture<Void> addFeatureFuture = mainShapefileLayer.getFeatureTable().addFeatureAsync(addedFeaturex);
                    baocunh=1;
                    }
                    break;
                }
        }
        //画面
        public void startDrawing(){
            try {
                mainSketchEditor = new SketchEditor();
                mainSketchStyle = new SketchStyle();
                mainSketchEditor.setSketchStyle(mainSketchStyle);
                mMapView.setSketchEditor(mainSketchEditor);

                mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        if (isSelect == true) {
                            mainShapefileLayer.clearSelection();
                            final Point clickPoint = mMapView.screenToLocation(new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY())));
                            int tolerance = 1;
                            double mapTolerance = tolerance * mMapView.getUnitsPerDensityIndependentPixel();
                            SpatialReference spatialReference = mMapView.getSpatialReference();
                            envelope = new Envelope(clickPoint.getX() - mapTolerance, clickPoint.getY() - mapTolerance,
                                    clickPoint.getX() + mapTolerance, clickPoint.getY() + mapTolerance, spatialReference);
                            QueryParameters query = new QueryParameters();
                            query.setGeometry(envelope);
                            query.setSpatialRelationship(QueryParameters.SpatialRelationship.WITHIN);
                            final ListenableFuture<FeatureQueryResult> future = mainShapefileLayer.selectFeaturesAsync(query, FeatureLayer.SelectionMode.NEW);
                            future.addDoneListener(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        FeatureQueryResult result = future.get();
                                        Iterator<Feature> iterator = result.iterator();
                                        int counter = 0;
                                        mMapView.setViewpointCenterAsync(clickPoint);
                                        //showpopupview1();
                                        mapx.clear();
                                        mapx1.clear();
                                        while (iterator.hasNext()) {
                                            counter++;
                                            feature = iterator.next();
                                            attributesx = feature.getAttributes();
                                            for (String key : attributesx.keySet()) {
                                                mapx1.put(key, attributesx.get(key));
                                            }
                                            //高亮显示选中区域
                                            mainShapefileLayer.selectFeature(feature);
                                            geometry = feature.getGeometry();
                                            json = geometry.toJson();
                                            Log.e("xyh2", json);

                                            if (mGraphicsOverlay != null) {
                                                ListenableList<Graphic> graphics = mGraphicsOverlay.getGraphics();
                                                if (graphics.size() > 0) {
                                                    graphics.removeAll(graphics);
                                                }
                                            }
                                            polygonGraphic = new Graphic(Geometry.fromJson(json),
                                                    new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x220000FF,
                                                            new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 2)));
                                            mGraphicsOverlay.getGraphics().add(polygonGraphic);
                                            mMapView.setViewpointGeometryAsync(geometry.getExtent());
//                                        double areaPolygon=GeometryEngine.area(envelope);
//                                        area.setText(String.valueOf(areaPolygon));
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        //缩放回调
                        double mScale = mMapView.getMapScale();
                        DecimalFormat df = new DecimalFormat("#####");
                        String str = df.format(mScale);
                        textscale.setText(str + "米");
                        return super.onScale(detector);
                    }
                });
            } catch (Exception e) {
                e.getCause();
            }
        }
        public void startDrawing1(){
            try {
                mainSketchEditor = new SketchEditor();
                mainSketchStyle = new SketchStyle();
                mainSketchEditor.setSketchStyle(mainSketchStyle);
                mMapView.setSketchEditor(mainSketchEditor);

                mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        if (isSelect == true) {
                            caozuo.setVisibility(View.VISIBLE);
                            mainShapefileLayer.clearSelection();
                            final Point clickPoint = mMapView.screenToLocation(new android.graphics.Point(Math.round(e.getX()), Math.round(e.getY())));
                            int tolerance = 1;
                            double mapTolerance = tolerance * mMapView.getUnitsPerDensityIndependentPixel();
                            SpatialReference spatialReference = mMapView.getSpatialReference();
                            envelope = new Envelope(clickPoint.getX() - mapTolerance, clickPoint.getY() - mapTolerance,
                                    clickPoint.getX() + mapTolerance, clickPoint.getY() + mapTolerance, spatialReference);
                            QueryParameters query = new QueryParameters();
                            query.setGeometry(envelope);
                            query.setSpatialRelationship(QueryParameters.SpatialRelationship.WITHIN);
                            final ListenableFuture<FeatureQueryResult> future = mainShapefileLayer.selectFeaturesAsync(query, FeatureLayer.SelectionMode.NEW);
                            future.addDoneListener(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        FeatureQueryResult result = future.get();
                                        Iterator<Feature> iterator = result.iterator();
                                        int counter = 0;
                                        mapxh.clear();
                                        parts2.add(future);
                                        mMapView.setViewpointCenterAsync(clickPoint);
                                        while (iterator.hasNext()) {
                                            counter++;
                                            feature = iterator.next();
                                            attributesx = feature.getAttributes();
                                            for (String key : attributesx.keySet()) {
                                                mapxh.put(key, attributesx.get(key));
                                            }
                                            //高亮显示选中区域
                                            mainShapefileLayer.selectFeature(feature);
                                            geometry = feature.getGeometry();
                                            json = geometry.toJson();
                                            Log.e("xyh2", json);
                                            polygonGraphic = new Graphic(Geometry.fromJson(json),
                                                    new SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, 0x220000FF,
                                                            new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, 0xFF0000FF, 2)));
                                            mGraphicsOverlay.getGraphics().add(polygonGraphic);
                                            mMapView.setViewpointGeometryAsync(geometry.getExtent());
                                            parts1.add(Geometry.fromJson(json));
                                            if(baocunh==0){
                                            final ListenableFuture<FeatureQueryResult> selectResultq = mainShapefileLayer.getSelectedFeaturesAsync();
                                            selectResultq.addDoneListener(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        mainShapefileLayer.getFeatureTable().deleteFeaturesAsync(selectResultq.get());
                                                    } catch (Exception e) {
                                                        e.getCause();
                                                    }
                                                }
                                            });
                                            }else{

                                            }
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        //缩放回调
                        double mScale = mMapView.getMapScale();
                        DecimalFormat df = new DecimalFormat("#####");
                        String str = df.format(mScale);
                        textscale.setText(str + "米");
                        return super.onScale(detector);
                    }
                });
            } catch (Exception e) {
                e.getCause();
            }
        }
        /**
         * 判断时间格式
         * @param date
         * @return
         */
        public static String getDatetimeFormat (String date){
            date = date.trim();
            String a1 = "[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}";//yyyyMMddHHmmss
            String a2 = "[0-9]{4}[0-9]{2}[0-9]{2}";//yyyyMMdd
            String a3 = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";//yyyy-MM-dd HH:mm:ss
            String a4 = "[0-9]{4}-[0-9]{2}-[0-9]{2}";//yyyy-MM-dd
            String a5 = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";//yyyy-MM-dd  HH:mm
            boolean datea1 = Pattern.compile(a1).matcher(date).matches();
            if (datea1) {
                return "yyyyMMddHHmmss";
            }
            boolean datea2 = Pattern.compile(a2).matcher(date).matches();
            if (datea2) {
                return "yyyyMMdd";
            }
            boolean datea3 = Pattern.compile(a3).matcher(date).matches();
            if (datea3) {
                return "yyyy-MM-dd HH:mm:ss";
            }
            boolean datea4 = Pattern.compile(a4).matcher(date).matches();
            if (datea4) {
                return "yyyy-MM-dd";
            }
            boolean datea5 = Pattern.compile(a5).matcher(date).matches();
            if (datea5) {
                return "yyyy-MM-dd HH:mm";
            }
            return "";
    }

    @Override
    public void onItemClick(AdapterView<?> listView, View itemLayout, int position, long id) {
        lvListView.setItemChecked(position, true);
        lvListView.setSelector(android.R.color.holo_orange_light);
        if (itemLayout.getTag() instanceof DemoAdapter.ViewHolder) {
            DemoAdapter.ViewHolder holder = (DemoAdapter.ViewHolder) itemLayout.getTag();
            // 会自动触发CheckBox的checked事件
            holder.cbCheck.toggle();
            mainShapefileLayer.setVisible(true);
        }
    }

    @Override
    public void onclick(View v, int id) {
        mMapView.getMap().getOperationalLayers().clear();
    }

    @Override
    public void onclick1(View v, int color, int pos1) {
        fillSymbol.setColor(color);
    }

    @Override
    public void onclick2(View v, int color, int pos2) {
        lineSymbol.setColor(color);
    }

    @Override
    public void onclick3(View v, int shuzi, int pos3) {
        lineSymbol.setWidth(shuzi);
    }

    @Override
    public void onclick4(View v, int tou, int pos4) {
        if (tou == 1) {
            mainShapefileLayer.setOpacity(0.1f);
        } else if (tou == 2) {
            mainShapefileLayer.setOpacity(0.2f);
        } else if (tou == 3) {
            mainShapefileLayer.setOpacity(0.3f);
        } else if (tou == 4) {
            mainShapefileLayer.setOpacity(0.4f);
        } else if (tou == 5) {
            mainShapefileLayer.setOpacity(0.5f);
        } else if (tou == 6) {
            mainShapefileLayer.setOpacity(0.6f);
        } else if (tou == 7) {
            mainShapefileLayer.setOpacity(0.7f);
        } else if (tou == 8) {
            mainShapefileLayer.setOpacity(0.8f);
        } else if (tou == 9) {
            mainShapefileLayer.setOpacity(0.9f);
        } else if (tou == 10) {
            mainShapefileLayer.setOpacity(1f);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}