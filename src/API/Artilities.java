package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.*;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Dictionary;
import java.util.HashMap;

@SuppressWarnings("DuplicatedCode")
public class Artilities {

    private static String APILink = "https://artilities.herokuapp.com/api/";

    public static HashMap<String,String> getidea() {
        HashMap<String,String> returnMap = new HashMap<String,String>();
        try {
            URL url = new URL(APILink + "ideas");
            HttpURLConnection response = (HttpURLConnection)url.openConnection();
            response.setRequestProperty("Accept", "application/json");
            response.disconnect();
            Object obj = JSONValue.parse(response.getResponseMessage());
            JSONObject responseJson = (JSONObject) obj;
            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("english", String.valueOf(responseJson.get("generated_idea.eng")));
            returnMap.put("russian", String.valueOf(responseJson.get("generated_idea.ru")));
            returnMap.put("raw", response.getResponseMessage());
        }catch(Exception e) {
            returnMap.put("raw", String.valueOf(e));
        }
        return returnMap;
    }
    public static HashMap<String,String> getChallenge() {
        HashMap<String,String> returnMap = new HashMap<String,String>();
        try {
            URL url = new URL(APILink + "challenges");
            HttpURLConnection response = (HttpURLConnection)url.openConnection();
            response.setRequestProperty("Accept", "application/json");
            response.disconnect();
            Object obj = JSONValue.parse(response.getResponseMessage());
            JSONObject responseJson = (JSONObject) obj;
            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("russian", String.valueOf(responseJson.get("generated_challenge.ru")));
            returnMap.put("english", String.valueOf(responseJson.get("generated_challenge.eng")));
            returnMap.put("raw", String.valueOf(response.getResponseMessage()));
        }catch(Exception e) {
            returnMap.put("raw", String.valueOf(e));
        }
        return returnMap;
    }
    public static HashMap<String,String> getDictionaryEntry(String query) {
        HashMap<String,String> returnMap = new HashMap<String,String>();
        String searchQuery = query.replace(" ", "%20");
        try {
            URL url = new URL(APILink + "dict" + "&query=" + searchQuery);
            HttpURLConnection response = (HttpURLConnection)url.openConnection();
            response.setRequestProperty("Accept", "application/json");
            response.disconnect();
            Object obj = JSONValue.parse(response.getResponseMessage());
            JSONObject responseJson = (JSONObject) obj;
            JSONArray dictEntry = (JSONArray) responseJson.get("query_results");
            JSONObject dictObj = (JSONObject) dictEntry.get(0);


            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("raw", response.getResponseMessage());

        } catch(Exception e) {
            returnMap.put("raw", String.valueOf(e));
        }

        return returnMap;
    }
}
