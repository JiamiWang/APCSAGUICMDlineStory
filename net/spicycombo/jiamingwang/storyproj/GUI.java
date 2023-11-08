package net.spicycombo.jiamingwang.storyproj;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.File;
import java.net.*;
import java.util.function.BiConsumer;

public class GUI extends JFrame implements ActionListener {
    // The comments are just the yapping of the developer
    // Note: extend JFrame so we can use this keyword mostly
    // Note: inhereits ActionListener so we can call component actions! 
    private boolean enabled = true;

    // Components of GUI
    // -Main Components

    // These instance variables are not designed to be usable by public.
    // Why would they anyways?!?

    // Icons
    private static final String musicOffIconPath = Misc.imagesPath + "musicOff.jpg";
    private static final String musicOnIconPath  = Misc.imagesPath + "musicOn.jpg";
    private ImageIcon musicOnIcon; private ImageIcon musicOffIcon;

    // -Menu
    private JMenu menu; private JMenuBar mb;
    private JMenuItem itemAbout;

    private JButton musicButton;
    // private JButton button1, button2;
    private JComboBox optionList;
    private JButton confirmButton;
    private JTextArea content;

    private JLabel sceneNum;

    // But, the important fields here
    
    public void changeJTextAreaContent(String text) {
        content.setText(text);
    }
    
    public static final String sceneTextTemplate = "Scene ";
    
    public boolean DisplayScene(Scene scene) {
        if (!enabled) return false;
        
        fixSceneId();

        optionList.removeAllItems();

        if (Main.story.isEndScene(scene)) {
            optionList.addItem("THANK YOU FOR PLAYING! :D");
            confirmButton.setEnabled(false);
            // i didn't test if this actually disables it lol
        }
        else {
            BiConsumer consumer = new BiConsumer<String, String>() {
                @Override
                public void accept(String thisSceneId, String thisSceneContext) {
                    optionList.addItem(thisSceneId + ": " + thisSceneContext);
                }
            };
            
            scene.getOptions().forEach(consumer);
        }
        
        content.setText(scene.getScene());
        
        return true;
    }
    
    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStat) {
        enabled = newStat;
    }

    // See: https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    protected ImageIcon createImageIcon(String path,
                                        String description) {
        URL imgURL; File file;
        try {
            ImageIcon ico;
            if (Misc.runAsJarFile()) {
                imgURL = getClass().getResource(path);
                ico = new ImageIcon(imgURL, description);
            }
            else {
                file = new File(path);
                ico = new ImageIcon(file.getPath(), description);
            }

            // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
            Image image = ico.getImage(); // transform it
            Image newImg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
            // scale it the smooth way
            ico = new ImageIcon(newImg);  // transform it back

            return ico;
        } catch (Exception e) {
            System.err.println("Nawwww, error occurred while creating ImageIcon: " + e.getMessage());
            return null;
        }

        // old me: lazy to throw and then having to catch da exception
        // new me: nvm
    }

    public void fixMusicButton() {
        if (Main.bMusic.isPlaying()) musicButton.setIcon(musicOnIcon);
        else musicButton.setIcon(musicOffIcon);
    }

    public void fixSceneId() {
        sceneNum.setText(sceneTextTemplate + Main.story.curScene.getSceneId());
        // wow, so long... normal tho, ig
    }

    public GUI() {
        musicOnIcon = createImageIcon(musicOnIconPath, "Music is On");
        musicOffIcon = createImageIcon(musicOffIconPath, "Music is Off");

        constructGUI();
    }
    
    private void setJLablelImage(JLabel label, String url) {
        try {
            URL PicURL = new URL(url);
            ImageIcon img = new ImageIcon(PicURL);
            label.setIcon(img);
        } catch (Exception e) {
            System.out.println("!! Cannot fetch image.");
            System.out.println("!! " + e.getMessage());
        }
    }
    
    private void constructGUI() {
        // Initialize Frame
        this.setTitle("Story Project");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Window Location

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        int posX = (dim.width / 2) - (this.getWidth() / 2);
        int posY = (dim.height / 2) - (this.getHeight() / 2);


        this.setLocation(posX, posY);


        // INITIALIZE DA PANEL

        JPanel mainPanel = new JPanel();
        this.add(mainPanel);

        JLabel title = new JLabel(Misc.softwareName);

        mainPanel.add(title);

        // Musix!!
        musicButton = new JButton("Toggle Music");
        musicButton.setIcon(musicOffIcon);
        musicButton.addActionListener(this);

        mainPanel.add(musicButton);

        // Label for scene ID

        sceneNum = new JLabel("Loading...");
        mainPanel.add(sceneNum);

        // Create content

        JPanel contentPanel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Current Scene");
        contentPanel.setBorder(border);

        JLabel thumb = new JLabel();
        
        content = new JTextArea(15, 25);
        content.setEditable(false);
        content.setText("Scene text will appear here!");
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        int numOfLines = content.getLineCount();
        content.append(" - currently there is " + numOfLines + " line in this JTextArea");

        JScrollPane scrollPane = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanel.add(scrollPane);
        mainPanel.add(contentPanel);

        // Option selection
        optionList = new JComboBox<String>();
        
        optionList.addItem("Select an option here");
        
        confirmButton = new JButton("Confirm Choice");

        confirmButton.addActionListener(this);

        mainPanel.add(optionList);
        mainPanel.add(confirmButton);

        // create top bar menu
        mb = new JMenuBar();
        menu = new JMenu("About");
        
        itemAbout = new JMenuItem("About this program");  
        itemAbout.addActionListener(this);
        
        menu.add(itemAbout);
        mb.add(menu);
        this.setJMenuBar(mb);

        // set visible
        this.setVisible(true);

        // focus with mouse
        content.requestFocus();
    }

    public void actionPerformed (ActionEvent e) {    
        if (e.getSource() == itemAbout) {
            StringBuilder creditsText = new StringBuilder(new String());
            for (String line : Misc.creditLines) {
                creditsText.append(line).append('\n');
            }
            // In C#, StringBuilder is usually necessary
            // Seems like the most formal in this case here

            JOptionPane.showMessageDialog(null,
                    "This is a GUI window for " + Misc.softwareName + "." +
                            "\nProgram " + Misc.softwareName + " by " + Misc.creator +
                            "\nCredits:" +
                            "\n"+creditsText + // new line because of StringBuilder
                            "This project is licensed under MIT!");
        } else if (e.getSource() == confirmButton) {
            int index = optionList.getSelectedIndex();
            int curIndex = 0; String sceneId = "";
            for ( String key : Main.story.curScene.getOptions().keySet() ) {
                if (curIndex == index) { sceneId = key; break; } 
                curIndex++;
            }
            
            // It seems like there are several issues with this code.
            // 1. This is too long
            // 2. Set can change the indicies after getting as set
            // 3. Java is dumb for not having KeyValuePair<K, V> like C#
            // 4. I blame Java for everything wrong in my life, my family is haunted, my dog peed
            // See https://stackoverflow.com/questions/10462819/get-keys-from-hashmap-in-java
            // and the below one was somehow more work for me?
            // https://stackoverflow.com/questions/3973512/java-hashmap-how-to-get-a-key-and-value-by-index

            Main.story.ValidateOption(sceneId);
            // OK!
        } else if (e.getSource() == musicButton) {
            if (Main.bMusic != null) {
               Main.bMusic.toggle();
               fixMusicButton();
            }
        }
    }     
}
