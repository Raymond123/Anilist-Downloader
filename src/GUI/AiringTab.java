package GUI;

import APIcon.Post;
import APIcon.Titles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class AiringTab extends JPanel{

    ActionListener actionListener;
    boolean cont;

    public AiringTab(){}

    public AiringTab(Titles titleList)
    {
        FrameGUI var = new FrameGUI();
        setLayout(var.gridLayout);

        //this.titleList.getSize()
        for(int i=0; i<titleList.getSize(); i++)
        {
            JButton addButton = new JButton(titleList.getList()[i]);
            this.actionListener = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton but = (JButton) e.getSource();
                    but.setBackground(Color.blue);//but.setEnabled(false);

                    try {
                        titleList.addToFile(addButton.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    if(but.isOpaque())
                    {
                        but.setOpaque(false);
                    }
                    else
                    {
                        but.setOpaque(true);
                    }
                }
            };

            try {
                String[] added = getAdd();
                this.cont = Arrays.asList(added).contains(addButton.getText());
                System.out.println(cont);

                if (cont) {
                    addButton.setBackground(Color.blue);
                    addButton.setOpaque(true);
                }
            }
            catch(IOException io)
            {
            }

            addButton.addActionListener(this.actionListener);

            //System.out.println(titleList.getList()[i]);
            this.add(addButton);
            //buttons should become unclickable after being clicked
        }
    }

    private String[] getAdd() throws IOException
    {
        return new Post().getAdded();
    }
}
