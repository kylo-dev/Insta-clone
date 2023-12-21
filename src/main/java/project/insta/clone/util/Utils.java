package project.insta.clone.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 태그 파싱하는 데 사용
public class Utils {

    // Tag 파싱
    public static List<String> tagParser(String tags){
        String[] temp = tags.split("#");
        // temp = [, hello, 안녕, 바이]

        int len = temp.length;

        return new ArrayList<>(Arrays.asList(temp).subList(1, len));
    }
}
