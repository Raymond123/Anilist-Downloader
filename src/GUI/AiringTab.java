package GUI;

import APIcon.Post;
import APIcon.Titles;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;

public class AiringTab extends JPanel{

    ActionListener actionListener;
    boolean cont;

    public AiringTab(){}

    public AiringTab(Titles titleList)
    {
        setLayout(new FrameGUI().gridLayout);

        //this.titleList.getSize()
        for(int i=0; i<titleList.getSize(); i++)
        {
            JButton addButton = new JButton(titleList.getList()[i]);
            this.actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton but = (JButton) e.getSource();
                    but.setBackground(Color.blue);//but.setEnabled(false);

                    if(but.isOpaque())
                    {
                        but.setOpaque(false);
                        try {
                            titleList.rmFile(titleList.simplifyTitle(addButton.getText()));
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            titleList.addToFile(addButton.getText());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        but.setOpaque(true);
                        System.out.println(titleList.getPartAlert());
                        if(titleList.getPartAlert())
                        {
                            //make alert telling user about whether the word is needed or not.
                            Object[] options = {"remove \"part\"", "keep"};
                            int n = JOptionPane.showOptionDialog(((JButton) e.getSource()).getParent(),
                                    but.getText() + " \nThis title  includes \"part\". Is this part of the actual title? \nNOTE: If this is in reference to a part 2 of a show then search results may not show up correctly. ",
                                    "Warning, \"part\" in title.",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]
                                    );
                            if(n==JOptionPane.YES_OPTION)
                            {
                                //remove part from title list && REMEMBER that part shouldnt be in that title
                                try {
                                    String ttl = titleList.simplifyTitle(but.getText());
                                    titleList.rmFile(ttl);
                                    titleList.addToFile(ttl.replaceAll("part", ""));

                                    JSONObject partTrue = new JSONObject();
                                    partTrue.put(true, ttl.replaceAll("part", ""));

                                    FileWriter fw = new FileWriter("lib/partList.json", true);
                                    BufferedWriter bfw = new BufferedWriter(fw);
                                    bfw.write(partTrue.toJSONString());
                                    bfw.close();
                                    System.out.println(partTrue.toJSONString());
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }*/
                    }
                }
            };

            try {
                String[] added = getAdd();
                this.cont = Arrays.asList(added).contains(titleList.simplifyTitle(addButton.getText()));
                System.out.println(cont + ": " + titleList.simplifyTitle(addButton.getText()));

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
        }
    }

    private String[] getAdd() throws IOException
    {
        return new Post().getAdded();
    }
}
