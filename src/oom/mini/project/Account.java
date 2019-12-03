package oom.mini.project;


import java.util.Random;


public abstract class Account
{       
        Database db=Database.getInstance();
        
	public static final int SAVINGS_ACCOUNT=1;
	public static final int CURRENT_ACCOUNT=2;
        public static final int LOAN_ACCOUNT=3;
	
	public static final int INSUFFICIENT_BALANCE=1;
	public static final int WITHDRAWAL_LIMIT_UNDER=2;
	public static final int WITHDRAWAL_LIMIT_OVER=3;
	
	private String accountNo;
	private String password;
        public String createdAt;
        protected double balance;
        protected Customer user;
        boolean isActivated;

        
        //Some other optional situations....
	protected double minBalance;
	protected double minWithdrawal;
	protected double maxWithdrawal;
        
        //Essential Properties of Account...
        // Acc/No,PIN,User,balance,isActivated
        
	
	Account(Customer c,String d)
	{
		accountNo=generateUniqueAccountNumber(); //Generating an account Number...
		password=generatePIN();// Generating PIN....
		user=c;//User...
                createdAt=d;
	}
	
	abstract void setMinBalance(double a);
	abstract void setWithdrawalLimit(double l, double h);
	abstract int getAccountType();
	
	double getBalance()
	{
		return balance;
	}
	void setBalance(double b)
	{
		balance=b;
	}
	
	String getAccuntNo()
	{
		return accountNo;
	}
	void setAccountNo(String s)
	{
		accountNo=s;
	}
	String getPIN()
	{
		return password;
	}
	void setPIN(String s)
	{
		password=s;
	}
	
	void activateAccount()
	{
		this.isActivated=true;
	}
	
	String generateUniqueAccountNumber()
	{
		Random r = new Random();
		String accountNum=String.valueOf(r.nextInt(10000000)+89999999);
		if(db.isAccountNumberUnique(accountNum))
		{
			return accountNum;
		}
		return generateUniqueAccountNumber();
	}
        
	String generatePIN()
	{
		Random r = new Random();
		return String.valueOf(r.nextInt(1000)+8999);
	}
	
	boolean payBill(double amount)
	{
		if(balance-amount<minBalance)
			return false;
		
		balance-=amount;
		return true;	
	}
	
	void depositMoney(double amount)
	{
		this.balance+=amount;
                db.transactions.add(new Transaction(this.accountNo,this.accountNo,this.accountNo, this.balance,amount,0));
	}
	
	boolean transferMoney(Account ac, double amount)
	{
		if(balance-amount<minBalance)
			return false;
		
		this.balance-=amount;
		ac.balance+=amount;
                
                db.transactions.add(new Transaction(this.accountNo, ac.getAccuntNo(),this.accountNo, this.balance,0, amount));
                db.transactions.add(new Transaction(this.accountNo, ac.getAccuntNo(),ac.getAccuntNo(),ac.balance,amount, 0));
		return true;	
	}
	
	int withdrawMoney(double amount)
	{
		if(amount<minWithdrawal)
			return WITHDRAWAL_LIMIT_UNDER;
		if(amount>maxWithdrawal)
			return WITHDRAWAL_LIMIT_OVER;
		if(balance-amount<minBalance)
			return INSUFFICIENT_BALANCE;
		
		balance-=amount;
                db.transactions.add(new Transaction(this.accountNo,this.accountNo,this.accountNo, this.balance,0,amount));
		return 0;
	}
	
	public String toString()
	{
		return getAccountType()+"\n"+ accountNo + "\n" + password + "\n"+ balance + "\n" + user + "\n" + isActivated + "\n"+createdAt+"\n";
	}


        public Customer getUser() {
            return user;
        }
}
