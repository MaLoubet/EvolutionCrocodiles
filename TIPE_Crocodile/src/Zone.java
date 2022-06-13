//Définition du type d'une case

public class Zone {

	
	boolean type;
	
	double T;
	
	int taille;
	int males;
	int femelles;
	int voisins;
	int migrants;
	int pondeuses;
	
	
	
	public Zone(boolean habitable, double temperature, int place, int populationFemelles, int populationMales) {
		
		type = habitable;
		T = temperature;
		taille = place;
		femelles = populationFemelles;
		males = populationMales;
		migrants = 0;
		pondeuses = 0;
		voisins = 0;
	}
}
