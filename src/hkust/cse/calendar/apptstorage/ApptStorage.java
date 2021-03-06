package hkust.cse.calendar.apptstorage;//

import hkust.cse.calendar.unit.Appt;
import hkust.cse.calendar.unit.Location;
import hkust.cse.calendar.unit.TimeSpan;
import hkust.cse.calendar.unit.User;
import hkust.cse.calendar.userstorage.UserStorageController;
import hkust.cse.calendar.xmlfactory.ApptXmlFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class ApptStorage {
	public static HashMap<User, HashMap<TimeSpan,Appt>> mUserToAppts = new HashMap<User, HashMap<TimeSpan,Appt>>();
	public HashMap<TimeSpan,Appt> mAppts;		//a hashmap to save every thing to it, write to memory by the memory based storage implementation	
	public User defaultUser;	//a user object, now is single user mode without login
	public int mAssignedApptID;	//a global appointment ID for each appointment record
	public static int mAssignedJointID = 0;	//a global joint appointment ID for each appointment record
	
	public UserStorageController userStorage;
	public static String apptFile = "appt.xml";  // xml file name to appts
	public ApptXmlFactory apptXmlFactory; // the object to control the convertion between appts obj and xml

	public ApptStorage() {	//default constructor
	}
	
	public static HashMap<User, HashMap<TimeSpan,Appt>> getUserToAppts() {
		return ApptStorage.mUserToAppts;
	}

	public abstract void SaveAppt(Appt appt);	//abstract method to save an appointment record

	public abstract Appt[] RetrieveAppts(TimeSpan d);	//abstract method to retrieve an appointment record by a given timespan

	public abstract Appt[] RetrieveAppts(User entity, TimeSpan time);	//overloading abstract method to retrieve an appointment record by a given user object and timespan
	
	public abstract Appt[] RetrieveAppts(User user,int joinApptID);					// overload method to retrieve appointment with the given joint appointment id
	
	public abstract Appt[] RetrieveJointApptsInWaitlist();

	public abstract void UpdateAppt(Appt appt);	//abstract method to update an appointment record

	public abstract void RemoveAppt(Appt appt);	//abstract method to remove an appointment record
	
	public abstract User getDefaultUser();		//abstract method to return the current user object

	public abstract int getAssignedApptID();	// return the assigned appt id
	
	public abstract void setAssignedApptID(int id);	// return the assigned appt id

	public abstract int getAssignedJointID();	// return the assigned Joint id
	
	public abstract void setAssignedJointID(int id);	// return the assigned Joint id

	/* begining of xml management functions*/
	public abstract void loadApptFromXml(User user, HashMap<TimeSpan,Appt> appts);		//abstract method to load appointment from xml reocrd into hash map

	public abstract void saveApptToXml(Appt appt);		//abstract method to save appointment To hash map to xml file

	public abstract void removeApptFromXml(Appt appt);	//abstract method to remove appointment from xml reocrd into hash map
	/* end of xml management functions*/

	public abstract boolean checkOverlap(Appt appt, Appt entry);		
	
	public abstract boolean checkOverLaps(ArrayList<Appt> apptlist);
	
	public abstract boolean checkApptsHaveLocation(String locationName);

	public abstract boolean checkotherApptsHaveLocation(Appt appt, String locationName);
	
	public abstract Appt[] retrieveAllAppts(User user);
	
	public abstract boolean checkotherUsersTimespan(TimeSpan suggestedTimeSpan, User[] users);

	public abstract TimeSpan[] getSuggestedTimeSpan(User[] users, Timestamp stamp);
	
	public abstract boolean checkLocationCapacityEnough(Appt appt);
	
	public abstract void deleteApptWithLocationName(String locationName);
	
	public abstract Appt[] getApptForLocation(Location location);
	
	public abstract Appt[] getApptThatLocationInToBeDelete();
	/*
	 * Add other methods if necessary
	 */

}
