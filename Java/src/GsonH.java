import com.google.gson.Gson;

public class GsonH {
    public static String toJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    public static <T> T toObject(String str ,Class<T> tClass){
        Gson gson = new Gson();
        return gson.fromJson(str,tClass);
    }
}
