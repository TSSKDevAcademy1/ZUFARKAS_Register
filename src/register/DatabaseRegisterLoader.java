package register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseRegisterLoader implements RegisterLoader {

	public static final String URL = "jdbc:mysql://localhost/school";
	public static final String USER = "root";
	public static final String PASSWORD = "Farkasova85.";

	public static final String CREATE = "CREATE TABLE register ( name VARCHAR(32) NOT NULL, phoneNumber VARCHAR(10) NOT NULL)";
	public static final String INSERT = "INSERT INTO register ( name, phoneNumber) VALUES (?, ?)";
	public static final String SELECT = "SELECT id, firstname, surname FROM student";
	public static final String DELETE = "DELETE from register";

	// ;
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#save(register.Register)
	 */
	@Override
	public void save(Register register) throws Exception {
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			try (Statement stmt = con.createStatement()) {
				stmt.executeUpdate(DELETE);
			}

			try (PreparedStatement stmt = con.prepareStatement(INSERT)) {
				for (int i = 0; i < register.getCount(); i++) {
					Person person = register.getPerson(i);
					stmt.setString(1, person.getName());
					stmt.setString(2, person.getPhoneNumber());
					stmt.executeUpdate();
				}
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#load()
	 */
	@Override
	public Register load() throws Exception {
		// File f = new File("register2.bin");
		// if (f.exists()) {
		// try (FileInputStream in = new FileInputStream("register2.bin"); //
		// nacitanie
		// ObjectInputStream inp = new ObjectInputStream(in);) {
		//
		// return (Register) inp.readObject(); // nacita cely register
		// }
		// }
		// return null;
		Register register = new ArrayRegister(20);
		System.out.println("tu1");
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println("tu2");
			try (Statement stmt = con.createStatement()) {
				ResultSet rs = stmt.executeQuery(SELECT);
System.out.println("tu");
				while (rs.next()) {
					//System.out.printf("%-32s %-32s%n", rs.getString(2), rs.getString(3));
					register.addPerson(new Person(rs.getString(2), rs.getString(3)));
				}

				rs.close();
				stmt.close();
				con.close();
			}

		}
		return register;
	}
}
