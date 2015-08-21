package register;

import java.nio.channels.GatheringByteChannel;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) {
		return persons[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) {
		persons[count] = person;
		count++;
	}

	// TODO: Implement the method findPersonByName
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {

		// Person p = new Person(null, null);
		for (int i = 0; i < getCount(); i++) {
			if (name.equals(getPerson(i).getName())) {
				// p.setName(persons[i].getName());
				return getPerson(i);
			}
		}
		return null;
	}

	// TODO: Implement the method findPersonByPhoneNumber
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {

		for (int i = 0; i < getCount(); i++) {
			if (phoneNumber.equals(getPerson(i).getPhoneNumber())) {

				return getPerson(i);
			}
		}
		return null;
	}

	// TODO: Implement the method removePerson
	/*
	 * (non-Javadoc)
	 * 
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {

		for (int i = 0; i < getCount(); i++) {
			Person p = getPerson(i);
			if (p.getName() != null && p.getName().equals(person.getName())) {
				for (int j = i; j < getCount() - 1; j++) {
					getPerson(j).setName(getPerson(j + 1).getName());
					getPerson(j).setPhoneNumber(getPerson(j + 1).getPhoneNumber());
				}
				count--;

			}
			if (i == getCount()) {
				getPerson(i).setName(null);

			}
		}

	}

}
