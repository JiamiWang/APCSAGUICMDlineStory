import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;

public class GUI extends JFrame implements ActionListener {
    // The comments are just the yapping of the developer
    // Note: extend JFrame so we can use this keyword mostly
    // Note: inhereits ActionListener so we can call component actions! 
    private boolean enabled = true;

    // Components of GUI
    // -Main Components
    private JPanel panel;
    private JTextField storyField;
    private JLabel title, subtitle;
    
    // -Menu
    private JMenu menu;
    private JMenuBar mb;  
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
        this.setLayout(new FlowLayout());
        this.setSize(500, 360);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // INITIALIZE DA PANEL

        panel = new JPanel();

        // Set Dimensions, so app is in middle of scrn

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();

        int xPos = (d.width / 2) - (this.getWidth() - 2);
        int yPos = (d.height / 2) - (this.getHeight() - 2);
        this.setLocation(xPos, yPos);
        this.setResizable(false);
        
        // Default Black Border
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        
        // create top bar menu
        mb = new JMenuBar();
        menu = new JMenu("About");
        
        itemAbout = new JMenuItem("About this program");  
        itemAbout.addActionListener(this);
        
        menu.add(itemAbout);
        mb.add(menu);
        
        // Create Title
        title = new JLabel("GUI Window for " + Misc.softwareName);
        // title.setBorder(border);
        title.setPreferredSize(new Dimension(250, 25));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        // // Create Subtitle
        // subtitle = new JLabel(Misc.creator);
        // subtitle.setBorder(border);
        // subtitle.setPreferredSize(new Dimension(350, 25));
        // subtitle.setHorizontalAlignment(JLabel.CENTER);
        // subtitle.setVerticalAlignment(JLabel.CENTER);

        // Add items, set visible
        this.setJMenuBar(mb);
        panel.add(title);
        // panel.add(subtitle);
        this.add(panel);
        this.setVisible(true);
    }

    public void actionPerformed (ActionEvent e) {    
        if (e.getSource() == itemAbout) {
            JOptionPane.showMessageDialog(null, "This is a GUI window for " + Misc.softwareName + "."+
                "\nProgram " + Misc.softwareName + " by \n" + Misc.creator);
        }
    }     
}