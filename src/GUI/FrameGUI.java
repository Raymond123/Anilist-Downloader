package GUI;

import APIcon.Post;
import APIcon.Titles;

import javax.swing.*;
import java.awt.*;

public class FrameGUI extends JFrame
{
    protected Post postReq;
    protected Titles titleList;

    private JPanel mainPanel;

    GridLayout gridLayout = new GridLayout(50, 1, 20, 1);

    public FrameGUI(){}

    public FrameGUI(boolean b){
        createUIComponents();

        setContentPane(this.mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(b);
    }

    private void createUIComponents()
    {
        // TODO: need to check watching.md file for titles and grew out buttons with matching titles
        this.postReq = new Post(true);
        this.titleList = this.postReq.getTitleList();
        this.gridLayout.setRows(this.titleList.getSize());

        JScrollPane airingScrollPane = new JScrollPane(new AiringTab(this.titleList), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane watchingScrollPane = new JScrollPane(new WatchingTab(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(airingScrollPane, "Airing");
        tabbedPane.add(watchingScrollPane, "Watching");

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new CardLayout());
        this.mainPanel.add(tabbedPane);

    }

}
