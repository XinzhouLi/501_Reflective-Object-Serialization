import javax.swing.plaf.basic.BasicComboBoxUI;

public class Only_object {

	public Only_primitives p1;
	public Only_primitives p2;
	public Only_primitives p3;

	public Only_object() {
	}
	public Only_object(boolean i){
		this.p1 = new Only_primitives(474,'a',true,0.22, (float) 0.22, (byte) 324, 496516);
		this.p2 = new Only_primitives(75,'c',true,0.55, (float) 0.27, (byte) 42, 453);
		this.p3 = new Only_primitives(7787,'d',true,0.72, (float) 0.89, (byte) 13, 25);

	}
}
