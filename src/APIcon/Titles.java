package APIcon;

import java.io.*;
import java.util.Locale;

public class Titles implements TitleList{

    private String[] titles;
    private int finalEIndex;
    private final int size;
    public boolean partAlert;

    public Titles()
    {
        this.titles = new String[50];
        this.size = 50;
        this.finalEIndex = 0;
    }

    public Titles(int size)
    {
        this.titles = new String[size];
        this.size = size;
        this.finalEIndex = 0;
    }

    public Titles(int size, String[] titles)
    {
        this.titles = titles;
        this.size = size;
        this.finalEIndex = 0;
    }

    public boolean getPartAlert()
    {
        return partAlert;
    }

    protected final String fp = "../Nyaa-Magnet-Links/bin/titles.md";

    public void addToFile(String title) throws IOException
    {
        FileWriter fw = new FileWriter(fp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(simplifyTitle(title) + "\n");
        bw.close();
    }

    public void rmFile(String title) throws IOException
    {
        FileWriter fw = new FileWriter(fp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        //remove title from file
        bw.close();

    }

    public String simplifyTitle(String title)
    {
        String[] retA = new String[2];
        String ret = title.toLowerCase(Locale.ROOT);
        this.partAlert = false;

        if(ret.contains(":"))
        {
            retA = ret.split(":");
            ret = ret.split(":")[0];
        }

        ret = ret.replaceAll("[0-9]", "");
        ret = ret.replace(".", " ");

        if(ret.equals(""))
        {
            ret = simplifyTitle(retA[1]);
        }

        //remove part if user confirmed it's not necessary

        if(ret.contains("part")) partAlert = true;

        return ret.trim();
    }

    @Override
    public boolean isEmpty() {
        return (this.titles.length == 0);
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(String title) {
        this.titles[finalEIndex++] = title;
        //System.out.println("added " + title);
    }

    @Override
    public int totalElements() {
        return this.titles.length;
    }

    @Override
    public String[] getList() {
        return this.titles;
    }
}
