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
		this.party=party;
		this.firstname=firstname;
		this.lastname=lastname;
		this.streetAddr=streetAddr;
		this.zipCode=zipCode;
		this.city=city;
		this.notes=notes;
	}
	
	public Candidate(String candidateNr, String party, String firstname, String lastname, String streetAddr, String zipCode, String city, String notes) {
		this.setCandidateNr(candidateNr);
		this.party=party;
		this.firstname=firstname;
		this.lastname=lastname;
		this.streetAddr=streetAddr;
		this.zipCode=zipCode;
		this.city=city;
		this.notes=notes;
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
}