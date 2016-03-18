package API_Base64_4_5;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 4.5 Base64
 * 对Base64编码的支持已经被加入到Java 8官方库中，这样不需要使用第三方库就可以进行Base64编码
 * <p>
 * 新的Base64API也支持URL和MINE的编码解码。
 * (Base64.getUrlEncoder() / Base64.getUrlDecoder(),
 * Base64.getMimeEncoder() / Base64.getMimeDecoder())。
 */
public class Main {

    public static void main(String[] args) {
        final String text = "Base64 finally in Java 8!";
        final String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        final String decoded = new String(
                Base64.getDecoder().decode(encoded),
                StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
