package login.example.com.myapplication6;

import com.esri.arcgisruntime.geometry.Envelope;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReference;

import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final List<String> SubDomain = Arrays.asList(new String [] {"t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7"});
    public static final String URL_VECTOR_2000 = "http://{subDomain}.tianditu.com/DataServer?T=vec_c&x={col}&y={row}&l={level}";
    public static final String URL_VECTOR_ANNOTATION_CHINESE_2000 = "http://{subDomain}.tianditu.com/DataServer?T=cva_c&x={col}&y={row}&l={level}";
    public static final String URL_VECTOR_ANNOTATION_ENGLISH_2000 = "http://{subDomain}.tianditu.com/DataServer?T=eva_c&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_2000 = "http://{subDomain}.tianditu.com/DataServer?T=img_c&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_ANNOTATION_CHINESE_2000 = "http://{subDomain}.tianditu.com/DataServer?T=cia_c&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_ANNOTATION_ENGLISH_2000 = "http://{subDomain}.tianditu.com/DataServer?T=eia_c&x={col}&y={row}&l={level}";
    public static final String URL_TERRAIN_2000 = "http://{subDomain}.tianditu.com/DataServer?T=ter_c&x={col}&y={row}&l={level}";
    public static final String URL_TERRAIN_ANNOTATION_CHINESE_2000 = "http://{subDomain}.tianditu.com/DataServer?T=cta_c&x={col}&y={row}&l={level}";

    public static final String URL_VECTOR_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=vec_w&x={col}&y={row}&l={level}";
    public static final String URL_VECTOR_ANNOTATION_CHINESE_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=cva_w&x={col}&y={row}&l={level}";
    public static final String URL_VECTOR_ANNOTATION_ENGLISH_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=eva_w&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=img_w&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_ANNOTATION_CHINESE_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=cia_w&x={col}&y={row}&l={level}";
    public static final String URL_IMAGE_ANNOTATION_ENGLISH_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=eia_w&x={col}&y={row}&l={level}";
    public static final String URL_TERRAIN_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=ter_w&x={col}&y={row}&l={level}";
    public static final String URL_TERRAIN_ANNOTATION_CHINESE_MERCATOR = "http://{subDomain}.tianditu.com/DataServer?T=cta_w&x={col}&y={row}&l={level}";

    public static final int DPI = 96;
    public static final int minZoomLevel = 1;
    public static final int maxZoomLevel = 18;
    public static final int tileWidth = 256;
    public static final int tileHeight = 256;
    public static final String LAYER_NAME_VECTOR = "vec";
    public static final String LAYER_NAME_VECTOR_ANNOTATION_CHINESE = "cva";
    public static final String LAYER_NAME_VECTOR_ANNOTATION_ENGLISH = "eva";
    public static final String LAYER_NAME_IMAGE = "img";
    public static final String LAYER_NAME_IMAGE_ANNOTATION_CHINESE = "cia";
    public static final String LAYER_NAME_IMAGE_ANNOTATION_ENGLISH = "eia";
    public static final String LAYER_NAME_TERRAIN = "ter";
    public static final String LAYER_NAME_TERRAIN_ANNOTATION_CHINESE = "cta";

    public static final SpatialReference SRID_2000 = SpatialReference.create(4490);
    public static final SpatialReference SRID_MERCATOR = SpatialReference.create(102100);
    public static final double X_MIN_2000 = -180;
    public static final double Y_MIN_2000 = -90;
    public static final double X_MAX_2000 = 180;
    public static final double Y_MAX_2000 = 90;

    public static final double X_MIN_MERCATOR = -20037508.3427892;
    public static final double Y_MIN_MERCATOR = -20037508.3427892;
    public static final double X_MAX_MERCATOR = 20037508.3427892;
    public static final double Y_MAX_MERCATOR = 20037508.3427892;
    public static final Point ORIGIN_2000 = new Point(-180, 90,SRID_2000);
    public static final Point ORIGIN_MERCATOR = new Point(-20037508.3427892,20037508.3427892,SRID_MERCATOR);
    public static final Envelope ENVELOPE_2000 = new Envelope(X_MIN_2000,Y_MIN_2000,X_MAX_2000,Y_MAX_2000,SRID_2000);
    public static final Envelope ENVELOPE_MERCATOR = new Envelope(X_MIN_MERCATOR,Y_MIN_MERCATOR,X_MAX_MERCATOR,Y_MAX_MERCATOR,SRID_MERCATOR);

    public static final double[] SCALES = {
            2.958293554545656E8,1.479146777272828E8,
            7.39573388636414E7, 3.69786694318207E7,
            1.848933471591035E7, 9244667.357955175,
            4622333.678977588,2311166.839488794,
            1155583.419744397, 577791.7098721985,
            288895.85493609926, 144447.92746804963,
            72223.96373402482,36111.98186701241,
            18055.990933506204, 9027.995466753102,
            4513.997733376551, 2256.998866688275,
            1128.4994333441375
    };
    public static final double[] RESOLUTIONS_MERCATOR = {
            78271.51696402048, 39135.75848201024,
            19567.87924100512, 9783.93962050256,
            4891.96981025128, 2445.98490512564,
            1222.99245256282, 611.49622628141,
            305.748113140705, 152.8740565703525,
            76.43702828517625, 38.21851414258813,
            19.109257071294063, 9.554628535647032,
            4.777314267823516, 2.388657133911758,
            1.194328566955879, 0.5971642834779395,
            0.298582141738970};

    public static final double[] RESOLUTIONS_2000 = {
            0.7031249999891485,0.35156249999999994,
            0.17578124999999997, 0.08789062500000014,
            0.04394531250000007, 0.021972656250000007,
            0.01098632812500002,0.00549316406250001,
            0.0027465820312500017, 0.0013732910156250009,
            0.000686645507812499, 0.0003433227539062495,
            0.00017166137695312503, 0.00008583068847656251,
            0.000042915344238281406, 0.000021457672119140645,
            0.000010728836059570307, 0.000005364418029785169};
}
