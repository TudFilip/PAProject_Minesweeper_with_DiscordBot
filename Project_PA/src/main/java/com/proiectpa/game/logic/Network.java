package com.proiectpa.game.logic;

import com.proiectpa.game.userinterface.buttons.ButonJoc;

import java.util.*;

/**
 * Clasa este folosita pentru a gestiona toata logica din spatele jocului
 */
public class Network {

    private ArrayList<ButonJoc> game;
    private static int[][] gameMatrix;
    private boolean winner;
    private int[][] conect;
    private int level;

    /**
     * Constructorul initializeaza jocul curent cu un nivel dat ca si parametru si creeaza in spate matricea jocului
     *
     * @param level Nivelul pe care se va desfasura jocul curent
     */
    public Network(int level) {
        this.level=level;
        this.game = new ArrayList<>();
        gameMatrix = new int[50][50];

    }

    /**
     * Metoda are rolul de a adauga in lista de butoane a jocului un nou buton cu o anumita valoare
     *
     * @param value Valoarea butonului nou adaugat
     * @param buton Butonul nou adaugat in joc
     */
    public void addToGame(int value, ButonJoc buton) {
        this.game.add(buton);
    }

    /**
     * Metoda este folosita pentru a initializa un nou joc
     */
    public void initializareMap() {
        int BombNumber = level*level*22;

        for (int i = 0; i < (level*10+3); i++)
            for (int j = 0; j < (level*10+3); j++)
                gameMatrix[i][j] = 0;

        while (BombNumber > 0) {
            Random rand = new Random();
            int max=level*10+2;
            int i = rand.nextInt(max);
            int j = rand.nextInt(max);
            if (gameMatrix[i][j] == 0) {
                BombNumber--;
                gameMatrix[i][j] = 10;
            }
        }

        for (int i = 0; i < (level*10+3); i++) {
            for (int j = 0; j < (level*10+3); j++) {

                if(gameMatrix[i][j]!=10){
                    if (i == 0 && j > 0&&j<(level*10+2)) {
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j + 1] == 10) gameMatrix[i][j]++;
                    }
                    else if (j == 0 && i > 0&&i<(level*10+2)) {
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j + 1] == 10) gameMatrix[i][j]++;

                    }
                    else if(i==(level*10+2)&&j<(level*10+2)&&j>0)
                    {
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;

                    }
                    else if(j==(level*10+2)&&i<(level*10+2)&&i>0){
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j - 1] == 10) gameMatrix[i][j]++;
                    }
                    else if(i==0&&j==0){
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j + 1] == 10) gameMatrix[i][j]++;

                    }
                    else if(i==0&&j==(level*10+2)){
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                    }
                    else if(j==0&&i==(level*10+2)){
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;
                    }
                    else if(j==(level*10+2)&& i==(level*10+2)){
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j - 1] == 10) gameMatrix[i][j]++;

                    }
                    else {
                        if (gameMatrix[i - 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i - 1][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i][j + 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j - 1] == 10) gameMatrix[i][j]++;
                        if (gameMatrix[i + 1][j + 1] == 10) gameMatrix[i][j]++;
                    }
                }
            }
        }
    }

    /**
     * Metoda este folosita pentru a cauta in matrice toti vecinii butonului apasat care nu au
     * bombe in jurul lor, formand o insula
     *
     * @param x Pozitia pe axa X a butonului de unde se incepe cautarea
     * @param y Pozitia pe axa Y a butonului de unde se incepe cautarea
     */
    public void searchMiniMap(int x, int y)
    {
        conect=new int[50][50];
        for (int i=0;i<(level*10+3);i++)
            for (int j=0;j<(level*10+3);j++)
                conect[i][j]=0;
        conect[x][y]=1;

        int ok=0;
        do{
            ok=0;
            for (int i=1;i<(level*10+2);i++)
                for (int j=1;j<(level*10+2);j++) {
                    if (conect[i][j] == 0) {
                        if (conect[i + 1][j + 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i + 1][j] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i + 1][j - 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i][j + 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i][j - 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i - 1][j + 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i - 1][j] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                        if (conect[i - 1][j - 1] == 1 && gameMatrix[i][j] == 0) {
                            conect[i][j] = 1;
                            ok = 1;
                        }
                    }
                }
        }while (ok!=0);
    }

    /**
     * Metoda este folosita pentru a returna matricea care se afla in spatele jocului
     *
     * @return Returneaza matricea din spatele jocului
     */
    public int[][] getGameMatrix() {
        return gameMatrix;
    }

    /**
     * Metoda are rolul de a afisa pe consola elementele matricei
     */
    public void printMatrix() {
        for (int i = 0; i < (level*10+3); i++) {
            for (int j = 0; j < (level*10+3); j++)
                System.out.print(gameMatrix[i][j] + " ");
            System.out.println();
        }

    }

    /**
     * Metoda este folosita pentru a returna variabila de tipul boolean 'winner'
     *
     * @return Este returnata variabila 'winner'
     */
    public boolean getWinner()
    {
        return winner;
    }

    /**
     * Metoda este folosita pentru a returna matricea de conexiune a jocului
     *
     * @return Este returnata matricea de conexiune
     */
    public int[][] getConect() {
        return conect;
    }

    /**
     * Metoda are rolul de a returna lista cu toate butoanele tablei de joc
     *
     * @return Este returnata lista cu butoane a jocului
     */
    public ArrayList<ButonJoc> getGame() {
        return game;
    }
}