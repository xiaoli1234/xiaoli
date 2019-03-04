package login.example.com.myapplication6;

public class Demo1Bean {

//  private String id;

    private String name;

    private String title;

    private String imgname;
    /**
     * 表示是否可以删除
     */
    private boolean canRemove = true;


    //    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getImgname() {
        return imgname;
    }

    public void setImgname(String imgname) {
        this.imgname = imgname;
    }

    public boolean isCanRemove() {
        return canRemove;
    }

    public void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    public Demo1Bean(String name, String title, String imgname, boolean canRemove) {
        this.name = name;
        this.title = title;
        this.imgname = imgname;
        this.canRemove = canRemove;
    }

    public Demo1Bean() {
    }

}
