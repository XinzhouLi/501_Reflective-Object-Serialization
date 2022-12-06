public class Only_primitives {

    public int id;
    public char ch;
    public boolean bool;
    public double db;



    public Only_primitives(int id, char ch, boolean bool, double db) {
        this.id = id;
        this.ch = ch;
        this.bool = bool;
        this.db = db;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
    }

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public double getDb() {
        return db;
    }

    public void setDb(double db) {
        this.db = db;
    }
}
