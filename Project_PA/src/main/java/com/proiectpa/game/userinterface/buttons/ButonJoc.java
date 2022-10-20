package com.proiectpa.game.userinterface.buttons;

import com.proiectpa.game.userinterface.pages.GamePage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clasa are rolul de a simula un buton pe tabla de joc si de a gestiona toate evenimentele ce tin de apasarea si
 * modificarea valorii unui buton
 */
public class ButonJoc extends JButton {
    private static final Color lightPurple = Color.decode("#b7adcf");

    private static final Color darkPurple = Color.decode("#5d4a66");
    private static final Color yellowHoney = Color.decode("#FACE75");
    private static final Color blueVerde = Color.decode("#82a79b");

    private String val;
    private boolean pressed=false;
    private int x,y;

    private Color pressedBackgroundColor;

    /**
     * Constructorul are rolul de a initializa un nou buton pe tabla de joc impreuna cu toate caracteristicile
     * initiale ale acestuia
     *
     * @param text Textul ce se doreste sa apara pe buton
     * @param x Pozitia pe axa X a butonului la initializare
     * @param y Pozitia pe axa Y a butonului la initializare
     * @param color Culoarea pe care o primeste butonul la initializare
     * @param textUpdate Valoarea numerica pe care o poate prelua pe timpul meciului butonul
     * @param game Pagina de joc de care apartine butonul
     * @param started Variabila ce are rolul de a marca daca jocul a fost inceput sau nu
     * @param level Levelul din joc din care face parte butonul
     */
    public ButonJoc(String text, int x, int y, int color, String textUpdate, GamePage game, boolean started, int level) {
        super(text);
        super.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setFont(new Font("Arial Black",Font.BOLD,40 -level*10));
        val=textUpdate;
        this.x=x;
        this.y=y;
        if(color==0)
            this.setBackground(darkPurple.brighter());
        else
            this.setBackground(lightPurple);
        this.setForeground(Color.BLACK);
        this.setFocusable(false);
        this.setBorder(new LineBorder(blueVerde));
        if(level==1)
            this.setBounds(x,y,30,30);
        else if (level==2)this.setBounds(x,y,20,20);
        else if(level==3) this.setBounds(x,y,14,14);
        this.setPressedBackgroundColor(yellowHoney);
        val=textUpdate;


        this.setUI(new BasicButtonUI() {
            @Override
            public void update(Graphics g, JComponent c) {
                if(c.isOpaque()) {
                    if (getModel().isPressed()) {
                        g.setColor(getBackground().darker());
                        if(val.equals("0"))  setText("");
                        else setText(val);
                        c.setEnabled(false);
                        c.setBackground(getBackground().darker());
                        game.setXButton(x);
                        game.setYButton(y);

                        pressed=true;

                        if(!started) {
                            Thread t = new Thread(game);
                            //t.start();
                            game.start();
                            game.setStarted(true);
                        }
                    }

                    else if (getModel().isRollover()) {
                        g.setColor(getBackground().brighter());
                    }
                    else {
                        g.setColor(getBackground());
                    }
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                paint(g,c);
            }

        });

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
                    if(pressed==false)
                        setBackground(Color.red.darker());
                }
                else if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 2) {
                    if(pressed==false)
                        if(color==0)
                            setBackground(darkPurple.brighter());
                        else
                            setBackground(lightPurple);
                }
            }
        });
    }

    /**
     * Metoda are rolul de a returna valoarea pe care o tine minte butonul, returnand variabila de tipul String 'val'
     *
     * @return Returneaza valoarea pe care o memoreaza butonul
     */
    public String getVal() {
        return val;
    }

    /**
     * Metoda are rolul de a verifica daca butonul a fost sau nu apasat pe timpul jocului si a returna variabila
     * 'pressed' de tipul boolean
     *
     * @return Returneaza o valoare de tipul boolean ce retine daca butonul a fost sau nu apasat
     */
    public boolean isPressed() {
        return pressed;
    }

    /**
     * Metoda are rolul de a seta starea butonului ca fiind apasat in cazul in care nu era deja apasat
     *
     * @param pressed Noua valoare de apasat a butonului
     */
    public void setPressed(boolean pressed) {
        if(this.pressed==false){
            this.pressed = pressed;
            setEnabled(false);
            if(val.equals("0"))  setText("");
            else setText(val);
            if(val.equals("10"))
            {setBackground(yellowHoney.brighter());}
            else setBackground(getBackground().darker());
        }
    }

    /**
     *
     * @param b
     */
    @Override
    public void setContentAreaFilled(boolean b) {
    }

    /**
     * Metoda are rolul de a seta o noua culoare butonului dupa ce acesta a fost apasat. Culoarea este primita
     * ca si parametru
     *
     * @param pressedBackgroundColor Noua culoare a butonului dupa ce a fost apasat
     */
    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;

    }
}