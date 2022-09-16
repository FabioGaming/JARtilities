package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

//Prevents from showing useless warnings
@SuppressWarnings("DuplicatedCode")
public class Artilities {
    //Sets the API link so it only needs to be written once
    private static String APILink = "https://artilities-web-api.vercel.app/api/";

    //Handles the GetIdea Logic of JARtilities
    //Will return a HashMap with 2 strings which can hold the keys: "WebResponseCode", "executionTime", "statusCode", "english", "russian", "raw"
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

            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("english", String.valueOf(ideaOutput.get("eng")));
            returnMap.put("russian", String.valueOf(ideaOutput.get("ru")));
            returnMap.put("raw", responseOutput);
        }catch(Exception e) {
            returnMap = null;
        }
        return returnMap;
    }

    //Handles the getChallenge Logic for JARtilities
    //Will return a HashMap that has 2 Strings which contain the keys: "WebResponseCode", "executionTime", "statusCode", "english", "russian", "raw"
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
            returnMap = null;
        }
        return returnMap;
    }
    //Handles the GetDictionaryEntry Logic for JARtilities
    //Requires a String as search query
    //Will return a HashMap with 2 Strings which can hold the keys: "WebResponseCode", "executionTime", "statusCode", "term", "description", "raw"
    //The keys "term" and "description" will return null when there was no result
    public static HashMap<String,String> getDictionaryEntry(String query) {
        HashMap<String,String> returnMap = new HashMap<String,String>();
        String searchQuery = query.replace(" ", "%20").toLowerCase();
        try {
            URL url = new URL(APILink + "dict" + "?query=" + searchQuery);
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
            JSONArray queryArray = (JSONArray) responseJson.get("query_results");
            String word;
            String Description;
            if(queryArray.isEmpty()) {
                word = null;
                Description = null;
            } else {
                JSONArray queryResult = (JSONArray) queryArray.get(0);
                word = String.valueOf(queryResult.get(0));
                Description = String.valueOf(queryResult.get(1));
            }
            returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
            returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
            returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
            returnMap.put("term", word);
            returnMap.put("description", Description);
            returnMap.put("raw", responseOutput);

        } catch(Exception e) {
            returnMap = null;
        }

        return returnMap;
    }
    //other contains the API features that are marked as "other" on the API target URL
    public static class other {
        //Handles Logic for GetBanners in JARtilities
        //Will return a HashMap that contains 2 Strings which contain the keys: "WebResonseCode", "executionTime", "statusCode", "raw", "bannerLink", "bannerImage", "language"
        public static HashMap<String, String> getBanners() {
            HashMap<String,String> returnMap = new HashMap<String,String>();
            try {
                URL url = new URL(APILink + "other/banners");
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
                JSONObject JsonContent = (JSONObject) responseJson.get("details");

                returnMap.put("WebResponseCode", String.valueOf(response.getResponseCode()));
                returnMap.put("executionTime", String.valueOf(responseJson.get("execution_time")));
                returnMap.put("statusCode", String.valueOf(responseJson.get("status_code")));
                returnMap.put("raw", responseOutput);
                returnMap.put("bannerLink", String.valueOf(JsonContent.get("banner_url")));
                returnMap.put("bannerImage", String.valueOf(JsonContent.get("banner_image")));
                returnMap.put("language", String.valueOf(JsonContent.get("language")));
            }catch(Exception e) {
                returnMap = null;
            }
            return returnMap;
        }
    }
}
