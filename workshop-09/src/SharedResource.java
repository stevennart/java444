import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** This program tests multiple threads modifying a shared resource. */

class Currency {

	private String currencyType;
	private int currencyAmount;

	public Currency(String c, int amt) {

		this.currencyType = c;
		this.currencyAmount = amt;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public int getCurrencyAmount() {
		return this.currencyAmount;
	}

} // each object defines a currency and its value.

public class SharedResource {
	public static void main(String[] args) {
//		if (args.length == 0) {
//			System.out.println("usage: java SharedResource <number of resources>");
//			System.exit(0);
//		}

		// int numOfDiffCurrency = 0;

//		try {
//			numOfDiffCurrency = Integer.parseInt(args[0]);
//		} catch (NumberFormatException e) {
//			System.out.println("String: <" + args[0] + "> must be an integer number.");
//		}

		List<Currency> currList = new ArrayList<Currency>();

		currList.add(new Currency("Dollar", 1));
		currList.add(new Currency("Euro", 1));
		currList.add(new Currency("Euro", 1));
		currList.add(new Currency("Pound", 1));
		currList.add(new Currency("Pound", 1));
		currList.add(new Currency("Pound", 1));

		System.out.println(currList);

		Collections.shuffle(currList);

		//System.out.println(currList);

//		int dollar = 1;
//		int euro = 2;
//		int pound = 3;
//		
		BankAccount bank = new BankAccount();// The shared container containing a shared int
//
//		
//		
		Me me = new Me(bank, currList);
//		
//		
//		
		Friend friend = new Friend(bank, currList);

		me.start();
		friend.start();
	}
}

/** Definition of a Thread class ProduceResource */
class Me extends Thread {
	private BankAccount myAccess;
	private List<Currency> currList;

//	private int dollar;
//	private int euro;
//	private int pound;

//	private int[] money = new int[3];
//	private String[] currency = { "dollar", "euro", "pound" };

	// private boolean isDollar = false;
	// private boolean isEuro = false;
	// private boolean isPound = false;

	public Me(BankAccount ba, List<Currency> cl) {
		super("Me"); // gives the name to the thread being used.
		myAccess = ba;
		currList = cl;

//		money[0] = dollar;
//		money[1] = euro;
//		money[2] = pound; 
//		this.dollar = dollar;
//		this.euro = euro;
//		this.pound = pound;
	}

	public void run() {
		// for (int count = 1; count <= numberOfResources; count++) {
		// sleep for a random interval
		try {
			Thread.sleep((int) (Math.random() * 3000));
		} catch (InterruptedException e) {
			System.err.println(e.toString());
		}

//			for (int i = 1; i <= 6; i++) {
//				int x = 1;
//				
//				for (int j = 1; j <= 2; i++) {
//					 
//					
//				} 
//				
//				for (int j = 1; j <= 3; i++) {
//					
//				} 

		for (Currency c : currList) {
			myAccess.depositBalance(c.getCurrencyAmount(), c.getCurrencyType());
			//currList.remove(c);

		}

		// }

		// }

	}
}

class Friend extends Thread {
	private BankAccount friendAccess;
	private int numberOfResources;
	private List<Currency> friendCurList;

	public Friend(BankAccount ba, List<Currency> cl) {
		super("Friend");
		friendAccess = ba;
		friendCurList = cl;
		// numberOfResources = m;
	}

	public void run() {
		int val;
		do {
			// sleep for a random interval
			try {
				Thread.sleep((int) (Math.random() * 3000));
			} catch (InterruptedException e) {
				System.err.println(e.toString());
			}
			val = friendAccess.withdrawBalance();
		} while (val != friendCurList.size());       

	}
}

/** Definition of a Container */
class BankAccount {
	private int balance = 0;
	private boolean writeable = true; // condition variable
	private String currencyInBalance = "";

	public synchronized int getBalance() {

		return this.balance;
	}

	public synchronized void depositBalance(int val, String currencyType) {

		if (currencyType.equals("Dollar")) {

			currencyInBalance = currencyType;

		} else if (currencyType.equals("Euro")) {
			currencyInBalance = currencyType;

		} else { // Pound.
			currencyInBalance = currencyType;
		}

		if (!currencyType.equals(currencyInBalance)) {
			writeable = false;
		}

		if (balance == 0) {
			writeable = true;
		}

		while (!writeable) { // not the producer's turn
			try {
				System.out.println("ME: Waiting for withdrawl...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.err.println(Thread.currentThread().getName() + " deposits " + val + " " + currencyType + " from bank account\n");

		balance += val;
		//this.currencyInBalance = currencyType;
		
		System.out.println("Current balance: " + getBalance());
		
		
		writeable = false;

		notify(); // tell a waiting thread to become ready
	}

	public synchronized int withdrawBalance() {

		while (writeable) { // not the consumer's turn
			try {
				System.out.println("FRIEND: Waiting for deposit...");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		writeable = true;
		notify(); // tell a waiting thread to become ready
		System.err.println(Thread.currentThread().getName() + " withdraws 1 " + currencyInBalance + " from bank account");
		balance -= 1;
		return balance;
	}
}