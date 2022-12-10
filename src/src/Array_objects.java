import java.util.ArrayList;
import java.util.Optional;

public class Array_objects {

	public Only_primitives[] OPs;

	public Array_objects() {

	}
	public Array_objects(boolean t){
		OPs = new Only_primitives[]{
				new Only_primitives(1, 'c', true, 0.11, (float) 0.22, (byte) 123, 1516),
				new Only_primitives(1, 'c', true, 0.11, (float) 0.22, (byte) 123, 1516),
				new Only_primitives(1, 'c', true, 0.11, (float) 0.22, (byte) 123, 1516)};
	}
}
