import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CVBuilderPremiumInput extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CVBuilderPremiumInput frame = new CVBuilderPremiumInput();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CVBuilderPremiumInput() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 796, 515);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 789, 50);
        contentPane.add(panel);

        JButton button_1 = new JButton("Back");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                UserLoginFrame frame = new UserLoginFrame();
                frame.setVisible(true);
                dispose();
            }
        });
        button_1.setBounds(10, 18, 53, 22);
        panel.add(button_1);

        JLabel lblPremiumSubscription = new JLabel("Premium Subscription");
        lblPremiumSubscription.setHorizontalAlignment(JLabel.CENTER);
        lblPremiumSubscription.setBounds(0, 0, 789, 50);
        panel.add(lblPremiumSubscription);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBounds(245, 95, 311, 340);
        contentPane.add(panel_1);

        JLabel lblEnterDetails = new JLabel("Enter details");
        lblEnterDetails.setHorizontalAlignment(JLabel.CENTER);
        lblEnterDetails.setBounds(57, 32, 198, 38);
        panel_1.add(lblEnterDetails);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(133, 114, 139, 20);
        panel_1.add(textField);

        JLabel label = new JLabel("Name");
        label.setBounds(52, 114, 46, 20);
        panel_1.add(label);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(52, 155, 67, 20);
        panel_1.add(lblEmail);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(133, 157, 139, 20);
        panel_1.add(textField_1);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(52, 201, 67, 20);
        panel_1.add(lblPhone);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(133, 203, 139, 20);
        panel_1.add(textField_2);

        JButton button = new JButton("Subscribe");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String name = textField.getText().toString();
				String email = textField_1.getText().toString();
                String phone = textField_2.getText().toString();
                
                Connection con = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cvbuilder", "root", "Chirag@123");
                    String query = "INSERT INTO cvbuilderpremium (name, email, phone) VALUES (?, ?, ?)";
                    
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, phone);
                    
                    int i = ps.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(button, "Registered successfully! Enjoy the premium features!");
                        dispose();
                        UserLoginDomainFrame userFrame = new UserLoginDomainFrame();
                		userFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(button, "Failed! Please enter the fields properly.");
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (con != null) {
                            con.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        button.setBounds(115, 279, 97, 22);
        panel_1.add(button);
    }
}

