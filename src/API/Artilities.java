package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Dictionary;
import java.util.HashMap;

@SuppressWarnings("DuplicatedCode")
public class Artilities {

    private static String APILink = "https://artilities.herokuapp.com/api/";

    public static HashMap<String,String> getIdea() {
        HashMap<String,String> returnMap = new HashMap<String,String>();
        try {
            URL url = new URL(APILink + "ideas");
            HttpURLConnection response = (HttpURLConnection)url.openConnection();
            response.setRequestProperty("Accept", "application/json");
            response.setRequestMethod("GET");
            InputStream inputStream = response.getInputStream();
            StringBuilder textBuilder = new StringBuilder();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            response.disconnect();
            String responseOutput = textBuilder.toString();


            JSONParser parser = new JSONParser();
            JSONObject responseJson = (JSONObject) parser.parse(responseOutput);
            JSONObject ideaOutput = (JSONObject) responseJson.get("generated_idea");

            //JSONArray JArray = (JSONArray) responseJson.get("generated_idea");

            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("english", String.valueOf(ideaOutput.get("eng")));
            returnMap.put("russian", String.valueOf(ideaOutput.get("ru")));
            returnMap.put("raw", responseOutput);
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
            response.setRequestMethod("GET");
            InputStream inputStream = response.getInputStream();
            StringBuilder textBuilder = new StringBuilder();
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
            response.disconnect();
            String responseOutput = textBuilder.toString();


            JSONParser parser = new JSONParser();
            JSONObject responseJson = (JSONObject) parser.parse(responseOutput);
            JSONObject challengeOutput = (JSONObject) responseJson.get("generated_challenge");

            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("russian", String.valueOf(challengeOutput.get("ru")));
            returnMap.put("english", String.valueOf(challengeOutput.get("eng")));
            returnMap.put("raw", String.valueOf(responseOutput));
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
            String resultString =  String.valueOf(dictObj);
            String[] results = resultString.split(",");


            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("term", results[0]);
            returnMap.put("description", results[1]);
            returnMap.put("raw", response.getResponseMessage());

        } catch(Exception e) {
            returnMap.put("raw", String.valueOf(e));
        }

        return returnMap;
    }
}
