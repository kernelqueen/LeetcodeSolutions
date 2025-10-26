class Bank {

    // [20, 30, 40]
    // [1, 2, 3]
    private long[] balances;

    public Bank(long[] balance) {
        this.balances = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if(isValid(account1) && isValid(account2) && balances[account1-1]>=money ) {
            balances[account1-1] -=money;
            balances[account2-1] +=money;
            return true;
        }
        else return false;
    }
    
    public boolean deposit(int account, long money) {
        if(isValid(account)) {
            balances[account-1] += money;
            return true;
        }
        return false;
    }
    
    public boolean withdraw(int account, long money) {
        if(isValid(account) && balances[account-1]>=money) {
            balances[account-1] -= money;
            return true;
        }
        return false;
    }

    private boolean isValid(int account) {
        if(account>=1 && account<=balances.length)
            return true;
        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */