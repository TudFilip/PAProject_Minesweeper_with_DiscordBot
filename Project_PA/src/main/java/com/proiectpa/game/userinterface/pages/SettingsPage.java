package com.proiectpa.game.userinterface.pages;

import com.proiectpa.game.userinterface.buttons.MyButton;
import com.proiectpa.game.userinterface.cross.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa are rolul de a descrie si a gestiona toate evenimentele ce tin de pagina cu setari a jocului
 */
public class SettingsPage extends JPanel {
    private static final int appInitialWidth = 580;
    private static final int appInitialHeight = 720;

    private static final Color lightPurple = Color.decode("#b7adcf");

    private static final Color darkPurple = Color.decode("#5d4a66");
    private static final Color yellowHoney = Color.decode("#FACE75");
    private static final Color blueVerde = Color.decode("#82a79b");
    private static final Color blueVerdeMaiDeschis = Color.decode("#b3d0bf");
    private static final Color verdeMinty = Color.decode("#bdf7b7");
    private static final Color albGri = Color.decode("#dee7e7");

    private Color temaback=blueVerde;
    private Color tematabla=blueVerdeMaiDeschis  ;
    private int level=2;

    private static String userN;
    private int themeTable;
    private int themeBack;

    private MyButton disconnect, backToGame;

    private final JPanel container1, container2;
    private final JPanel syncPanel, syncPanel2;

    //First page width and height
    private static final int pageWidth=appInitialWidth;
    private static final int pageHeight=appInitialHeight;

    /**
     * Constructorul are rolul de a initializa pagina cu setari, in el facandu-se toate operatiile necesare crearii
     * si functionarii paginii
     *
     * @param frame Frameul din care face parte pagina
     * @param themeBack Valoarea numerica a temei din fundal
     * @param themeTable Valoarea numerica a temei din fata
     */
    SettingsPage(MainFrame frame, int themeBack, int themeTable){
        this.themeBack=themeBack;
        this.themeTable=themeTable;

        this.setLayout( new BorderLayout());
        this.setPreferredSize(new Dimension(pageWidth,pageHeight));

        if(themeBack==1)
            this.setBackground(blueVerde);
        else if(themeBack==2) this.setBackground(lightPurple.darker());
        else if(themeBack==3) this.setBackground(yellowHoney.darker());
        else if(themeBack==4) this.setBackground(verdeMinty.darker());

        int container1Height = pageHeight-100;
        int container1Width = pageWidth-100;

        this.add( Box.createVerticalStrut(pageHeight-(pageHeight-130)), BorderLayout.NORTH);
        this.add( Box.createVerticalStrut(pageHeight-(pageHeight-150)), BorderLayout.SOUTH);
        this.add( Box.createHorizontalStrut(pageWidth-(pageWidth-50)), BorderLayout.WEST);
        this.add( Box.createHorizontalStrut(pageWidth-(pageWidth-50)), BorderLayout.EAST);

        container1 = new JPanel(new BorderLayout());
        container2 = new JPanel(new BorderLayout());

        syncPanel = new JPanel();
        syncPanel.setLayout(null);
        syncPanel.setPreferredSize(new Dimension((int) (container1Width-container1Width/2.5),container1Height));

        syncPanel.setBackground(blueVerdeMaiDeschis);

        int container2Height = pageHeight - 610;
        int container2Width = pageWidth - 300;

        syncPanel2 = new JPanel();
        syncPanel2.setLayout(null);
        syncPanel2.setPreferredSize(new Dimension((int) (container2Width - container2Width / 1.5), container2Height));
        syncPanel2.setBackground(blueVerdeMaiDeschis);


        JLabel title = new JLabel();
        title.setText("SETTINGS");
        title.setForeground(darkPurple);
        title.setFont(new Font( "Arial Black",Font.BOLD,40 ));
        title.setBounds(40,20,(int) (container1Width-30),50);
        syncPanel.add(title);

        JLabel titleShadow = new JLabel();
        titleShadow.setText("SETTINGS");
        titleShadow.setForeground(yellowHoney);
        titleShadow.setFont(new Font( "Arial Black",Font.BOLD,40 ));
        titleShadow.setBounds(42,22,(int) (container1Width-30),50);
        syncPanel.add(titleShadow);

        backToGame = new MyButton("BACK TO GAME");
        backToGame.setFont(new Font("Arial Black",Font.BOLD,20));
        backToGame.setBackground(yellowHoney);
        backToGame.setForeground(darkPurple);
        backToGame.setFocusable(false);
        backToGame.setBorder(new LineBorder(blueVerde));
        backToGame.setBounds(125,350,240,50);
        backToGame.setPressedBackgroundColor(yellowHoney);
        syncPanel.add(backToGame);

        JLabel change1 = new JLabel();
        change1.setText("Change color for table game");
        change1.setForeground(darkPurple);
        change1.setFont(new Font(Font.MONOSPACED,Font.BOLD,20 ));
        change1.setBounds(52,80,(int) (container1Width-30),50);
        syncPanel.add(change1);

        MyButton color1 = new MyButton("");
        color1.setBackground(lightPurple);
        color1.setFocusable(false);
        color1.setBorder(new LineBorder(blueVerde));
        color1.setBounds(52,130,50,40);
        color1.setPressedBackgroundColor(yellowHoney);
        color1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeTable(1);
                syncPanel.setBackground(lightPurple);
                syncPanel2.setBackground(lightPurple);
                tematabla=lightPurple;
            }
        });
        syncPanel.add(color1);
        MyButton color2 = new MyButton("");
        color2.setBackground(blueVerdeMaiDeschis);
        color2.setFocusable(false);
        color2.setBorder(new LineBorder(blueVerde));
        color2.setBounds(162,130,50,40);
        color2.setPressedBackgroundColor(yellowHoney);
        color2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeTable(2);
                syncPanel.setBackground(blueVerdeMaiDeschis);
                syncPanel2.setBackground(blueVerdeMaiDeschis);

                tematabla=blueVerdeMaiDeschis;

            }
        });
        syncPanel.add(color2);

        MyButton color3 = new MyButton("");
        color3.setBackground(yellowHoney);
        color3.setFocusable(false);
        color3.setBorder(new LineBorder(blueVerde));
        color3.setBounds(272,130,50,40);
        color3.setPressedBackgroundColor(yellowHoney);
        color3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeTable(3);
                syncPanel.setBackground(yellowHoney);
                syncPanel2.setBackground(yellowHoney);

                tematabla=yellowHoney;
            }
        });
        syncPanel.add(color3);

        MyButton color4 = new MyButton("");
        color4.setBackground(verdeMinty);
        color4.setFocusable(false);
        color4.setBorder(new LineBorder(blueVerde));
        color4.setBounds(382,130,50,40);
        color4.setPressedBackgroundColor(yellowHoney);
        color4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeTable(4);
                syncPanel.setBackground(verdeMinty);
                syncPanel2.setBackground(verdeMinty);

                tematabla=verdeMinty;
            }
        });
        syncPanel.add(color4);

        JLabel change2 = new JLabel();
        change2.setText("Change color for background game");
        change2.setForeground(darkPurple);
        change2.setFont(new Font(Font.MONOSPACED,Font.BOLD,20 ));
        change2.setBounds(52,170,(int) (container1Width-30),50);
        syncPanel.add(change2);

        MyButton back1 = new MyButton("");
        back1.setBackground(blueVerde);
        back1.setFocusable(false);
        back1.setBorder(new LineBorder(blueVerde));
        back1.setBounds(52,220,50,40);
        back1.setPressedBackgroundColor(yellowHoney);
        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeBack(1);
                setBackground(blueVerde);
                temaback=blueVerde;
            }
        });
        syncPanel.add(back1);


        MyButton back2= new MyButton("");
        back2.setBackground(lightPurple.darker());
        back2.setFocusable(false);
        back2.setBorder(new LineBorder(blueVerde));
        back2.setBounds(162,220,50,40);
        back2.setPressedBackgroundColor(yellowHoney);
        back2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeBack(2);
                setBackground(lightPurple.darker());
                temaback=lightPurple.darker();
            }
        });
        syncPanel.add(back2);


        MyButton back3 = new MyButton("");
        back3.setBackground(yellowHoney.darker());
        back3.setFocusable(false);
        back3.setBorder(new LineBorder(blueVerde));
        back3.setBounds(272,220,50,40);
        back3.setPressedBackgroundColor(yellowHoney);
        back3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeBack(3);
                setBackground(yellowHoney.darker());
                temaback=yellowHoney.darker();
            }
        });
        syncPanel.add(back3);


        MyButton back4 = new MyButton("");
        back4.setBackground(verdeMinty.darker());
        back4.setFocusable(false);
        back4.setBorder(new LineBorder(blueVerde));
        back4.setBounds(382,220,50,40);
        back4.setPressedBackgroundColor(yellowHoney);
        back4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setThemeBack(4);
                setBackground(verdeMinty.darker());
                temaback=verdeMinty.darker();
            }
        });
        syncPanel.add(back4);

        JLabel change3 = new JLabel();
        change3.setText("Select level of difficulty");
        change3.setForeground(darkPurple);
        change3.setFont(new Font(Font.MONOSPACED,Font.BOLD,20 ));
        change3.setBounds(52,250,(int) (container1Width-30),50);
        syncPanel.add(change3);

        for ( int l=1;l<=3;l++)
        {
            MyButton lvl3 = new MyButton("hard");

            if(l==1) {lvl3.setText("easy");
                lvl3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        level=1;
                    }
                });}
            if(l==2) {lvl3.setText("medium");
                lvl3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        level=2;
                    }
                });}
            if(l==3) {lvl3.setText("hard");
                lvl3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        level=3;
                    }
                });}
            lvl3.setBackground(albGri.darker());
            lvl3.setFont(new Font(Font.MONOSPACED,Font.BOLD,13 ));;
            lvl3.setForeground(albGri.darker().darker());
            Pane s3 = new Pane(l);
            s3.setForeground(albGri);
            lvl3.add(s3);
            lvl3.setFocusable(false);
            lvl3.setBorder(new LineBorder(blueVerde));
            lvl3.setBounds(l*110,300,50,40);
            lvl3.setPressedBackgroundColor(yellowHoney);

            syncPanel.add(lvl3);
        }

        disconnect = new MyButton("DISCONNECT");
        disconnect.setFont(new Font("Arial Black",Font.BOLD,20));
        disconnect.setBackground(albGri.darker());
        disconnect.setForeground(darkPurple);
        disconnect.setFocusable(false);
        disconnect.setBorder(new LineBorder(blueVerde));
        disconnect.setBounds(173,20,240,50);
        disconnect.setPressedBackgroundColor(yellowHoney);
        syncPanel2.add(disconnect);

        container1.add(syncPanel, BorderLayout.CENTER);
        container2.add(syncPanel2, BorderLayout.CENTER);
        this.add(container1, BorderLayout.CENTER);
        this.add(container2, BorderLayout.SOUTH);
    }

    /**
     * Metoda seteaza confor unei valori tema din fundal
     *
     * @param themeBack Valoare temei care trebuie setata
     */
    public void setThemeBack(int themeBack) {
        this.themeBack = themeBack;
    }

    /**
     * Metoda seteaza confor unei valori tema din fata
     *
     * @param themeTable Valoare temei care trebuie setata
     */
    public void setThemeTable(int themeTable) {
        this.themeTable = themeTable;
    }

    /**
     * Metoda returneaza valoarea temei din fundalul paginii
     *
     * @return Valoarea temei din fundalul paginii
     */
    public int getThemeBack() {
        return themeBack;
    }

    /**
     * Metoda returneaza valoarea temei din partea din fata a paginii
     *
     * @return Valoarea temei din partea din fata a paginii
     */
    public int getThemeTable() {
        return themeTable;
    }

    /**
     * Metoda returneaza butonul de 'Disconnect' din pagina
     *
     * @return Butonul 'Disconnect' din pagina
     */
    public MyButton getDisconnect() {
        return disconnect;
    }

    /**
     * Metoda returneaza butonul de 'Back to game' din pagina
     *
     * @return Butonul 'Back to game' din pagina
     */
    public MyButton getBackToGame() {
        return backToGame;
    }

    /**
     * Metoda returneaza culoarea temei din fundalul paginii
     *
     * @return Culoarea din fundalul paginii
     */
    public Color getTemaback(){
        return temaback;
    }

    /**
     * Metoda are rolul de a returna rolul selectat de utilizator prin intermediul paginii
     *
     * @return Returneaza nivelul selectat de utilizator
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Metoda returneaza culoarea temei din partea din fata a paginii
     *
     * @return Culoarea din partea din fata a paginii paginii
     */
    public Color getTematabla() {
        return tematabla;
    }
}
