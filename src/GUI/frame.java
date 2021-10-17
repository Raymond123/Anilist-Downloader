package GUI;

import javax.swing.*;

public class frame extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPanel;
    private JPanel airingTab;
    private JPanel watchingTab;
    private JButton button1;
    private JLabel titleLabel;

    public frame(){
        setContentPane(mainPanel);
        createUIComponents();
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        for(int i=0; i<5; i++)
        {
            titleLabel = new JLabel("testTitle");
        }

    }
}
