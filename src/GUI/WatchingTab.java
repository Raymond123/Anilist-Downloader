package GUI;

import APIcon.Post;
import APIcon.Titles;

import javax.annotation.processing.FilerException;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class WatchingTab extends JPanel {

    public WatchingTab() {}

    public WatchingTab(Titles titles){
        setLayout(new FrameGUI().gridLayout);
        try{
            getLinks(titles);
        }catch(IOException io){
            io.printStackTrace();
        }
    }

    private List<List<String>> getLinks(Titles t) throws IOException {
        System.out.println("getting DL links");
        List<List<String>> showObjs = new ArrayList<>();
        List<String> titleList = new ArrayList<>(Arrays.asList(t.getList()));
        Stack<String> magLinks = new Stack<>();
        List<String> magLinkArr = new ArrayList<>();

        FileReader fr = new FileReader("../Nyaa-Magnet-Links/bin/d-links.md");
        BufferedReader br = new BufferedReader(fr);

        String line;
        titleList = simplifyList(titleList, t);
        boolean mag = false;
        while((line=br.readLine()) != null){
            if(titleList.contains(line)){
                System.out.println("Title: " + line);
                magLinks = new Stack<>();
                magLinkArr = new ArrayList<>();
                showObjs.add(magLinkArr);
                mag = true;
                continue;
            }else if(line.equals("")){
                System.out.println("Transfering from stack to arrayList\n");
                for(int i=0; i<magLinks.size(); i++){
                    magLinkArr.add(magLinks.pop());
                }
                mag = false;
            }
            if(mag && !(line.equals(": "))){
                System.out.println("link: " + line);
                magLinks.push(line);
            }

        }

        return showObjs; //placeholder return
    }

    private List<String> simplifyList(List<String> arrayList, Titles t) {
        ArrayList<String> ret = new ArrayList<>();
        for(String elem : arrayList){
            ret.add(t.simplifyTitle(elem));
        }
        return ret;
    }

}
