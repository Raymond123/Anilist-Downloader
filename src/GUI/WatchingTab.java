package GUI;

import APIcon.Post;
import APIcon.Titles;

import javax.annotation.processing.FilerException;
import javax.swing.*;
import javax.xml.crypto.NodeSetData;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class WatchingTab extends JPanel {

    List<List<String>> magnetLinks;
    LinkedList<Node<String>> titles;
    ActionListener actionListener;
    Titles t;

    protected class TNode<E> implements Node<E> {

        E element;
        Node<E> next;
        int size;

        protected TNode(E elem){
            this.size = 1;
            this.element = elem;
            this.next = null;
        }

        protected TNode(E elem, Node<E> next){
            this.size = 1;
            this.element = elem;
            this.next = next;
        }

        @Override
        public E getElement() {
            return this.element;
        }

        @Override
        public Node<E> getNext() {
            return this.next;
        }

        @Override
        public void setElement(E elem) {
            this.element = elem;
        }

        @Override
        public void setNext(Node<E> next) {
            this.next = next;
        }
    }


    public WatchingTab() {}

    public WatchingTab(Titles titles){
        setLayout(new FrameGUI().gridLayout);
        this.t = titles;
        try{
            this.titles = new LinkedList<>();
            magnetLinks = getLinks();
        }catch(IOException io){
            io.printStackTrace();
        }

        for(List<String> show : magnetLinks){
            int epNum=1;
            for(String link : show){
                System.out.println("Epsiode " + epNum++ + " " + link);
            }
            System.out.println();
        }
    }

    private void creatButtons(List<List<String>> shows){
        Node<String> t = this.titles.getFirst();
        for(List<String> links : shows){

            JButton showBut = new JButton(t.getElement());
            t = t.getNext();

            this.actionListener = e -> {

            };
            showBut.addActionListener(this.actionListener);
            this.add(showBut);
        }
    }

    private List<List<String>> getLinks() throws IOException {
        //System.out.println("getting DL links");
        List<List<String>> showObjs = new ArrayList<>();
        List<String> titleList = new ArrayList<>(Arrays.asList(this.t.getList()));
        Stack<String> magLinks = new Stack<>();
        List<String> magLinkArr = new ArrayList<>();

        FileReader fr = new FileReader("../Nyaa-Magnet-Links/bin/d-links.md");
        BufferedReader br = new BufferedReader(fr);

        String line;
        titleList = simplifyList(titleList);
        boolean mag = false;
        while((line=br.readLine()) != null){
            if(titleList.contains(line)){
                //System.out.println("Title: " + line);
                magLinks = new Stack<>();
                magLinkArr = new ArrayList<>();

                Node<String> node = new TNode<>(line);
                titles.getLast().setNext(node);
                titles.add(node);

                showObjs.add(magLinkArr);
                mag = true;
                continue;

            }else if(line.equals("")){

                int s = magLinks.size();
                for(int i=0; i<s; i++){
                    magLinkArr.add(magLinks.pop());
                }
                mag = false;

            }
            if(mag && !(line.equals(": "))){
                //System.out.println("link: " + line);
                magLinks.push(line);
            }
        }

        return showObjs; //placeholder return
    }

    private List<String> simplifyList(List<String> arrayList) {
        ArrayList<String> ret = new ArrayList<>();
        for(String elem : arrayList){
            ret.add(this.t.simplifyTitle(elem));
        }
        return ret;
    }

}
