/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oom.mini.project;

/**
 *
 * @author encrypted_
 */
public class LoanAccount extends Account{
    
    private double principalAmount;

    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }
    
    private double interest=0.05;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
    
    private int loanDuration;

    public int getLoanDuration() {
        return loanDuration;
    }

    public void debitInterest(){
        //....Debit Interest........
        if(balance>balance*interest)
        this.depositMoney(balance*interest);
        else balance=0;
    }
    public void setLoanDuration(int loanDuration) {
        this.loanDuration = loanDuration;
    }
    
    LoanAccount(Customer u,String d)
    {
            super(u,d);
            setMinBalance(500);
            setWithdrawalLimit(100,20000);
            setBalance(500);
    }

    
    LoanAccount(String an, String pin, double balance, Customer u,String d)
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
            return Account.LOAN_ACCOUNT;
    }
    
}
