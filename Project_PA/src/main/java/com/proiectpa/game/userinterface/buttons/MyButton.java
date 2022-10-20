package com.proiectpa.game.userinterface.buttons;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

/**
 * Clasa are rolul de fi un template te buton care poate fi folosit pe tot parcursul crearii aplicatiei
 */
public class MyButton extends JButton {

    private Color pressedBackgroundColor;

    /**
     * Este instantiat butonul cu un text dat ca si parametru si sunt adaugate evenimente in cazul in care butonul
     * este folosit
     *
     * @param text Textul care va fi in cadrul butonului
     */
    public MyButton(String text) {
        super(text);
        super.setContentAreaFilled(false);
        this.setOpaque(true);


        this.setUI(new BasicButtonUI() {
            @Override
            public void update(Graphics g, JComponent c) {
                if(c.isOpaque()) {
                    if (getModel().isPressed()) {
                        g.setColor(getBackground().darker());
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