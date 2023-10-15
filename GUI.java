import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;


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
    
    
    public boolean getStatus() {
        return enabled;
    }

    public void setStatus(boolean newStat) {
        enabled = newStat;
    }
    
    public GUI() {
        constructGUI();
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

        // create buttons

        JButton button1 = new JButton("Send");
        button1.setToolTipText("Click to send a message");

        mainPanel.add(button1);

        // Create content

        JPanel contentPanel = new JPanel();
        Border border = BorderFactory.createTitledBorder("Current Scene");
        contentPanel.setBorder(border);

        JTextArea content = new JTextArea(15, 20);
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
        }
    }     
}