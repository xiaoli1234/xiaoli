package login.example.com.myapplication6;

public class DemoBean {
//    private int id;
    private String name;
	private String pic;
//	private int id1;
	private boolean canRemove = true;

//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getId1() {
//		return id1;
//	}
//	public void setId1(int id1) {
//		this.id1 = id1;
//	}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public boolean isCanRemove() {
		return canRemove;
	}

	public void setCanRemove(boolean canRemove) {
		this.canRemove = canRemove;
	}

	public DemoBean(String name,String pic,boolean canRemove) {
//        this.id = id;
		this.name = name;
		this.pic = pic;
//		this.id1 = id1;
		this.canRemove = canRemove;
	}

}
