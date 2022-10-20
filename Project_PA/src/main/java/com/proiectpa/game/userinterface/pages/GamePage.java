package com.proiectpa.game.userinterface.pages;

import com.proiectpa.database.GameDAO;
import com.proiectpa.game.logic.Network;
import com.proiectpa.game.userinterface.buttons.ButonJoc;
import com.proiectpa.game.userinterface.buttons.MyButton;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clasa este folosita pentru a descrie si gestiona toate evenimentele ce tin de pagina in care jucatorul se poate
 * juca jocul
 */
public class GamePage extends JPanel implements Runnable{

    private Network game;
    int[][] gamematrix;
    private int x,y;

    private static final int appInitialWidth = 580;
    private static final int appInitialHeight = 720;

    private static final Color lightPurple = Color.decode("#b7adcf");

    private static final Color darkPurple = Color.decode("#5d4a66");
    private static final Color yellowHoney = Color.decode("#FACE75");
    private static final Color blueVerde = Color.decode("#82a79b");
    private static final Color blueVerdeMaiDeschis = Color.decode("#b3d0bf");
    private static final Color verdeMinty = Color.decode("#bdf7b7");
    private static final Color albGri = Color.decode("#dee7e7");

    private static String userN;
    private static String scor;
    private boolean winner=false;
    private int level=2;

    private MyButton connect;
    private static MyButton settings = new MyButton("Settings");

    private final JPanel container1, container2, container3;
    private final JPanel syncPanel, syncPanel2, syncPanel3;

    //First page width and height
    private static final int pageWidth=appInitialWidth;
    private static final int pageHeight=appInitialHeight;

    private static boolean started=false;

    private static SimpleDateFormat timer;
    JLabel time = new JLabel();
    JLabel timeShadow = new JLabel();

    Thread thread = null;

    /**
     * Metoda are rolul de a returna o componenta grafica care face referire la joc. In acest caz clasa actuala este chiar
     * acea componenta grafica ce gestioneaza jocul
     *
     * @return Returneaza pagina cu jocul, in caz de fata chiar clasa actuala
     */
    public JPanel getGame(){
        return this;
    }

    /**
     * Metoda are rolul de a marca faptul ca jocul a fost incepand prin apasarea primului buton de pe tabla de joc
     *
     * @param started Parametrul este folosit pentru a seta faptul ca jocul a fost inceput
     */
    public void setStarted(boolean started){
        this.started=started;
    }

    /**
     * Constructorul instantiaza pagina de joc cu culorile primite ca si parametru, iar nivelul este ales in functie
     * de parametru 'level' primit
     *
     * @param tema1 Culoarea temei cu numarul 1 din pagina curenta
     * @param tema2 Culoarea temei cu numarul 2 din pagina curenta
     * @param level Nivelul cu care este instantiata pagina de joc
     */
    GamePage(Color tema1, Color tema2, int level) {
        this.level=level;

        game = new Network(level);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(pageWidth, pageHeight));

        Date startTime = new Date();
        Date stopTime =new Date();
        timer=new SimpleDateFormat("mm:ss");
        timer.format(startTime.getTime()-stopTime.getTime());

        setBackground(tema1);

        int container1Height = pageHeight - 100;
        int container1Width = pageWidth - 100;

        this.add(Box.createVerticalStrut(pageHeight - (pageHeight - 130)), BorderLayout.NORTH);
        this.add(Box.createVerticalStrut(pageHeight - (pageHeight - 150)), BorderLayout.SOUTH);
        this.add(Box.createHorizontalStrut(pageWidth - (pageWidth - 50)), BorderLayout.WEST);
        this.add(Box.createHorizontalStrut(pageWidth - (pageWidth - 50)), BorderLayout.EAST);

        container1 = new JPanel(new BorderLayout());
        container2 = new JPanel(new BorderLayout());
        container3 = new JPanel(new BorderLayout());

        syncPanel = new JPanel();
        syncPanel.setLayout(null);
        syncPanel.setPreferredSize(new Dimension((int) (container1Width - container1Width / 2.5), container1Height));

        syncPanel.setBackground(tema2);

        int container2Height = pageHeight - 610;
        int container2Width = pageWidth - 300;

        syncPanel2 = new JPanel();
        syncPanel2.setLayout(null);
        syncPanel2.setPreferredSize(new Dimension((int) (container2Width - container2Width / 1.5), container2Height));
        syncPanel2.setBackground(tema2);

        syncPanel3 = new JPanel();
        syncPanel3.setLayout(null);
        syncPanel3.setPreferredSize(new Dimension((int) (container2Width - container2Width / 0.5), container2Height));

        syncPanel3.setBackground(tema2);

        game.initializareMap();

        gamematrix=game.getGameMatrix();

        for (int i = 0; i < level*10+3; i++)
            for (int j = 0; j < level*10+3; j++) {
                String val= Integer.toString(gamematrix[i][j]);
                if(level==2)
                {
                    ButonJoc But1 = new ButonJoc("", j * 20 +10, i * 20 +20 , (i + j) % 2, val, this, started,level);
                    game.addToGame(0,But1);
                    syncPanel.add(But1, BorderLayout.WEST);
                }
                else if(level==3) {
                    ButonJoc But1 = new ButonJoc("", j * 14 +10 , i * 14 +20, (i + j) % 2, val, this, started, level);
                    game.addToGame(0,But1);
                    syncPanel.add(But1, BorderLayout.WEST);
                }
                else if(level==1)
                {
                    ButonJoc But1 = new ButonJoc("", j * 30 +40 , i * 30 +50 , (i + j) % 2, val, this, started,level);
                    game.addToGame(0,But1);
                    syncPanel.add(But1, BorderLayout.WEST);
                }
            }

        settings.setFont(new Font("Arial Black", Font.BOLD, 20));
        settings.setBackground(yellowHoney);
        settings.setForeground(darkPurple);
        settings.setFocusable(false);
        settings.setBorder(new LineBorder(blueVerde));
        settings.setBounds(170, 10, 240, 50);
        settings.setPressedBackgroundColor(yellowHoney);
        syncPanel2.add(settings);

        JLabel title = new JLabel();
        title.setText("MINESWEEPER");
        title.setForeground(darkPurple);
        title.setFont(new Font( "Arial Black",Font.BOLD,40 ));
        title.setBounds(50,30,(int) (container1Width-30),50);
        syncPanel3.add(title);

        JLabel titleShadow = new JLabel();
        titleShadow.setText("MINESWEEPER");
        titleShadow.setForeground(albGri);
        titleShadow.setFont(new Font( "Arial Black",Font.BOLD,40 ));
        titleShadow.setBounds(52,32,(int) (container1Width-30),50);
        syncPanel3.add(titleShadow);

        time.setText(timer.format(startTime.getTime()-stopTime.getTime()));
        time.setFont(new Font( Font.MONOSPACED,Font.BOLD,30 ));
        time.setForeground(yellowHoney);
        time.setBounds(470, 30, 240, 50);
        syncPanel3.add(time);

        timeShadow.setText(timer.format(startTime.getTime()-stopTime.getTime()));
        timeShadow.setFont(new Font( Font.MONOSPACED,Font.BOLD,30 ));
        timeShadow.setBounds(472, 32, 240, 50);
        timeShadow.setForeground(darkPurple);
        syncPanel3.add(timeShadow);

        container3.add(syncPanel3, BorderLayout.CENTER);
        container2.add(syncPanel2, BorderLayout.CENTER);
        container1.add(syncPanel, BorderLayout.CENTER);

        this.add(container3,BorderLayout.NORTH);
        this.add(container1, BorderLayout.CENTER);
        this.add(container2, BorderLayout.SOUTH);
    }

    /**
     * Metoda are rolul de a pornit thredul pentru timmer doar in cazul in care acesta nu fost pornit inca sau era
     * deja oprit
     */
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Metoda are rolul de a opri threadul pentru timmer si de a reinitializa valoarea variabilei 'started' inapoi in
     * false, marcand astfel ca jocul a fost terminat si se asteapta inceperea altuia
     */
    public void stop() {
        thread = null;
        started = false;
    }

    /**
     * Metoda este mostenita de la interfata Runnable si are rolul de a descrie comportamentul threadului pentru timmer
     * in timpul jocului. Tot in cadrul acesteia este memorat scorul jocului curent
     */
    public void run() {
        while (thread != null ) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                started=false;
            }
            Date startTime = new Date(); // time right now
            SimpleDateFormat scoreSeconds = new SimpleDateFormat("ss");

            while(!isWinner()){
                Date now = new Date();
                time.setText(timer.format(now.getTime()-startTime.getTime()));
                timeShadow.setText(timer.format(now.getTime()-startTime.getTime()));

                scor=scoreSeconds.format(now.getTime()-startTime.getTime());
            }
        }
        thread = null;
    }

    /**
     * Metoda are rolul de a seta o pozitie pe axa X la care sa fie asezat un buton
     *
     * @param x Pozitia pe axa X unde este pozitionat butonul
     */
    public void setXButton(int x)
    {
        this.x=x;
    }

    /**
     * Metoda are rolul de a seta o pozitie pe axa Y la care sa fie asezat un buton, cat si de a marca ca fiind
     * apasate toate butonele cu valori nule din jurul butonului
     *
     * @param y Pozitia pe axa Y unde este pozitionat butonul
     */
    public void setYButton(int y)
    {
        this.y=y;
        setPressedNullValues(x,y);
    }

    /**
     * Metoda are rolul de a returna scorul dupa terminarea jocului
     *
     * @return Returneaza scorul dunb forma de String
     */
    public String getScor()
    {
        return scor;
    }

    /**
     * Metoda are rolul de a selecta taote valorile nule din jurul unui buton apasat si de a le descoperi. In cazul
     * in care nu se mai gasesc valori nule de descoperit, se verifica daca jocul este gata, iar in caz afirmativ
     * este verificat daca jucatorul a castigat sau nu jocul, afisandu-se mesajele aferente
     *
     * @param x Valoarea pe axa X de unde incepe sa marcheze valorile nule
     * @param y Valoarea pe axa Y de unde incepe sa marcheze valorile nule
     */
    public void setPressedNullValues(int x, int y)
    {
        ArrayList<ButonJoc> joc=game.getGame();

        int j=(x-10)/20;
        int i=(y-20)/20;

        if(level==1)
        {
            j=(x-40)/30;
            i=(y-50)/30;
        }
        else if (level ==3)
        {
            j=(x-10)/14;
            i=(y-20)/14;
        }

        String key = joc.get(i*(level*10+3)+j).getVal();
        game.searchMiniMap(i,j);

        int[][] conect=game.getConect();

        if(key.equals("0"))
        {
            for (int l=1;l<(level*10+3)-1;l++)
                for(int m=1;m<(level*10+3)-1;m++)
                {
                    if(conect[l][m]==1)
                    {

                        joc.get(l*(level*10+3)+m+1).setPressed(true);
                        joc.get(l*(level*10+3)+m-1).setPressed(true);
                        joc.get((l+1)*(level*10+3)+m).setPressed(true);
                        joc.get((l+1)*(level*10+3)+m+1).setPressed(true);
                        joc.get((l+1)*(level*10+3)+m-1).setPressed(true);
                        joc.get((l-1)*(level*10+3)+m).setPressed(true);
                        joc.get((l-1)*(level*10+3)+m+1).setPressed(true);
                        joc.get((l-1)*(level*10+3)+m-1).setPressed(true);
                    }
                }

        }
        if(key.equals("10"))
        {
            for(ButonJoc buton:joc)
            {
                buton.setPressed(true);

            }

            JPanel containerwinner;
            JPanel syncPanelwinner;

            containerwinner = new JPanel(new BorderLayout());
            int container1Height = pageHeight - 610;
            int container1Width = pageWidth - 300;

            syncPanelwinner = new JPanel();
            syncPanelwinner.setLayout(null);
            syncPanelwinner.setPreferredSize(new Dimension((int) (container1Width - container1Width / 2.5), container1Height));

            syncPanelwinner.setBackground(yellowHoney);


            JLabel titleShadow = new JLabel();
            titleShadow.setText(" You lost " + getScor());
            titleShadow.setForeground(darkPurple);
            titleShadow.setBackground(yellowHoney);
            titleShadow.setFont(new Font( "Arial Black",Font.BOLD,40 ));
            titleShadow.setBounds(52,32,(int) (appInitialWidth-30),50);

            syncPanelwinner.add(titleShadow);
            containerwinner.add(syncPanelwinner, BorderLayout.CENTER);
            this.add(containerwinner,BorderLayout.NORTH);
            winner=true;
            stop();

        }
        else if(verifyWinner()==0)
        {
            for(ButonJoc buton:joc)
            {
                buton.setPressed(true);
            }

            JPanel containerwinner;
            JPanel syncPanelwinner;

            containerwinner = new JPanel(new BorderLayout());
            int container1Height = pageHeight - 610;
            int container1Width = pageWidth - 300;

            syncPanelwinner = new JPanel();
            syncPanelwinner.setLayout(null);
            syncPanelwinner.setPreferredSize(new Dimension((int) (container1Width - container1Width / 2.5), container1Height));

            syncPanelwinner.setBackground(yellowHoney);


            JLabel titleShadow = new JLabel();
            titleShadow.setText(" You win " + getScor());
            titleShadow.setForeground(darkPurple);
            titleShadow.setBackground(yellowHoney);
            titleShadow.setFont(new Font( "Arial Black",Font.BOLD,40 ));
            titleShadow.setBounds(52,32,(int) (appInitialWidth-30),50);

            syncPanelwinner.add(titleShadow);
            containerwinner.add(syncPanelwinner, BorderLayout.CENTER);
            this.add(containerwinner,BorderLayout.NORTH);
            winner=true;
            stop();
            GameDAO.insertUserScore(LoginPage.userN, Integer.parseInt(scor));
        }
    }

    /**
     * Metoda are rolul de a verifica daca jucatorul a castigat jocul
     *
     * @return Returneaza 0 daca meciul a fost castigat si orice alta valoare in caz contrar
     */
    public int verifyWinner()
    {
        ArrayList<ButonJoc> joc=game.getGame();
        int ok=-1;
        for (ButonJoc buton:joc)
        {
            if(!(buton.isPressed())&& !(buton.getVal().equals("10")))
            {
                ok++;
            }
        }
        if(ok==0) winner=true;
        return ok;
    }

    /**
     * Metoda are rolul de a returna valoarea de adevar a variabilei 'winner' si de a returna daca un jucator a castigat
     * sau nu jocul
     *
     * @return Returneaza true daca a castigat si false in caz contrar
     */
    public boolean isWinner() {
        return winner;
    }

    /**
     * Metoda are rolul de a returna butonul responsabil cu trecerea la pagina cu setari
     *
     * @return Returneaza butonul pentru pagina de setari
     */
    public MyButton getSettings(){
        return settings;
    }

}