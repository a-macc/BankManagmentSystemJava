/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oom.mini.project;

/**
 *
 * @author thenullterminator
 */

public class SavingsAccount extends Account
{
    
    private double rateOfInterest=0.05;
    
    public void setInterest(double r)
    {
        this.rateOfInterest=r;
    }
    
    public double getInterest()
    {
        return rateOfInterest;
    }
    
    public void creditInterest(){
        //....Debit Interest........
        this.depositMoney(balance*rateOfInterest);
    }
    
    SavingsAccount(Customer u,String d)
    {
            super(u,d);
            setMinBalance(500);
            setWithdrawalLimit(100,20000);
            setBalance(500);
    }

    
    SavingsAccount(String an, String pin, double balance, Customer u,String d)
    {
            this(u,d);
            super.setAccountNo(an);
            super.setPIN(pin);
            super.setBalance(balance);
    }
    

    @Override
    void setMinBalance(double a)
    {
            minBalance=a;
    }

    @Override
    void setWithdrawalLimit(double l, double h)
    {
            minWithdrawal=l;
            maxWithdrawal=h;
    }

    @Override
    int getAccountType()
    {
            return Account.SAVINGS_ACCOUNT;
    }
        
}