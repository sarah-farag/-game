package model.infrastructure;

import java.util.ArrayList;

import model.disasters.Disaster;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public class ResidentialBuilding implements Simulatable, Rescuable {
	private Address location;
	private int structuralIntegrity;// starts with 100
	private int fireDamage;// starts with 0
	private int gasLevel;// starts with 0
	private int foundationDamage; // starts with 0
	private ArrayList<Citizen> occupants;// read only // already getters in class citizens !
	private Disaster disaster;// read only

	// I added the public keyword; I didn't tell Hagar yet.
	public ResidentialBuilding(Address location) {
		// this.location=new Address(location.getX(),location.getY()); //is this
		// right???????????
		this.location = location;// this is correct ; idk why though XD
		this.fireDamage = 0;
		this.gasLevel = 0;
		this.foundationDamage = 0;
		this.structuralIntegrity = 100;
		occupants = new ArrayList<Citizen>();
	}

	public static int getRandomInteger(int min, int max) {
		int x = (int) (Math.random() * ((max - min) + 1)) + min;
		return x;
	}

	public void cycleStep() {

		if (this.getFoundationDamage() > 0)
			this.setStructuralIntegrity(this.getStructuralIntegrity() - (getRandomInteger(5, 10)));

		if (this.getFireDamage() < 30 && this.getFireDamage() > 0)
			this.setStructuralIntegrity(this.getStructuralIntegrity() - 3);
		else if ((this.getFireDamage() >= 30 && this.getFireDamage() < 70))
			this.setStructuralIntegrity(this.getStructuralIntegrity() - 5);
		else if (this.getFireDamage() >= 70)
			this.setStructuralIntegrity(this.getStructuralIntegrity() - 7);

	}

	public Address getLocation() {
		return location;
	}

	public void setLocation(Address location) {
		this.location = location;
	}

	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}

	public void setStructuralIntegrity(int structuralIntegrity) {
		if (structuralIntegrity <= 0) {
			this.structuralIntegrity = 0;
			for (Citizen citizen : occupants) {
				citizen.setHp(0);

			}

		} else

			this.structuralIntegrity = structuralIntegrity;
	}

	public int getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(int fireDamage) {
		if (fireDamage <= 0)
			this.fireDamage = 0;
		else if (fireDamage >= 100)
			this.fireDamage = 100;
		else

			this.fireDamage = fireDamage;
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(int gasLevel) {

		if (gasLevel <= 0)
			this.gasLevel = 0;
		else if (gasLevel >= 100) {
			this.gasLevel = 100;
			for (Citizen citizen : occupants) {
				citizen.setHp(0);

			}
		} else

			this.gasLevel = gasLevel;
	}

	public int getFoundationDamage() {
		return foundationDamage;
	}

	public void setFoundationDamage(int foundationDamage) {
		
		if(foundationDamage>=100) {
			this.setStructuralIntegrity(0);
		}else 
			
		
		
		this.foundationDamage = foundationDamage;
	}

	public ArrayList<Citizen> getOccupants() {
		return occupants;
	}

	public Disaster getDisaster() {
		return disaster;
	}

}
