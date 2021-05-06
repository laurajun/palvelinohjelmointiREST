package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int id;
	private int candidateNr;
	private String party;
	private String firstname;
	private String lastname;
	private String streetAddr;
	private String zipCode;
	private String city;
	private String notes;
	
	public Candidate() {
		
	}
	public Candidate(int candidateNr, String party, String firstname, String lastname, String streetAddr, String zipCode, String city, String notes) {
		this.candidateNr=candidateNr;
		this.setParty(party);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setStreetAddr(streetAddr);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setNotes(notes);
	}
	
	public Candidate(String candidateNr, String party, String firstname, String lastname, String streetAddr, String zipCode, String city, String notes) {
		this.setCandidateNr(candidateNr);
		this.setParty(party);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setStreetAddr(streetAddr);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setNotes(notes);
	}
	
	public Candidate(String id, String candidateNr, String party, String firstname, String lastname, String streetAddr, String zipCode, String city, String notes) {
		this.setId(id);
		this.setCandidateNr(candidateNr);
		this.setParty(party);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setStreetAddr(streetAddr);
		this.setZipCode(zipCode);
		this.setCity(city);
		this.setNotes(notes);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public int getCandidateNr() {
		return candidateNr;
	}
	public void setCandidateNr(int candidateNr) {
		this.candidateNr = candidateNr;
	}
	public void setCandidateNr(String candidateNr) {
		try {
			this.candidateNr = Integer.parseInt(candidateNr);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}