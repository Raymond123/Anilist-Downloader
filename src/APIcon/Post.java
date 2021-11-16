package APIcon;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.*;

public class Post {

    Titles titleList;

    public Post(){}

    public Post(boolean b)
    {
        if(b){ parseJSON(); }
    }

    public Titles getTitleList()
    {
        return this.titleList;
    }

    public String[] getAdded() throws IOException
    {
        String fp = "bin/titles.md";
        BufferedReader br = new BufferedReader(new FileReader(fp));

        String[] addedTitles = new String[20];
        String title;

        int i=0;
        while((title = br.readLine()) != null)
        {
            addedTitles[i++] = title;
        }

        return addedTitles;
    }

    public void parseJSON() throws NullPointerException{
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("bin/post-result.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject Page = (JSONObject) jsonObject.get("data");
            JSONObject media = (JSONObject) Page.get("Page");

            // A JSON array. JSONObject supports java.util.List interface.
            JSONArray mediaList = (JSONArray) media.get("media");
            JSONObject pageInfo = (JSONObject) media.get("pageInfo");

            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
            Iterator<JSONObject> iterator = mediaList.iterator();
            long perPage = (long) pageInfo.get("total");

            this.titleList = new Titles((int) perPage);

            while (iterator.hasNext()) {
                JSONObject show = iterator.next();
                JSONObject title = (JSONObject) show.get("title");
                String romajiTitle = (String) title.get("romaji");

                titleList.add(romajiTitle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
