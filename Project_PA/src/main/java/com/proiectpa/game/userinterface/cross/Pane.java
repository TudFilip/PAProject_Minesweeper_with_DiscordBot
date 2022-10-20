package com.proiectpa.game.userinterface.cross;

import javax.swing.*;
import java.awt.*;

/**
 * Clasa este folosita pentru a desena in pagina de setari cele 3 butoane folosite in alegerea nivelului de dificultate
 * preferat de catre jucator
 */
public class Pane extends JPanel {
    int cols;
    int rows;

    /**
     * Constructorul clasei primeste ca parametru numarul de linii si coloane care se doresc a fi desenate pe buton
     *
     * @param x Reprezinta numarul de linii si coloane desenate in buton
     */
    public Pane(int x){
        this.cols=this.rows=x;
    }

    /**
     * Metoda are rolul de seta dimensiunea butoanelor. Dimensiunea este deja data, ea fiind de 100px pe latime
     * si 40px pe inaltime
     *
     * @return Returneaza dimensiunea butonului
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 40);
    }

    /**
     * Metoda are rolul de a desena butoanele in functie de numarul de linii si coloane dat initial in constructor
     *
     * @param g Componenta grafica care trebuie desenata
     */
    @Override
    protected void paintComponent(Graphics g){
        if(rows==1)
        {
            g.drawLine(0,20/rows + rows,100,20/rows+ rows);
            g.drawLine(25/cols+cols,0,25/cols+cols,40);
        }

        if(rows==2)
        {
            g.drawLine(0,20/rows + rows,100,20/rows+ rows);
            g.drawLine(25/cols+cols,0,25/cols+cols,40);
            g.drawLine(0,40/rows + rows+5,100,40/rows+ rows+5);
            g.drawLine(50/cols+cols+5,0,50/cols+cols+5,40);
        }

        if(rows==3)
        {
            g.drawLine(0,20/rows + rows,100,20/rows+ rows);
            g.drawLine(25/cols+cols,0,25/cols+cols,40);
            g.drawLine(0,40/rows + rows+5,100,40/rows+ rows+5);
            g.drawLine(50/cols+cols+5,0,50/cols+cols+5,40);
            g.drawLine(0,70/rows + rows+5,100,70/rows+ rows+5);
            g.drawLine(75/cols+cols+10,0,75/cols+cols+10,40);
        }
    }
}
