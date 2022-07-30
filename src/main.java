import API.Artilities;

import java.util.HashMap;

public class main {

    public static void main(String[] args) {
        HashMap<String,String> testMap = Artilities.getChallenge();
        System.out.println(testMap.get("english"));
    }

}
