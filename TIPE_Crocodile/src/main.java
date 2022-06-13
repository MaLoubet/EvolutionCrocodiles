
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fTemps(300, 22, 5);
		//fTemperature(100, 10, 5);
		//Rechauffement(300, 42, 5);
	}

	//Effectue une simulation sur t années en prenant en compte le réchauffement planétaire
	//pour une grille de n*n cases de capacité de population k
	public static void fTemperature(int t, int n, int k) {
		int[][] tab = new int[1][25];
		for(int i = 0 ; i <= 24 ; i ++) {
			Fenetre fen = new Fenetre(false, t, n, k, false, 30.0 + 0.2 * (double)(i));

			tab[0][i] = fen.fin;
			System.out.println(i);
		}
		new Graphes(tab, 24);
	}
	
	//Effectue une simulation sur t années sans prendre en compte le réchauffement planétaire
	//pour une grille de n*n cases de capacité de population k
	public static void fTemps(int t, int n, int k) {

		Fenetre region = new Fenetre(true, t, n, k, false, -1);
		Graphes graphe = new Graphes(region.carte.graphe, region.t);
		
	}
	
	//Effectue une simulation sur t années en prenant en compte le réchauffement planétaire
	//pour une grille de n*n cases de capacité de population k
	public static void Rechauffement(int t, int n, int k) {

		Fenetre region = new Fenetre(true, t, n, k, true, -1);
		Graphes graphe = new Graphes(region.carte.graphe, region.t);
	
	}
}
