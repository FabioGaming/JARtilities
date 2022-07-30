# JARtilities
An unofficial Java Wrapper for the [Artilities REST API](https://artilities.github.io/artilities-api/)

# What can this Wrapper do?
This wrapper currently supports `getting an Idea`, `Getting a challenge Idea`, `Looking up artist slang`, `getting Atilities Banners`. The other API functions like `getting patreons` will hopefully follow soon.

# Where can I get the Wrapper?
You can grab the latest release from this github page!

# How to Install
1. Navigate to Releases and download the latest version of JARtilities
2. Open Your Project in any Java IDE (my example: IntelliJ)
3. Head to File > Project Structure
![image](https://user-images.githubusercontent.com/61352968/181909743-638fee75-74e0-40e7-ace4-145a59282080.png)
4. Go to Library and click the + sign, then proceed to click on Java
![image](https://user-images.githubusercontent.com/61352968/181909783-42cf0ff8-9f2b-4950-b6f6-524ba6ad8a56.png)
5. Select JARtilities.jar and click OK
6. After it's added to the list you can click Apply
7. That's it.

# DOCUMENTATION
### Getting an Idea
You can get a random Idea from the Artilities Database using the `getIdea()` function, this function will return a HashMap with the following keys: `english`, `executionTime`, `statusCode`, `russian`, `raw`, `WebResponseCode`
- `english` - Will return a random Idea in english
- `russian` - Will return a random Idea in russian
- `executionTime` - Will return the amount of time it took the server to respond (in ms).
- `statusCode` - Will return the Servers response code (in best case `200`).
- `WebResponseCode` - Will return the response code of the request itself (in best case it matches `statusCode`)
- `raw` - Will return the raw JSON output of the server
#### Note
- If there was an error during the request, the HashMap will return `null`
- You might need to change your encoding to display `russian`
#### Example Usage:
```Java
        HashMap<String,String> testMap = Artilities.getIdea();
        if(testMap != null) {

            System.out.println("Your Idea: " + testMap.get("english"));
            System.out.println("Russian: " + testMap.get("russian"));
            System.out.println("Server Response " + testMap.get("statusCode"));
            System.out.println("Server Response Time: " + testMap.get("executionTime") + "ms");
            System.out.println("Raw JSON: " + testMap.get("raw"));
        } else {
            System.out.println("There was an error during the request.");
        }   
```
#### Output:
```
Your Idea: God of gambling
Russian: Бог азартных игр
Server Response 200
Server Response Time: 136ms
Raw JSON: {"status_code":200,"generated_idea":{"ru":"Бог азартных игр","eng":"God of gambling"},"execution_time":136}
```
