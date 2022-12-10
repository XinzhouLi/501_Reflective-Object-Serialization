import java.util.Scanner;

public class Only_primitives {

    public int id;
    public char ch;
    public boolean bool;
    public double db;

    public float fl;

    public byte bt;

    public long ln;

    public Only_primitives(int id, char ch, boolean bool, double db, float fl, byte bt, long ln) {
        this.id = id;
        this.ch = ch;
        this.bool = bool;
        this.db = db;
        this.fl = fl;
        this.bt = bt;
        this.ln = ln;
    }

    public Only_primitives(boolean i){
        id = 1;
        ch = 'a';
        bool = true;
        db = 0.111;
        fl = (float) 0.23;
        bt = (byte) 0x123;
        ln = 123123;
    }

    public Only_primitives(){

    }
    public void init(){
        System.out.println("Set value for int id");
        this.id = Integer.parseInt(inputData());
        System.out.println("Set value for char ch");
        ch = inputData().charAt(0);
        System.out.println("Set value for boolean bool");
        bool = Boolean.parseBoolean(inputData());
        System.out.println("Set value for double db");
        db = Double.parseDouble(inputData());
        System.out.println("Set value for float fl");
        fl = Float.parseFloat(inputData());
        System.out.println("Set value for byte bt");
        bt = Byte.parseByte(inputData());
        System.out.println("Set value for long ln");
        ln = Long.parseLong(inputData());
    }
    public String inputData(){
        Scanner scan = new Scanner(System.in);
        String str = new String();
        if(scan.hasNext()){
            str = scan.next();
        }
        scan.close();
        return str;
    }
}
