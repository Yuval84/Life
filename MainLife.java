/**
 * Created by yuval_edelman on 3/25/2015.
 */
import javax.swing.JOptionPane;

     public class MainLife {
        public static void main(String []args){
            String row,col;
            int result=0;
            int rowNum,colNum;
            Life n;
            JOptionPane.showMessageDialog(null,"To start the life game press OK");
            row=JOptionPane.showInputDialog("Enter the number of row you like");
            rowNum=Integer.parseInt(row);
            col=JOptionPane.showInputDialog("Enter the number of column you like");
            colNum=Integer.parseInt(col);
            n=new Life(rowNum,colNum);
        }
    }


