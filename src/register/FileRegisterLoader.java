package register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileRegisterLoader implements RegisterLoader {

	public FileRegisterLoader() {
		// TODO Auto-generated constructor stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#save(register.Register)
	 */
	@Override
	public void save(Register register) throws IOException, FileNotFoundException {
		try (FileOutputStream out = new FileOutputStream("register2.bin");
				ObjectOutputStream source = new ObjectOutputStream(out);) {
			source.writeObject(register);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.RegisterLoader#load()
	 */
	@Override
	public Register load() throws IOException, ClassNotFoundException, FileNotFoundException {
		File f = new File("register2.bin");
		if (f.exists()) {
			try (FileInputStream in = new FileInputStream("register2.bin");
					ObjectInputStream inp = new ObjectInputStream(in);) {

				return (Register) inp.readObject();
			}
		}
		return null;
	}

}
