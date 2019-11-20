public class BeCarefulAboutWait{  
	public static void main(String args[]) {  
		final Customer c = new Customer(); 
		
		new Thread(){  
			public void run() {
				c.withdraw(17000);
			}  
		}.start();  
		
		new Thread(){  
			public void run() {
				c.deposit(17000);
			}  
		}.start();  
	}
}

