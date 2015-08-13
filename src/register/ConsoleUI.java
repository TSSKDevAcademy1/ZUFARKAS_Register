package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * User interface of the application.
 */
public class ConsoleUI implements Serializable {
	/** register.Register of persons. */
	private Register register;

	/**
	 * In JDK 6 use Console class instead.
	 * 
	 * @see readLine()
	 */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	// private RegisterLoader loader = new DatabaseRegisterLoader();

	private RegisterLoader loader = new TextFileRegisterLoader();

	/**
	 * Menu options.
	 */
	private enum Option {
		PRINT, ADD, UPDATE, REMOVE, FIND, EXIT
	};

	public ConsoleUI(Register register) {
		this.register = register;
	}

	public void run() throws IOException, ClassNotFoundException {

		try {
			register = loader.load();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("cannot read register");
		}

		while (true) {
			switch (showMenu()) {
			case PRINT:
				printRegister();
				break;
			case ADD:
				addToRegister();
				break;
			case UPDATE:
				updateRegister();
				break;
			case REMOVE:
				removeFromRegister();
				break;
			case FIND:
				findInRegister();
				break;
			case EXIT:
				try {
					loader.save(register);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			// source.close();

		}
	}

	private String readLine() {
		// In JDK 6.0 and above Console class can be used
		// return System.console().readLine();

		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}

	private Option showMenu() {
		System.out.println("Menu.");
		for (Option option : Option.values()) {
			System.out.printf("%d. %s%n", option.ordinal() + 1, option);
		}
		System.out.println("-----------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			selection = Integer.parseInt(readLine());
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}
	/*
	 * // TODO: Implement the method printRegister private void printRegister()
	 * { // throw new UnsupportedOperationException("Method printRegister not
	 * yet // implemented"); for (int i = 0; i < register.getCount(); i++) {
	 * System.out.println(register.getPerson(i)); }
	 * 
	 * }
	 */

	private void printRegister() {
		System.out.println();
		for (int i = 0; i < register.getCount(); i++) {
			if (register.getPerson(i).getName() != null)
				System.out.printf("%d. %s\n", i + 1, register.getPerson(i).toString());
		}
		System.out.println();
	}

	 private void addToRegister() {
	
	 // Person p = new Person(null, null);
	
	 // insert Name
	 System.out.println("Enter Name: ");
	 String name = readLine();
	
	 if (register.findPersonByName(name) != null) {
	 System.out.println("Person with same name exists!");
	 return;
	 }
	 // p.setName(name);
	
	 // insert PhoneNumber
	 System.out.println("Enter Phone Number: ");
	 String phoneNumber = readLine();
	
	 if (register.findPersonByPhoneNumber(phoneNumber) != null) {
	 System.out.println("Person with same phone number exists!");
	 return;
	 }
	 // p.setPhoneNumber(phoneNumber);
	 // register.ArrayRegister(p);
	 register.addPerson(new Person(name, phoneNumber));
	 System.out.println("Add person: " + name + " (" + phoneNumber + ") sucessful !");
	
	 }

//	private void addToRegister() {
//
//		// Person p = new Person(null, null);
//
//		// insert Name
//		System.out.println("Enter Name: ");
//		String name = readLine();
//		System.out.println("Enter Phone Number: ");
//		String phoneNumber = readLine();
//
//		if ((register.findPersonByName(name) != null && register.findPersonByPhoneNumber(phoneNumber) != null)) {
//			System.out.println("Person with same name and same phone number exists!");
//			return;
//
//		} else if (register.findPersonByPhoneNumber(phoneNumber) != null) {
//			System.out.println("Person with some phone number exists.");
//			return;
//		}
//
//		register.addPerson(new Person(name, phoneNumber));
//		System.out.println("Add person: " + name + " (" + phoneNumber + ") sucessful !");
//
//	}

	// TODO: Implement the method updateRegister
	private void updateRegister() {
		// select the person in register for update
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());

		// update the name of the person
		Person person = register.getPerson(index - 1);
		System.out.println("Enter name: ");
		person.setName(readLine());

		// update the Phone number
		System.out.println("Enter Phone Number: ");
		person.setPhoneNumber(readLine());
	}

	// TODO: Implement the method findInRegister
	private void findInRegister() {

		System.out.println("Enter person's name or phone number: ");
		String insert = readLine();
		try {
			Integer.parseInt(insert);
			System.out.println(register.findPersonByPhoneNumber(insert));
		} catch (Exception e) {
			System.out.println(register.findPersonByName(insert));
		}
	}

	private void removeFromRegister() {
		System.out.println("Enter index: ");
		int index = Integer.parseInt(readLine());
		Person person = register.getPerson(index - 1);
		register.removePerson(person);
	}

}
