package APIcon;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class Post {

    public Post(){}

    public Post(boolean b)
    {
        if(b){ parseJSON(); }
    }

    public void parseJSON() throws NullPointerException{
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("../Nyaa-Magnet-Links/bin/post-result.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject Page = (JSONObject) jsonObject.get("data");
            JSONObject media = (JSONObject) Page.get("Page");

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray mediaList = (JSONArray) media.get("media");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = mediaList.iterator();
            while (iterator.hasNext()) {
                JSONObject show = iterator.next();
                System.out.println(show);
                JSONObject title = (JSONObject) show.get("title");
                String romajiTitle = (String) title.get("romaji");
                System.out.println(romajiTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
