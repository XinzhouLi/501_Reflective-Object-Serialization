public class Only_object {

	public Just_object p1;
	public Just_object p2;


	public Only_object() {
	}
	public Only_object(boolean i){
		p1 = new Just_object(1, true);
		p2 = new Just_object(2,false);
	}
	public void init(){
		p1.init();
		p2.init();
	}
}
