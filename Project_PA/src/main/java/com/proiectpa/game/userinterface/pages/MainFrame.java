package com.proiectpa.game.userinterface.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa este folosita pentru a gestiona toate paginile din cadrul aplicatiei. Este clasa care sta la baza crearii jocului
 */
public class MainFrame {
    private static final int appWidth =580;
    private static final int appHeight =720;

    private static final Color lightPurple = Color.decode("#b7adcf");

    private static final Color darkPurple = Color.decode("#5d4a66");
    private static final Color yellowHoney = Color.decode("#FACE75");
    private static final Color blueVerde = Color.decode("#82a79b");
    private static final Color blueVerdeMaiDeschis = Color.decode("#b3d0bf");
    private static final Color verdeMinty = Color.decode("#bdf7b7");
    private static final Color albGri = Color.decode("#dee7e7");

    private JFrame mainFrame;

    private CardLayout cardLayoutPages;
    private CardLayout cardLayoutWholePages;

    //The panel which will contain the page associated with a button
    private JPanel panelSidePageContainer;
    private JPanel panelWholePageContainer;
    private JPanel pagePanel;

    private LoginPage loginPage;
    private SettingsPage settingsPage;
    private GamePage gamePage;

    private int themeTable=1;
    private int themeBack=1;

    /**
     * Cosntructorul clasei doar initializeaza structura aplicatiei
     */
    public MainFrame(){
        pagePanel =new JPanel();
        pagePanel.setLayout( new BorderLayout() );

        mainFrame=new JFrame("Minesweeper championship");

        mainFrame.setPreferredSize( new Dimension( appWidth , appHeight ) );
        mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        mainFrame.setLayout( new FlowLayout() );
        mainFrame.setResizable( false );

        //Make the cardLayoutWholePages
        cardLayoutWholePages=new CardLayout();

        //Add the whole page container
        panelWholePageContainer=new JPanel();

        panelWholePageContainer.setLayout( cardLayoutWholePages );

        //Make the cardLayoutPages
        cardLayoutPages=new CardLayout();

        //Adding the pages in the card container and giving them a proper id(name)
        panelSidePageContainer =new JPanel();
        panelSidePageContainer.setLayout( cardLayoutPages );

        loginPage = new LoginPage(blueVerde,blueVerdeMaiDeschis);
        gamePage=new GamePage(blueVerde, blueVerdeMaiDeschis,2);
        settingsPage=new SettingsPage(this,themeBack,themeTable);

        panelSidePageContainer.add( loginPage,"loginPage" );
        panelSidePageContainer.add( gamePage,"gamePage" );
        panelSidePageContainer.add( settingsPage,"settingsPage" );

        loginPage.getConnectButton().addActionListener( this::whenConnectButtonPressed );
        gamePage.getSettings().addActionListener(this::whenSettingsButtonPressed);
        settingsPage.getDisconnect().addActionListener( this::whenDisconnectButtonPressed );
        settingsPage.getBackToGame().addActionListener( this::whenBackToGameButtonPressed );

        //Don't forget to add panelPages to the wholePage panel
        panelWholePageContainer.add( loginPage,"loginPage" );
        panelWholePageContainer.add( gamePage ,"gamePage" );
        panelWholePageContainer.add( settingsPage ,"settingsPage" );

        cardLayoutWholePages.show( panelWholePageContainer,"loginPage" );

        mainFrame.add( panelWholePageContainer );

        pagePanel.add( panelSidePageContainer,BorderLayout.CENTER );

        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Metoda este folosita pentru a descrie ce se intampla atunci cand este apasat butonul 'Back to game' din pagina
     * de setari
     *
     * @param e Evenimentul care se petrece la apelarea metodei
     */
    private void whenBackToGameButtonPressed( ActionEvent e ) {
        cardLayoutWholePages.show( panelWholePageContainer,"gamePage" );

        int level=settingsPage.getLevel();
        Color tema1=settingsPage.getTemaback();
        Color tema2=settingsPage.getTematabla();
        gamePage=new GamePage(tema1, tema2,level);
        panelSidePageContainer.add( gamePage,"gamePage" );
        panelWholePageContainer.add(gamePage, "gamePage");
        cardLayoutWholePages.show( panelWholePageContainer,"gamePage" );
        cardLayoutPages.show( panelSidePageContainer ,"gamePage" );
    }

    /**
     * Metoda este folosita pentru a descrie ce se intampla atunci cand este apasat butonul 'Connect' din pagina
     * de login
     *
     * @param e Evenimentul care se petrece la apelarea metodei
     */
    private void whenConnectButtonPressed( ActionEvent e ) {
        System.out.println(LoginPage.userN);
        if(LoginPage.userN!=null) {
            cardLayoutWholePages.show(panelWholePageContainer, "gamePage");
            cardLayoutPages.show(panelSidePageContainer, "gamePage");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "   You  need to connect with an user. \n Please press ENTER after you insert your name","InfoBox: Connection error" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Metoda este folosita pentru a descrie ce se intampla atunci cand este apasat butonul 'Settings' din pagina
     * jocului
     *
     * @param e Evenimentul care se petrece la apelarea metodei
     */
    private void whenSettingsButtonPressed( ActionEvent e ) {
        cardLayoutWholePages.show( panelWholePageContainer,"settingsPage" );
        cardLayoutPages.show( panelSidePageContainer ,"settingsPage" );
        gamePage.stop();
    }

    /**
     * Metoda este folosita pentru a descrie ce se intampla atunci cand este apasat butonul 'Disconnect' din pagina
     * de setari
     *
     * @param e Evenimentul care se petrece la apelarea metodei
     */
    private void whenDisconnectButtonPressed( ActionEvent e ) {
        cardLayoutWholePages.show( panelWholePageContainer,"loginPage" );
        cardLayoutPages.show(panelSidePageContainer,"loginPage");
    }

    /**
     * Metoda are rolul de a seta cu o anumita valoare primita ca si parametru tema din fata a oricarei pagini
     *
     * @param themeTable Valoarea numerica a temei care trebuie setata
     */
    public void setThemeTable(int themeTable) {
        this.themeTable = themeTable;
    }

    /**
     * Metoda are rolul de a seta cu o anumita valoare primita ca si parametru tema din fundalul oricarei pagini
     *
     * @param themeBack Valoarea numerica a temei care trebuie setata
     */
    public void setThemeBack(int themeBack) {
        this.themeBack = themeBack;
    }
}