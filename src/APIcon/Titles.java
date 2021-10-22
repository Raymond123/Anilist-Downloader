package APIcon;

import java.io.*;

public class Titles implements TitleList{

    private String[] titles;
    private int finalEIndex;
    private int size;

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

    public void addToFile(String title) throws IOException
    {
        // "../Nyaa-Magnet-Links/bin/titles.md" <-- needed fp
        String fp = "../Nyaa-Magnet-Links/bin/titles.md";
        FileWriter fw = new FileWriter(fp, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(title + "\n");
        bw.close();
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
