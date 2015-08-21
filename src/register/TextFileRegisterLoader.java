package register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import javax.print.attribute.standard.RequestingUserName;

public class TextFileRegisterLoader implements RegisterLoader {

	public TextFileRegisterLoader() {
		// TODO Auto-generated constructor stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#save(register.Register) nacita
	 */
	@Override
	public void save(Register register) throws IOException, FileNotFoundException {
		try (PrintWriter pw = new PrintWriter("file.txt")) {
			for (int i = 0; i < register.getCount(); i++) {
				Person p = register.getPerson(i);
				pw.println(p.getName());
				pw.println(p.getPhoneNumber());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#load()
	 */
	@Override
	public Register load() throws IOException, ClassNotFoundException, FileNotFoundException {
		File f = new File("file.txt");
		Register register = new ArrayRegister(20);
		if (!f.exists()) {
			return register;
		}

		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {

			String name;

			while ((name = reader.readLine()) != null) {
				String phoneNumber = reader.readLine();
				if (phoneNumber == null) {
					throw new IOException("Co si sprtal do registra??");
				}
				register.addPerson(new Person(name, phoneNumber));
			}

		} catch (IOException e) {
			e.printStackTrace();

		}
		return register;
	}

}
