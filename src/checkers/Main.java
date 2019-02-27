/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;
import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 *
 * @author alex
 */

/*
port description will either be USBER000 or USBER001
These names are distrubed based on the order they were pluged in.

SystemPortName is bound to actual device, use this to figure out what events are fired.

Scanners are used to read the arduinos
PrintWriters are used to send data to the arduinos
    you need to flush the PrintWriter after every use
    Recommended to create a new thread for every output.


*/
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        CheckerBoard cb = new CheckerBoard();
        HashMap<Integer,Integer> ac= new HashMap<>();
        ac.put(1, 1);
        ac.put(2, 3);
        ac.put(3, 12);
        ac.put(4, 5);
        ac.put(5, 5);
        ac.put(6, 6);
        ac.put(7, 7);
        ac.put(8, 8);
        ac.put(9, 9);
        ac.put(10, 10);
        ac.put(11, 11);
        ac.put(12, 4);
        ac.put(13, 13);
        ac.put(14, 14);
        ac.put(15, 15);
        ac.put(16, 16);
        ac.put(17, 16);
        ac.put(18, 15);
        ac.put(19, 14);
        ac.put(20, 13);
        ac.put(21, 4);
        ac.put(22, 11);
        ac.put(23, 10);
        ac.put(24, 9);
        ac.put(25, 8);
        ac.put(26, 7);
        ac.put(27, 6);
        ac.put(28, 5);
        ac.put(29, 12);
        ac.put(30, 3);
        ac.put(31, 2);
        ac.put(32, 1);
        
        
        
        SerialPort ports[] = SerialPort.getCommPorts();
        for(int i=0;i<ports.length;i++){//quick test to see if all ports were read before running
            System.out.println(ports[i].getPortDescription());
            System.out.println(ports[i].getSystemPortName());
            System.out.println(i);
        }
        
        if(ports.length!=2){
            System.out.println(ports.length);System.exit(0);
        }
        
        SerialPort top;
        SerialPort bot;
        if(ports[0].getSystemPortName().equals("COM3")){//determine which port is for top of the board and bot of the board
            top=ports[0];
            bot=ports[1];
        }else{
            top=ports[1];
            bot=ports[0];
        }
        
        if(top.openPort()&&bot.openPort()){//make sure to open the ports for comunication and set timeouts
            top.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
            bot.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        }else{
            System.out.println("didnt connect to both");
            return;
        }
        
        //get the inputs from each arduino
        Scanner topReader = new Scanner(top.getInputStream());
        Scanner botReader = new Scanner(bot.getInputStream());
        //setup the outputs for each arduino
        PrintWriter topOutput = new PrintWriter(top.getOutputStream(),true);
        PrintWriter botOutput = new PrintWriter(bot.getOutputStream(),true);
        System.out.println("made it to loops");
        
        ConcurrentLinkedQueue<String> eventList = new ConcurrentLinkedQueue<>();
        String event;
        
        ConcurrentLinkedQueue<String> outputList1 = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<String> outputList2 = new ConcurrentLinkedQueue<>();
        
        Thread t1 = new Thread(new InputRun(topReader,0,eventList));
        Thread t2 = new Thread(new InputRun(botReader,1,eventList));
        t1.start();
        t2.start();
       
        topOutput.flush();
        botOutput.flush();
        while(true){//loop for whole program
            
            Thread.sleep(1000);
            
            if(!eventList.isEmpty()){
                event = eventList.poll();
            }else{
                //System.out.println("no event");
                continue;
            }
            
            System.out.println(event);
            if(cb.gameOver()){return;}
            String board = event.substring(0,1);
            int tbark = Integer.parseInt(event.substring(1));
            int x = 0;
            for(int i=1;i<ac.size()+1;i++){
                if(board.equals("0") && tbark==ac.get(i) && i < 17){
                    x=i;break;
                }else if(board.equals("1") && tbark==ac.get(i) && i > 16){
                    x=i;break;
                }
            }
            
            ArrayList<Integer> lightsOn = cb.move(x);
            if(lightsOn==null){
                System.out.println("reset");
                outputList1.add("reset");
                outputList2.add("reset");
                Thread t3 = new Thread(new OutputRun(outputList1,topOutput));
                Thread t4 = new Thread(new OutputRun(outputList2,botOutput));
                t3.start();
                t4.start();
                continue;
            }
            
           
            System.out.println(lightsOn.toString());
            for(int i=0;i<lightsOn.size();i++){
                int temp = lightsOn.get(i);
                Thread.sleep(400);
                if(lightsOn.get(i)==-1){
                    outputList1.add("reset");
                    outputList2.add("reset");
                    Thread t3 = new Thread(new OutputRun(outputList1,topOutput));
                    Thread t4 = new Thread(new OutputRun(outputList2,botOutput));
                    t3.start();
                    t4.start();
                }else if(lightsOn.get(i)<=16){//send output to top arduino
                    outputList1.add(Integer.toString(ac.get(temp)));
                    Thread t3 = new Thread(new OutputRun(outputList1,topOutput));
                    t3.start();
                }else{//send output to bot arduino
                    outputList2.add(Integer.toString(ac.get(temp)));
                    Thread t4 = new Thread(new OutputRun(outputList2,botOutput));
                    t4.start();
                }
            }
            
            
            
            //send the data to the arduinos
                       
        }
        
        
    }
    
    private static class InputRun implements Runnable {
        Scanner s;
        int id;
        ConcurrentLinkedQueue<String> event;
        InputRun(Scanner b, int d, ConcurrentLinkedQueue<String> e){
            s=b;id=d;event=e;
        }
        public int getCustomeId(){
            return id;
        }
        @Override
        public void run() {
             while(s.hasNextLine()){
                try {
                    Thread.sleep(800);
                } catch (InterruptedException ex) {}
                String text = s.nextLine();
                event.add(id+text);
            }
        }
    }
    
    private static class OutputRun implements Runnable{
        ConcurrentLinkedQueue<String> list;
        PrintWriter p;
        OutputRun(ConcurrentLinkedQueue<String> li,PrintWriter pw){
            list = li;
            p=pw;
        }
        @Override
        public void run() {
                if(!list.isEmpty()){
                    p.println(list.poll());
                    System.out.println("it sent something");
                }
        }
        
    }
    
    private static void pr(CheckerBoard x){
        for(int y=0;y<x.board[0].length;y++){
            for(int i=0;i<x.board.length;i++){
                //System.out.print(x.board[i][y].toString()+" ");
                if(y % 2 == 0){
                    System.out.print("[] ");
                }
                if(x.board[i][y].piece==null){
                    System.out.print("O ");
                }else if(x.board[i][y].piece.white){
                    System.out.print(x.board[i][y].piece.id+"W ");
                }else{
                    System.out.print(x.board[i][y].piece.id+"B ");
                }
                //System.out.print("[] ");
                
                if(y % 2 != 0){
                    System.out.print("[] ");
                }
            }
            System.out.print("\n");
        }
    }
    
}
