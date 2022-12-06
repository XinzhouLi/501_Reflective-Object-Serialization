import org.json.JSONObject;
//import com.alibaba.fastjson.JSONObject ;

import java.lang.reflect.Field;
import java.util.Map;

public class Serializer {

    private Object incomeO;
    public Serializer(Object O){
        incomeO = O;
    }
    public JSONObject serialize() throws IllegalAccessException {

        JSONObject j = new JSONObject();
        for (Field fd : incomeO.getClass().getDeclaredFields()){
            j.put(fd.getName(), getValue(fd, incomeO));
        }
        System.out.println(j);
        return j;
    }
    public static void main(String[] args) throws IllegalAccessException {
        Only_primitives test = new Only_primitives(1,'c',true,0.11);
        Serializer ser = new Serializer(test);
        JSONObject x = ser.serialize();
        System.out.println(x);


    }


    public Object getValue(Field fd, Object obj) throws IllegalAccessException {
        if (fd.getType().toString().equals("short")) {
            return fd.getShort(obj);
        } else if (fd.getType().toString().equals("int")) {
            return fd.getInt(obj);
        } else if (fd.getType().toString().equals("long")) {
            return fd.getLong(obj);
        } else if (fd.getType().toString().equals("float")) {
            return fd.getFloat(obj);
        } else if (fd.getType().toString().equals("double")) {
            return fd.getDouble(obj);
        } else if (fd.getType().toString().equals("byte")) {
            return fd.getByte(obj);
        } else if (fd.getType().toString().equals("boolean")) {
            return fd.getBoolean(obj);
        } else if (fd.getType().toString().equals("char")) {
            return fd.getChar(obj);
        }
        return "not pri";
    }
}
