package readwritter;

import java.util.Random;

 
             
public class Controller {
    public static int b=100;
    boolean writing ;
    int waitingWriters,readers;
    private boolean readersTurn =false;

    public  synchronized void statrtWrite() {

        while(writing || readers>0){
            waitingWriters++;
            try{
               wait(); }
            catch(InterruptedException ex){
                waitingWriters--;
            } 
        }
        
        waitingWriters--;
        writing=true;
    }
    
    public static synchronized void write(String s){
         
          b=b-1;
          
    }
    public synchronized void stopWriting(){
        writing = false;
        readersTurn =true;
        notifyAll();
    }
    
    
    public synchronized void startReading() {

        while(writing || waitingWriters > 0 && !readersTurn ){
            try{
                wait();
            }catch(InterruptedException ex){
            }
           
        } readers++;
    }
//    public void read() {
//        //MainFrame.Balance.setText(b+"");
//
//    }
    
    public synchronized void stopReading(){
        --readers;
        readersTurn = false;
        if(readers==0){
            notifyAll();
        }
    }
    public int getTickets(){
        return b;
    }
}
