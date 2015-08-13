package register;

public interface RegisterLoader {

	void save(Register register) throws Exception;

	Register load() throws Exception;

}