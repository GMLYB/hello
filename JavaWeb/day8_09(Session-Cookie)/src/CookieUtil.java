import javax.servlet.http.Cookie;

public class CookieUtil {

    public static Cookie getcookie(String str,Cookie cookies[]){
        if (str == null || cookies.length ==0 || cookies == null){
            return null;
        }
        for (Cookie c : cookies){
            if (str.equals(c.getName())){
                return c;
            }
        }
        return null;
    }
}
