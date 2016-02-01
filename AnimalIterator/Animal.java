
public class Animal {
	//Member fields
	private String animalName;
	private String animalClass;
	private String animalWeight;
	private String animalHeight;
	
	//Constructor to initialize name,class,weight, and height of Animal
	public Animal(String animalName, String animalClass, String animalWeight, String animalHeight) {
		this.animalName = animalName;
		this.animalClass = animalClass;
		this.animalWeight = animalWeight;
		this.animalHeight = animalHeight;
	}
	
	//Getter method to get AnimalName
	public String getAnimalName() {
		return animalName;
	}
	//Setter method to set AnimalName
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	//Getter method to set AnimalClass
	public String getAnimalClass() {
		return animalClass;
	}
	
	//Setter method to set AnimalClass
	public void setAnimalClass(String animalClass) {
		this.animalClass = animalClass;
	}
	
	//Getter method to set AnimalWeight
	public String getAnimalWeight() {
		return animalWeight;
	}
	//Setter method to set AnimalWeight
	public void setAnimalWeight(String animalWeight) {
		this.animalWeight = animalWeight;
	}
	
	//Getter method to set AnimalHeight
	public String getAnimalHeight() {
		return animalHeight;
	}
	
	//Setter method to set AnimalHeight
	public void setAnimalHeight(String animalHeight) {
		this.animalHeight = animalHeight;
	}
}
