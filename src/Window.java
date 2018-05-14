import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements ActionListener{

    private JMenuItem fields[] = new JMenuItem[9];
    private boolean xTurn = true;

    public Window(){

        super("XoX");
        JPanel board = new JPanel(new GridLayout(3,3));

        for(int i=0; i<9; i++)
        {
            fields[i] = new JMenuItem();
            if(i % 2 == 0)
                fields[i].setBackground(Color.LIGHT_GRAY);
            fields[i].addActionListener(this);
            fields[i].setActionCommand("" + i);
        }

        JMenu menu = new JMenu("Options");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        xTurn = true;
                        for(int i=0; i<9; i++)
                        {
                            fields[i].setText("");
                            fields[i].setEnabled(true);
                            if( i%2 == 0)
                                fields[i].setBackground(Color.LIGHT_GRAY);
                            else
                                fields[i].setBackground(Color.WHITE);

                        }
                    }
                }
        );
        //menu.add(newGame);
        JMenuBar bar = new JMenuBar();
        bar.add(newGame);

        for(int i=0; i<9; i++)
        board.add(fields[i]);

        add(bar, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++)
        {
            if (Integer.parseInt(e.getActionCommand()) == i)
            {
                fields[i].setText(write());
                fields[i].setEnabled(false);
            }
        }
        if(checkifEnd())
            return;
        if(tied())
        {
            for (int i = 0; i < 9; i++)
                fields[i].setBackground(Color.WHITE);
        }
    }

    public String write(){
        if(xTurn) {
            xTurn = false;
            return "      X";
        } else {
            xTurn = true;
            return "      O";
        }
    }

    public boolean checkifEnd()
    {
        //right
        for(int i=0; i<=6; i+=3) {
            if (fields[i].getText().equals(fields[i + 1].getText()) && fields[i+1].getText().equals(fields[i + 2].getText())
                    && notEmpty(i,i+1,i+2))
            {
                    color(i,i+1,i+2);
                return true;
            }
        }

        //down
        for(int i=0; i<3; i++) {
            if (fields[i].getText().equals(fields[i + 3].getText()) && fields[i + 3].getText().equals(fields[i + 6].getText())
                    && notEmpty(i,i+3,i+6))
            {
                color(i,i+3,i+6);
                return true;
            }
        }

        //diagonal
        if (fields[0].getText().equals(fields[4].getText()) && fields[4].getText().equals(fields[8].getText())
                && notEmpty(0,4,8))
        {
            color(0,4,8);
            return true;
        }

        if (fields[2].getText().equals(fields[4].getText()) && fields[4].getText().equals(fields[6].getText())
                && notEmpty(2,4,6))
        {
            color(2,4,6);
            return true;
        }

        return false;
    }

    public boolean tied()
    {
        for(int i=0; i<9; i++)
        {
            System.out.println(fields[i].getText());
            if(!fields[i].getText().contains("X") && !fields[i].getText().contains("O"))
                return false;
        }
        return true;
    }

    public boolean notEmpty(int pos1, int pos2, int pos3)
    {
        return fields[pos1].getText().contains("X") || fields[pos1].getText().contains("O") &&
                fields[pos2].getText().contains("X") || fields[pos2].getText().contains("O") &&
                fields[pos3].getText().contains("X") || fields[pos3].getText().contains("O");
    }

    public void color(int pos1, int pos2, int pos3)
    {
        fields[pos1].setBackground(Color.BLACK);
        fields[pos2].setBackground(Color.BLACK);
        fields[pos3].setBackground(Color.BLACK);
    }
}
