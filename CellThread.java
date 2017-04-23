/**
 * Created by yuval_edelman on 6/6/2015.
 */
public class CellThread implements Runnable{
    private Life life;
    int row,column;

    public CellThread(Life life,int row,int column)
    {
        this.life=life;
        this.row=row;
        this.column=column;
    }



    public void run()
    {
        life.setNewLife(row,column);
    }
}
