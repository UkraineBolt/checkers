/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import java.util.ArrayList;


/**
 *
 * @author alex
 */
public class CheckerBoard {
    Square[][] board;
    Checker moving;
    private int white;
    private int black;
    private ArrayList<Group> jumps;

    CheckerBoard() {
        board = new Square[4][8];
        intBoard();
        jumps = new ArrayList<>();
    }
    
    public boolean gameOver(){
        return white==0||black==0;
    }
    
    private void intBoard() {
        int i = 1;
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 4; x++){
                board[x][y] = new Square(i,x,y);
                i++;
                if (y < 3) {
                    board[x][y].piece = new Checker(true,i-1);
                    this.white++;
                } else if (y > 4) {
                    board[x][y].piece = new Checker(false,i-1);
                    this.black++;
                }
            }
        }
    }
    
    private Square getSquare(int id){
        for(int i=0;i<board.length;i++){
            for(int y=0;y<board[i].length;y++){
                Square s = board[i][y];
                if(s.id==id){
                    return board[i][y];
                }
            }
        }
        return null;
    }
     

    public ArrayList<Integer> move(int id) {
        if(0>id&&id>33){
            System.out.println("error");
            return null;
        }
        Square lo = getSquare(id);
        
        if (lo.piece != null && moving == null && jumps.isEmpty()) {//handles takaing a piece up to move
            moving = lo.piece;
            System.out.println("piece picked up");
            return avbMoves(lo);
        } else if (lo.piece == null && moving != null && jumps.isEmpty()) {//handles placing a piece down after a move and should send what pieces should be removed if any
            lo.piece = moving;
            moving = null;
            kinged(lo);
            System.out.println("piece placed");
            return null;//need to return what peices need to be removed
        }else if(moving !=null && !jumps.isEmpty()){//handles pieces being removed
            System.out.println("light pieces to be taken");
            ArrayList<Integer> ls = new ArrayList<>();
            lo.piece = moving;
            moving=null;
            kinged(lo);
            ls.add(-1);
            for(int i=0;i<jumps.size();i++){
                if(jumps.get(i).newSquare.equals(lo)){
                    ls.add(jumps.get(i).openent.id);
                    return ls;
                }
            }
            ls.clear();
            return null;
        }else if(moving==null&&!jumps.isEmpty()){
            System.out.println("clear lights/removed piece");
            lo.piece=null;
            jumps.clear();
            return null;
        }else{
            System.out.println("invalid");
            return null;
        }
    }

    private ArrayList<Integer> avbMoves(Square c) {
        ArrayList<Integer> m = new ArrayList<>();
        m.add(c.id);
        int key;
        
        if(c.y % 2 == 0){
            key=1;
        }else{
            key=-1;
        }
        
        boolean ekd = (c.piece.king||c.piece.white);//its white
        boolean ekl = (c.piece.king||!c.piece.white);//its black
        boolean p1,p2,p3,p4;//work
        
        boolean p5,p6,p7,p8;
        boolean e1,e2,e3,e4;
        
        try{
           p1 = board[c.x][c.y+1].piece == null;
        }catch(Exception e){p1=false;}
        
        try{
            p2 = board[c.x+key][c.y+1].piece == null;
        }catch(Exception e){p2=false;}
        
        try{
            p3 = board[c.x][c.y-1].piece == null;
        }catch(Exception e){p3=false;}
        
        try{
            p4 = board[c.x+key][c.y-1].piece == null;
        }catch(Exception e){p4=false;}
        
        
        try{
           e1 = board[c.x][c.y+1].piece.white!= c.piece.white;
        }catch(Exception e){e1=false;}
        
        try{
            e2 = board[c.x+key][c.y+1].piece.white!= c.piece.white;
        }catch(Exception e){e2=false;}
        
        try{
            e3 = board[c.x][c.y-1].piece.white!= c.piece.white;
        }catch(Exception e){e3=false;}
        
        try{
            e4 = board[c.x+key][c.y-1].piece.white!= c.piece.white;
        }catch(Exception e){e4=false;}
        
        
        try{
           p5 = board[c.x+1][c.y+2].piece == null;
        }catch(Exception e){p5=false;}
        
        try{
            p6 = board[c.x-1][c.y+2].piece == null;
        }catch(Exception e){p6=false;}
        
        try{
            p7 = board[c.x-1][c.y-2].piece == null;
        }catch(Exception e){p7=false;}
        
        try{
            p8 = board[c.x+1][c.y-2].piece == null;
        }catch(Exception e){p8=false;}
        
        
        
        
        
        //base moves
        boolean f1 = ekd && p1;
        boolean f2 = ekd && p2;
        boolean f3 = ekl && p3;
        boolean f4 = ekl && p4;
        
        //jump moves
        boolean f5 = e1 && p5;
        boolean f6 = e2 && p6;
        boolean f7 = e3 && p7;
        boolean f8 = e4 && p8;
        
        if (f1) {
            m.add(board[c.x][c.y+1].id);
        }else if(f5){
            Group g = new Group(board[c.x+1][c.y+2],board[c.x][c.y+1]);
            jumps.add(g);
            m.add(g.newSquare.id);
        }
        if (f2) {
            m.add(board[c.x+key][c.y+1].id);
        }else if(f6){
            Group g = new Group(board[c.x-1][c.y+2],board[c.x+key][c.y+1]);
            jumps.add(g);
            m.add(g.newSquare.id);
        }
        if (f3) {
            m.add(board[c.x][c.y-1].id);
        }else if(f7){
            Group g = new Group(board[c.x-1][c.y-2],board[c.x][c.y-1]);
            jumps.add(g);
            m.add(g.newSquare.id);
        }
        if (f4) {
            m.add(board[c.x+key][c.y-1].id);
        }else if(f8){
            Group g = new Group(board[c.x+1][c.y-2],board[c.x+key][c.y-1]);
            jumps.add(g);
            m.add(g.newSquare.id);
        }
        
        c.piece=null;
        
        return m;
    }

    private void kinged(Square c) {
        if (c.piece.white && c.y == 7) {
            c.piece.king = true;
        } else if (!c.piece.white && c.y == 0) {
            c.piece.king = true;
        }
    }

    public class Checker {//white goes down black goes up

        boolean white;
        boolean king;
        int id;

        Checker(boolean w,int x) {
            king = false;
            this.white = w;
            id=x;
        }

        

        public boolean getColor() {
            return this.white;
        }
    }

    

    public class Square {
        
        int id;
        int x,y;
        Checker piece;

        Square(int i,int x1,int y1) {
            id=i;
            x=x1;
            y=y1;
        }
        public String toString(){
            return Integer.toString(id);
        }
    }
    
    private class Group{
        Square newSquare;
        Square openent;
        Group(Square b, Square o){
            newSquare=b;openent=o;
        }
    }
    
   
}
