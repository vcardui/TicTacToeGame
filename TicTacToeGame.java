/*
 * 21 de Septiembre de 2024
 * Vanessa Reteguín
 * 375533
 * 
 * Examen primer parcial: Tic-tac-toe Game
 * 
 * Ingeniería en Computación Inteligente (ICI)
 * Semestre III
 * Grupo 2 - E
 * Languajes de Computación III
 * Juan Pedro Cardona Salas
 * 
 * Instrucciones:
 * Realizar un programa con interfáz gráfica que le permita a dos usuarios jugar
 * Gato.
 * [*] Grid 3x3
 * [*] Botones en grid (X / O)
 * [*] Botones cambian con click dando turno a jugadores (X / O)
 * [ ] Registrar quién ganó
 * [*] Botón de reiniciar + aclarar valores
 * [*] Puntaje de jugador 1 y 2
 * [ ] GUI bonita :)
 */
package components;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacToeGame implements ActionListener, ItemListener {

    public static int totalClicks, XScore, OScore;
    public JButton gameButtons[][] = new JButton[3][3]; // Game buttons

    public JLabel XPlayer = new JLabel();
    public JLabel OPlayer = new JLabel();

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        // Menu bar
        menuBar = new JMenuBar();

        // Main menu
        menu = new JMenu("Menu");
        menuBar.add(menu);

        // Menu options
        menuItem = new JMenuItem("Clear game");
        menuItem.addActionListener(new ClearTicTacToe());
        menu.add(menuItem);

        menuItem = new JMenuItem("Restart Score");
        menuItem.addActionListener(new ResetTicTacToe());
        menu.add(menuItem);

        return menuBar;
    }

    private static GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        return gbc;
    }

    public Container createContentPane() {
        // Content pane
        JPanel contentPane = new JPanel(new GridBagLayout());
        contentPane.setOpaque(true);

        // Game buttons setup
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameButtons[i][j] = new JButton();
                gameButtons[i][j].setPreferredSize(new Dimension(100, 100));
                gameButtons[i][j].addActionListener(new TicTacToeClick());
                gameButtons[i][j].setText("");
                gameButtons[i][j].setOpaque(true);
                contentPane.add(gameButtons[i][j], createGbc(i, j));
            }
        }

        // Score labels

        // X Player
        GridBagConstraints gbcXPlayer = new GridBagConstraints();
        gbcXPlayer.gridx = 0;
        gbcXPlayer.gridy = 4;
        gbcXPlayer.ipady = 35;
        gbcXPlayer.gridwidth = 3;

        XScore = 0;
        XPlayer.setText("X Player score: " + XScore);
        XPlayer.setForeground(Color.BLACK);
        contentPane.add(XPlayer, gbcXPlayer);

        // O Player
        GridBagConstraints gbcOPlayer = new GridBagConstraints();
        gbcOPlayer.gridx = 0;
        gbcOPlayer.gridy = 5;
        gbcOPlayer.ipady = 10;
        gbcOPlayer.gridwidth = 3;

        OScore = 0;
        OPlayer.setText("O Player score: " + OScore);
        OPlayer.setForeground(Color.BLACK);
        contentPane.add(OPlayer, gbcOPlayer);

        return contentPane;
    }

    public boolean checkGame(String s) {
        if ((gameButtons[0][0].getText() == s) &&
                (gameButtons[1][0].getText() == s) &&
                (gameButtons[2][0].getText() == s)) {
            gameButtons[0][0].setBackground(Color.GREEN);
            gameButtons[1][0].setBackground(Color.GREEN);
            gameButtons[2][0].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[0][1].getText() == s) &&
                (gameButtons[1][1].getText() == s) &&
                (gameButtons[2][1].getText() == s)) {
            gameButtons[0][1].setBackground(Color.GREEN);
            gameButtons[1][1].setBackground(Color.GREEN);
            gameButtons[2][1].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[0][2].getText() == s) &&
                (gameButtons[1][2].getText() == s) &&
                (gameButtons[2][2].getText() == s)) {
            gameButtons[0][2].setBackground(Color.GREEN);
            gameButtons[1][2].setBackground(Color.GREEN);
            gameButtons[2][2].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[0][0].getText() == s) &&
                (gameButtons[0][1].getText() == s) &&
                (gameButtons[0][2].getText() == s)) {
            gameButtons[0][0].setBackground(Color.GREEN);
            gameButtons[0][1].setBackground(Color.GREEN);
            gameButtons[0][2].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[1][0].getText() == s) &&
                (gameButtons[1][1].getText() == s) &&
                (gameButtons[1][2].getText() == s)) {
            gameButtons[1][0].setBackground(Color.GREEN);
            gameButtons[1][1].setBackground(Color.GREEN);
            gameButtons[1][2].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[2][0].getText() == s) &&
                (gameButtons[2][1].getText() == s) &&
                (gameButtons[2][2].getText() == s)) {
            gameButtons[2][0].setBackground(Color.GREEN);
            gameButtons[2][1].setBackground(Color.GREEN);
            gameButtons[2][2].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[0][0].getText() == s) &&
                (gameButtons[1][1].getText() == s) &&
                (gameButtons[2][2].getText() == s)) {
            gameButtons[0][0].setBackground(Color.GREEN);
            gameButtons[1][1].setBackground(Color.GREEN);
            gameButtons[2][2].setBackground(Color.GREEN);
            return true;
        } else if ((gameButtons[2][0].getText() == s) &&
                (gameButtons[1][1].getText() == s) &&
                (gameButtons[0][2].getText() == s)) {
            gameButtons[2][0].setBackground(Color.GREEN);
            gameButtons[1][1].setBackground(Color.GREEN);
            gameButtons[0][2].setBackground(Color.GREEN);
            return true;
        } else {
            return false;
        }
    }

    public void stopGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameButtons[i][j].setEnabled(false);
            }
        }
    }

    public void clearGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameButtons[i][j].setBackground(Color.WHITE);
                gameButtons[i][j].setText("");
                gameButtons[i][j].setEnabled(true);
            }
        }
        totalClicks = 0;
    }

    public class ClearTicTacToe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            clearGame();
        }
    }

    public class ResetTicTacToe implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            clearGame();
            XScore = 0;
            OScore = 0;

            XPlayer.setText("X Player score: " + XScore);
            XPlayer.setForeground(Color.BLACK);

            OPlayer.setText("O Player score: " + OScore);
            OPlayer.setForeground(Color.BLACK);
        }
    }

    public class TicTacToeClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton clickedButton = (JButton) event.getSource();
            if (clickedButton.getText() == "") {
                Toolkit.getDefaultToolkit().beep();
                if (totalClicks % 2 == 0) {
                    clickedButton.setText("O");
                    clickedButton.setBackground(Color.CYAN);

                } else {
                    clickedButton.setText("X");
                    clickedButton.setBackground(Color.PINK);
                }
                totalClicks++;

                if (checkGame("X") == true) {
                    System.out.println("Winner! X");
                    XScore++;
                    XPlayer.setText(
                            "<html>X Player score: <bold><font color='green'>" + XScore + "</font></bold></html>");
                    OPlayer.setForeground(Color.BLACK);

                    stopGame();
                } else if (checkGame("O") == true) {
                    System.out.println("Winner! O");
                    OScore++;
                    OPlayer.setText(
                            "<html>O Player score: <bold><font color='green'>" + OScore + "</font></bold></html>");
                    XPlayer.setForeground(Color.BLACK);
                    stopGame();
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }

    private static void createAndShowGUI() {
        // Create and set up the window
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane
        TicTacToeGame game = new TicTacToeGame();
        frame.setJMenuBar(game.createMenuBar());
        frame.setContentPane(game.createContentPane());

        // Set window
        frame.setSize(300, 450);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}