import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.net.*;

public class GUI extends JFrame implements ActionListener {
    // The comments are just the yapping of the developer
    // Note: extend JFrame so we can use this keyword mostly
    // Note: inhereits ActionListener so we can call component actions! 
    private boolean enabled = true;

    // Components of GUI
    // -Main Components

    
    // -Menu
    private JMenu menu; private JMenuBar mb;
    private JMenuItem itemAbout;
    private JButton button1, button2;
    
    
    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStat) {
        enabled = newStat;
    }
    
    public GUI() {
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
    
    public void UpdateStoryContent() {
        
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
        
        // Create content

        JPanel contentPanel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Current Scene");
        contentPanel.setBorder(border);

        JLabel thumb = new JLabel();
        
        JTextArea content = new JTextArea(15, 25);
        content.setEditable(false);
        content.setText("Buncha text with a lot of a a a a a a a a a a a a a a a a a a a a a a a a a a a a lol");
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        int numOfLines = content.getLineCount();
        content.append(", also you have " + numOfLines + " in this thingy thing thing");

        JScrollPane scrollPane = new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        contentPanel.add(scrollPane);
        mainPanel.add(contentPanel);
        
        // create buttons

        button1 = new JButton("Option 1");
        button1.setToolTipText("Select option 1");
        
        button2 = new JButton("Option 2");
        button2.setToolTipText("Select option 2");

        mainPanel.add(button1);
        button1.addActionListener(this);
        mainPanel.add(button2);
        button2.addActionListener(this);

        
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
            JOptionPane.showMessageDialog(null, "This is a GUI window for " + Misc.softwareName + "."+
                "\nProgram " + Misc.softwareName + " by \n" + Misc.creator);
        } else if (e.getSource() == button1) {
            JOptionPane.showMessageDialog(null, "Option 1 pressed.");
        } else if (e.getSource() == button2) {
            JOptionPane.showMessageDialog(null, "Option 2 pressed.");
        }
    }     
}