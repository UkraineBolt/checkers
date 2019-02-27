/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;


import com.fazecast.jSerialComm.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alex
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        CheckerBoard cb = new CheckerBoard();
        /*Square[][] b = cb.board;
        for(int i=0;i<b[0].length;i++){
            for(int y=0;y<b.length;y++){
                if(b[y][i].piece!=null){
                    System.out.println(y + " "+ i+" : "+b[y][i].id);
                }
            }
        }*/
        Scanner s = new Scanner(System.in);
        while(true){
            pr(cb);
            System.out.println("move:");
            String input = s.nextLine();
            ArrayList<Integer> bd = cb.move(Integer.parseInt(input));
            if(bd==null){System.out.println("placed down");continue;}
            
            System.out.println(bd.toString());
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
