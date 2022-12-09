import java.util.ArrayList;

public class Collection_object {

	public ArrayList<String> arlist = new ArrayList<>();
	public ArrayList<String>  arlist2 = new ArrayList<>();

	public Collection_object(ArrayList<String> arlist, ArrayList<String> arlist2) {
		this.arlist = arlist;
		this.arlist2 = arlist2;
	}

	public Collection_object() {
		arlist.add("a1");
		arlist.add("a2");
		arlist2.add("a2");
		arlist2.add("a3");
	}
}
