package yemu.Common;

public class TokenUtil {
    public static String sign(String id,String name){
        return "token"+id+name;
    }
    public static boolean verify(String token){
        return token.substring(0, 5).equals("token");
    }
}
