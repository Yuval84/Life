import javax.swing.*;
import java.awt.*;
/**
 * Created by yuval_edelman on 6/6/2015.
 */
public class Button extends JButton{
    Color color;
    boolean isOn; //  on/of for the button
    int x,y; //place in the matrix

    public Button(Color c,int x,int y){
        color=c;
        isOn=false;
        this.x=x;
        this.y=y;
        setBackground(color);
        setBackground(c);

    }

    public void setColor(Color a,Color b){
        if(!isOn) { //if the button is "off"
            isOn=true;
            setBackground(b);
            color = b;
        }else{       //if the button is "on"
            isOn=false;
            setBackground(a);
            color = a;
        }
    }

    public void setColor(Color color){
        setBackground(color);
    }

    public boolean isColor(Color c){
        if (c==color){
            return true;
        }
        return false;
    }
}
