package com.proiectpa.game.userinterface.pages;

import com.proiectpa.database.GameDAO;
import com.proiectpa.game.userinterface.buttons.MyButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Clasa este folosita pentru a descrie si gestiona toate evenimentele ce tin de pagina in de login a aplicatiei
 */
public class LoginPage extends JPanel {
    //Application initial width and height
    private static final int appInitialWidth = 580;
    private static final int appInitialHeight = 720;

    private static final Color lightPurple = Color.decode("#b7adcf");

    private static final Color darkPurple = Color.decode("#5d4a66");
    private static final Color yellowHoney = Color.decode("#FACE75");
    private static final Color blueVerde = Color.decode("#82a79b");
    private static final Color blueVerdeMaiDeschis = Color.decode("#b3d0bf");
    private static final Color verdeMinty = Color.decode("#bdf7b7");
    private static final Color albGri = Color.decode("#dee7e7");
    //First page width and height
    private static final int pageWidth = appInitialWidth;
    private static final int pageHeight = appInitialHeight;
    public static String userN;
    private final JPanel container1;
    private final JPanel syncPanel;
    private MyButton connect;
    private static boolean usernameIsOk = false;

    private int themeTable;
    private int themeBack;

    private Color temaback;
    private Color tematabla;

    /**
     * Constructorul initializeaza pagina de login cu temele primite ca si parametru, cat si creeaza intreaga pagina
     * de login cu toate featurele acesteia
     *
     * @param tema1 Culoarea temei cu numarul 1 din pagina curenta
     * @param tema2 Culoarea temei cu numarul 2 din pagina curenta
     */
    LoginPage(Color tema1, Color tema2) {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(pageWidth, pageHeight));

        setBackground(tema1);

        int container1Height = pageHeight - 100;
        int container1Width = pageWidth - 100;

        this.add(Box.createVerticalStrut(pageHeight - (pageHeight - 130)), BorderLayout.NORTH);
        this.add(Box.createVerticalStrut(pageHeight - (pageHeight - 150)), BorderLayout.SOUTH);
        this.add(Box.createHorizontalStrut(pageWidth - (pageWidth - 50)), BorderLayout.WEST);
        this.add(Box.createHorizontalStrut(pageWidth - (pageWidth - 50)), BorderLayout.EAST);

        container1 = new JPanel(new BorderLayout());
        syncPanel = new JPanel();
        syncPanel.setLayout(null);
        syncPanel.setPreferredSize(new Dimension((int) (container1Width - container1Width / 2.5), container1Height));

        syncPanel.setBackground(tema2);

        syncPanel.setVisible(true);

        JLabel welcome = new JLabel();
        welcome.setText("WELCOME TO...");
        welcome.setForeground(darkPurple);
        welcome.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        welcome.setBounds(140, 20, (int) (container1Width - container1Width / 2), 50);
        syncPanel.add(welcome);

        JLabel title = new JLabel();
        title.setText("MINESWEEPER");
        title.setForeground(darkPurple);
        title.setFont(new Font("Arial Black", Font.BOLD, 40));
        title.setBounds(70, 60, (int) (container1Width - 30), 50);
        syncPanel.add(title);

        JLabel titleShadow = new JLabel();
        titleShadow.setText("MINESWEEPER");
        titleShadow.setForeground(albGri);
        titleShadow.setFont(new Font("Arial Black", Font.BOLD, 40));
        titleShadow.setBounds(72, 62, (int) (container1Width - 30), 50);
        syncPanel.add(titleShadow);

        JLabel logintext = new JLabel();
        logintext.setText("Please login");
        logintext.setForeground(lightPurple.darker());
        logintext.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        logintext.setBounds(165, 160, (int) (container1Width - 30), 50);
        syncPanel.add(logintext);

        JTextField inputUML = new JTextField("", 20);
        inputUML.setText("Your name...");
        inputUML.setFont(new Font("Comic sans", Font.PLAIN, 18));
        inputUML.setForeground(darkPurple);
        inputUML.setBounds(120, 200, 250, 30);
        inputUML.setBackground(lightPurple.darker());

        inputUML.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                while(!usernameIsOk){
                    userN = inputUML.getText(); //perform your operation
                    if (userN.length() > 30)
                        JOptionPane.showMessageDialog(syncPanel.getComponent(0), "Set a name for your user smaller than 30 characters");
                    else {
                        GameDAO.addNewUser(userN);
                        GameDAO.insertNewLoggedUser(userN);
                        usernameIsOk = true;
                    }
                }
            }
        });
        syncPanel.add(inputUML, BorderLayout.CENTER);

        connect = new MyButton("CONNECT");
        connect.setFont(new Font("Arial Black", Font.BOLD, 20));
        connect.setBackground(yellowHoney);
        connect.setForeground(darkPurple);
        connect.setFocusable(false);
        connect.setBorder(new LineBorder(blueVerde));
        connect.setBounds(125, 300, 240, 50);
        connect.setPressedBackgroundColor(yellowHoney);
        syncPanel.add(connect);

        container1.add(syncPanel, BorderLayout.CENTER);
        this.add(container1, BorderLayout.CENTER);

    }

    /**
     * Metoda returneaza butonul de 'Connect' din pagina
     *
     * @return Returneaza un buton de tipul JButton
     */
    public JButton getConnectButton() {
        return connect;
    }
}





