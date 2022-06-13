import javax.swing.JFrame;

//Définit la fenêtre qui contient les cases
public class Fenetre extends JFrame{
	
	
	Chrono chrono = new Chrono();
	Carte carte;

	private int t0 = 0;
	private int pause = 100;
	int t;
	int fin;

	boolean prems = true;
	
	public Fenetre(boolean voir, int tmax, int n, int k, boolean varieT, double T) {

		this.setTitle("Migrations des crocodiles");
		this.setSize(900, 850);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.t = tmax;
		
		this.carte = new Carte(voir, t, n, k, varieT, T);
		carte.repaint();
		this.setContentPane(carte);
		initialisation();
		this.fin = actualisation(t);
	}

	public void initialisation() {
		while(!carte.init) {
			carte.repaint();
			if(prems) {
				this.setSize(901,851);
				prems = false;
			}	
		}
	}
	//Actualise la fenêtre à intervalle de temps régulier
	//Renvoie les populations finales après t années
	public int actualisation(int t) {
		for(int i = 1 ; i < t ; i ++){
			t0 = chrono.getMILLI();
			carte.repaint();
			if(chrono.getMILLI() - t0 < pause){
				try {
					Thread.sleep(pause-(chrono.getMILLI()-t0));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return carte.graphe[0][t-1];
	}
}
