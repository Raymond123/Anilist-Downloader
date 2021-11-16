package GUI;

import APIcon.Post;
import APIcon.Titles;
import org.junit.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static org.junit.Assert.*;

public class FrameGUI extends JFrame {
    protected Post postReq;
    protected Titles titleList;

    private JPanel mainPanel;

    GridLayout gridLayout = new GridLayout(50, 1, 20, 1);

    public FrameGUI() {
    }

    public FrameGUI(boolean b) {
        createUIComponents();

        setContentPane(this.mainPanel);
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(b);
    }

    private void createUIComponents() {
        // TODO: need to check watching.md file for titles and grew out buttons with matching titles
        this.postReq = new Post(true);
        this.titleList = this.postReq.getTitleList();
        this.gridLayout.setRows(this.titleList.getSize());
        JScrollPane airingScrollPane = new JScrollPane(
                new AiringTab(this.titleList),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane watchingScrollPane = new JScrollPane(
                new WatchingTab(this.titleList),
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(airingScrollPane, "Airing");
        tabbedPane.add(watchingScrollPane, "Watching");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem linkMenu = new JMenuItem("Get Links");
        linkMenu.addActionListener(e->{
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/main.py");
            processBuilder.redirectErrorStream(true);
            int exit;
            try {
                Process p = processBuilder.start();
                if((exit = p.waitFor()) != 0) JOptionPane.showMessageDialog(this, "Exit code: " + exit);
                else{
                    SwingUtilities.updateComponentTreeUI(this);
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        JMenuItem reloadMenu = new JMenuItem("Refresh");
        reloadMenu.addActionListener(e-> {
            ProcessBuilder processBuilder = new ProcessBuilder("python3", "src/API/ApiCon.py");
            processBuilder.redirectErrorStream(true);
            int exit;
            try {
                Process p = processBuilder.start();
                if((exit = p.waitFor()) != 0) JOptionPane.showMessageDialog(this, "Exit code: " + exit);
                else{
                    SwingUtilities.updateComponentTreeUI(this);
                }
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
        menu.add(linkMenu);
        menu.add(reloadMenu);
        menuBar.add(menu);

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new CardLayout());
        this.mainPanel.add(tabbedPane);
        this.setJMenuBar(menuBar);

    }

}
