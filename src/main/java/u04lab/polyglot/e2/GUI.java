package u04lab.polyglot.e2;


import u04lab.polyglot.e2.logic.Logics;
import u04lab.polyglot.e2.logic.LogicsImpl;
import u04lab.polyglot.e2.logic.state.StateEnum;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size, int minesNumber) {
        this.logics = new LogicsImpl(size, minesNumber);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            this.logics.click(pos.getX(), pos.getY());
            StateEnum gameStatus = this.logics.getStatus();
            boolean aMineWasFound = gameStatus.equals(StateEnum.GAME_OVER); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitAndShowMessage("You lost!");
            } else {
                drawBoard();            	
            }
            boolean isThereVictory = gameStatus.equals(StateEnum.WIN); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitAndShowMessage("You won!");
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    if(logics.placeFlag(pos)) {
                        bt.setText("F");
                    } else {
                        bt.setText("");
                    }
                    if(logics.getStatus().equals(StateEnum.WIN)) {
                        quitAndShowMessage("You won!");
                    }
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }

    private void quitAndShowMessage(String message) {
        quitGame();
        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a mine, draw it "*"
            // disable the button
            var coords = entry.getValue();
            if(this.logics.getMines().contains(coords)) {
                entry.getKey().setText("*");
                entry.getKey().setEnabled(false);
            }
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // call the logic here
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            if(this.logics.isClicked(entry.getValue())) {
                entry.getKey().setEnabled(false);
                entry.getKey().setText(String.valueOf(this.logics.numberOfAdjacentMines(entry.getValue())));
            }
    	}
    }
    
}
