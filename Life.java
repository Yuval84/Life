/**
 * Created by yuval_edelman on 3/25/2015.
 */
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.Random;
    import javax.swing.*;

public class Life extends JFrame {
    static Random rand = new Random();
    private int current[][] = new int[0][0];
    private int next[][] = new int[0][0];
    private static Button matrix[][];//the game bord
    private Container container;
    private JPanel panel;
    private GridLayout gridLayout;
    private int threadCount, maxThread;
    private boolean fin;
    private JButton nextLife;
    static int loop=0;

   public Life(int row, int col) {//constructor
        super("Life");
        current = new int[col][row];//current life
        matrix=new Button[col][row];
        panel=new JPanel();
        gridLayout=new GridLayout(row,col);
        container=getContentPane();
        setLife(current);
        next = new int[col][row];//next life...
        arryCopy(current, next);//copy cur to next
        maxThread = row * col;
        threadCount = 0;
        fin = false;
        panel.setLayout(gridLayout);
        panel.setSize(900,900);
        add(panel);
        nextLife=new JButton("next cycle");
        add(nextLife,BorderLayout.NORTH);
        nextLife.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetThreads();
                thread();
                arryCopy(next, current);
                setSecondMatrix();
                repaint();
            }
        });
        pack();
        setVisible(true);
        setExtendedState (java.awt.Frame.MAXIMIZED_BOTH);

    }

    public void setLife(int[][] matrix) {//set new life
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = randInt();
            }
        }
        setMatrix();
    }

    public int randInt() {//randomize the life cell
        int randomNum = rand.nextInt((1 - 0) + 1) + 0;
        return randomNum;
    }

    public void arryCopy(int a[][], int b[][]) {// copy matrix a to b
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[i][j] = a[i][j];
            }
        }
    }

    public void print() {//print the life matrix
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[i].length; j++) {
                if (current[i][j] == 1) {
                    str1 = str1.append('+');
                } else {
                    str1 = str1.append('-');
                }
            }
            str1 = str1.append('\n');
        }
        JOptionPane.showMessageDialog(null, new JLabel("<html><pre>" + str1.toString()));
    }


    public synchronized int countLife(int i, int j) {//count the life
        int count = 0;
        if (i != 0 && j != 0 && i != current.length - 1 && j != current[0].length - 1) {//all is good
            if (current[i - 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i == 0 && j == 0) {//left top corner
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i == current.length - 1 && j == 0) {//left bottom corner
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i == 0 && j == current[0].length - 1) {//right top corner
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            return count;
        }

        if (i == current.length - 1 && j == current[0].length - 1) {//right bottom corner
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i != 0 && j == 0) {//left side
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i != 0 && j == current[0].length - 1) {//right side
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j - 1] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i == 0 && j != current[0].length - 1) {//top row!
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j] == 1) {
                count = count + 1;
            }
            if (current[i + 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            return count;
        }
        if (i == current.length - 1 && j != 0) {//bottom
            if (current[i - 1][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i][j - 1] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j] == 1) {
                count = count + 1;
            }
            if (current[i - 1][j + 1] == 1) {
                count = count + 1;
            }
            if (current[i][j + 1] == 1) {
                count = count + 1;
            }
            return count;
        }

        return count;
    }

    public synchronized void setNewLife(int i, int j) {
        int count = 0;
        while (threadCount != maxThread - 1) {
            threadCount++;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!fin) {//last cell
            fin = true;
            notifyAll();
        }
        count = countLife(i, j);
        if (count == 3 && current[i][j] == 0) {//new life
            next[i][j] = 1;
        }
        if (count < 2 && current[i][j] == 1) {//death
            next[i][j] = 0;
        }
        if (count > 3 && current[i][j] == 1) {////death
            next[i][j] = 0;
        }
    }

    public void resetThreads() {
        threadCount = 0;
        fin = false;
    }

    private void setMatrix(){
        for (int i=0;i<current.length;i++){
            for (int j=0;j<current[0].length;j++){
                if (current[i][j]==1){
                    matrix[i][j]=new Button(Color.BLUE,i,j);
                    panel.add(matrix[i][j]);
                }
                if (current[i][j]==0){
                    matrix[i][j]=new Button(Color.BLACK,i,j);
                    panel.add(matrix[i][j]);
                }
            }
        }
    }

    private void setSecondMatrix(){
        for (int i=0;i<current.length;i++){
            for (int j=0;j<current[0].length;j++){
                if (current[i][j]==1){
                    matrix[i][j].setColor(Color.BLUE);
                }
                if (current[i][j]==0){
                    matrix[i][j].setColor(Color.BLACK);
                }
            }
        }
    }

    private void thread() {
        for (int i = 0; i < current.length; i++) {
            for (int j = 0; j < current[0].length; j++) {
                (new Thread(new CellThread(this, i, j))).start();
            }
        }
    }

}//end


