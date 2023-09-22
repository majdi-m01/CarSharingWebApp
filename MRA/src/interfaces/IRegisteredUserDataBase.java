package interfaces;

import java.util.ArrayList;

import dbadapter.RegisteredUserDataBase;

public interface IRegisteredUserDataBase {

	public ArrayList<RegisteredUserDataBase> get_Registered(String email, int age, String username);
	
	public void registering(String email, int age, String username);
}
