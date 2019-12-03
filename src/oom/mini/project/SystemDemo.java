/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oom.mini.project;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
import java.util.Date;
/**
 *
 * @author thenullterminator
 */
public class SystemDemo extends Thread {
    
    Database db=Database.getInstance();
    
    public void run(){
        
       while(true){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String present=dtf.format(now);
            Date d=new Date(present);

            for(Account ac: db.account){
                   
                System.out.println(ac.createdAt);
                Date d1=new Date(ac.createdAt.split(" ")[1]);
                
                if(ac.getAccountType()==Account.SAVINGS_ACCOUNT && d.getTime()-d1.getTime()>=180000){
                    ((SavingsAccount)ac).creditInterest();
                }
                
                if(ac.getAccountType()==Account.LOAN_ACCOUNT && d.getTime()-d1.getTime()>=180000){
                    ((LoanAccount)ac).debitInterest();
                }
            }   
            
            try{
                Thread.sleep(180000);
            }
            catch(Exception e){
                
            }
       }
    }
    
    public static void main(String args[]){
        
    }
}
