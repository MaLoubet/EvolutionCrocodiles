import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Carte extends JPanel implements MouseListener{

	//Déclaration des variables
	
	int n;
	int max;
	int taille;
	double Tinit;
	
	
	int pop = 0;
	int an = 0;
	int i = 0;
	int[][] graphe;
	
	
	int mouseX = 0;
	int mouseY = 0;
	boolean gauche = false;
	boolean droit = false;
	int curseur[] = {0,0};
	

	double x = 0.0;
	double itm[] = {30.0, 30.5, 31.0, 31.83, 32.17, 32.33, 33.17, 33.5, 34.0, 34.5};
	double tm[] = {0, 0.0625, 0.25, 0.96, 1.0 , 1.0, 0.33, 0.17, 0.04, 0};
	
	
	boolean premierTour = true;
	boolean premiereActu = true;
	boolean init = false;
	boolean auto;
	boolean changeT;
	

	Zone[][] croco;
	
	
	Color vert = new Color(150,200,50);
	Color bleu = new Color(30,100,220);
	Color rouge1 = new Color(100, 30, 30);
	Color rouge2 = new Color(140, 30, 30);
	Color rouge3 = new Color(180, 30, 30);
	Color rouge4 = new Color(210, 30, 30);
	Color rouge5 = new Color(240, 30, 30);
	
	
	Font texte = new Font("TimesRoman", Font.PLAIN, 20);
	
	
	public Carte(boolean voir, int t, int taille, int tailleZone, boolean varieT, double T){
		
		this.auto = !voir;
		this.max = t;
		this.n = taille;
		this.taille = tailleZone;
		this.changeT = varieT;
		this.Tinit = T;
		this.croco = new Zone[taille][taille];
		this.graphe = new int[3][t + 1];
		this.addMouseListener(this);
	}
	
	
	
	public void paintComponent(Graphics g) {

		//Récupère la position de la souris
		mouseX = MouseInfo.getPointerInfo().getLocation().x-getLocationOnScreen().x;
		mouseY = MouseInfo.getPointerInfo().getLocation().y-getLocationOnScreen().y;
		
		curseur[0] = mouseX*(n-2)/(817-17);
		curseur[1] = mouseY*(n-2)/(850-50);
		
		
		//Réinitialise la fenêtre
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 900, 900);
		g.setFont(texte);
		
		
		//S'effectue une seule fois, en premier
		if(premierTour) {
			init();
			premierTour = false;
		}
		
		
		//permet de déclarer quelles zones sont habitables
		if(!init && !auto) {
			if(gauche) {
				croco[curseur[0]+1][curseur[1]+1].type = true ;
			}
			if(droit) {
				croco[curseur[0]+1][curseur[1]+1].type = false ;
			}
		}
		if(auto) {
			for(int i = 1 ; i <= n-2 ; i++) {
				for(int j = 1 ; j <= n - 2 ; j++) {
					croco[i][j].type = true;
					
				}
			}
			init = true;
			auto = false;

		}
		
		
		//S'effectue une seule fois, après avoir déclaré les zones habitables
		if(init && premiereActu) {
			preparation();
			premiereActu = false;
		}
		
		//Actualise la population d'une année à la suivante
		if(!premiereActu) {
			actu();
		}
		
		
		//Dessine le tableau		
		g.setColor(Color.BLACK);
		g.drawString(Integer.toString(an), 840, 50);
		
		for(int i = 0; i <= n-3; i++) {
			for(int j = 0 ; j <= n-3 ; j++) {
				pop = croco[i+1][j+1].males + croco[i+1][j+1].femelles;
				if(!croco[i+1][j+1].type) {
					g.setColor(vert);
				}else if(!init || pop < 1 ){
					g.setColor(bleu);
				}else if(pop <= 30){
					g.setColor(rouge5);
				}else if(pop <= 70){
					g.setColor(rouge4);
				}else if(pop <= 110){
					g.setColor(rouge3);
				}else if(pop <= 160){
					g.setColor(rouge2);
				}else {
					g.setColor(rouge1);
				}
				g.fillRect((817-17)/(n-2)*i, (850-50)/(n-2)*j+3, (817-17)/(n-2) -1, (850-50)/(n-2) -1);
			}
		}
	
	}

	
	
	//Initialise les tableaux
	public void init() {
		
			for(int i = 0; i <= n-1; i++) {
				for(int j = 0 ; j <= n-1 ; j++) {
					
					if(Tinit < 0) {
						croco[i][j] = new Zone(false, T(i,j), taille, 0, 0);
					}else {
						croco[i][j] = new Zone(false, Tinit, taille, 0, 0);
					}
						
				}
			}
			
			for(int i = 0 ; i <= max ; i++) {
					graphe[0][i] = 0;
					graphe[1][i] = 0;
					graphe[2][i] = 0;
			}
	}
	
	//Compte le nombre de voisins de chaque zone et affecte à chaque zone une population initiale
	public void preparation() {
		for(int i = 1 ; i < n-1 ; i++) {
			for(int j = 1 ; j < n - 1 ; j ++) {
				if(croco[i][j].type) {
					if(croco[i-1][j].type) {
						croco[i][j].voisins ++;
					}
					if(croco[i+1][j].type) {
						croco[i][j].voisins ++;
					}
					if(croco[i][j+1].type) {
						croco[i][j].voisins ++;
					}
					if(croco[i][j-1].type) {
						croco[i][j].voisins ++;
					}
					
				croco[i][j].femelles = 5;
				croco[i][j].males = 5;
				graphe[0][0] += 10;
				graphe[1][0] += 5;
				graphe[2][0] += 5;
				}
			}
				
		}
	}

	
	//Affecte à chaque case la population de l'année suivante
	public void actu() {
		
		an ++;
		System.out.println(T(5,5) + ";" + T((int)(n/2),(int)(n/2)) + ";" + T(n-5,n-5));
		for(int i = 1; i < n-1; i ++) {
			for(int j = 1; j < n -1; j ++) {
				
				//Augmente la température si le changement climatique est pris en compte
				if(changeT) {
					croco[i][j].T = croco[i][j].T + 0.03 ;
				}
				if(croco[i][j].type) {
						croco[i][j].migrants = max(croco[i][j].femelles - croco[i][j].taille, 0) / croco[i][j].voisins;
				}
			}
		}
		
		for(int i = 1; i < n-1; i ++) {
			for(int j = 1; j < n -1; j ++) {
				if(croco[i][j].type) {
					//if(an == 100) {
						//croco[i][j].males = 2 ;
						
						//croco[i][j].femelles = 2 ;
					//}else {
					croco[i][j].pondeuses = min(croco[i][j].taille, croco[i][j].femelles 
							+ croco[i-1][j].migrants + croco[i+1][j].migrants + croco[i][j-1].migrants + croco[i][j+1].migrants) ;

					
					if(croco[i][j].males == 0) {
						croco[i][j].femelles = (int) (0.86 * croco[i][j].femelles);
					}else {
						croco[i][j].femelles = (int) ((1 - m(croco[i][j].T)) * b(croco[i][j].T) * croco[i][j].pondeuses 
								+ 0.86 * croco[i][j].femelles) ;
					}
					croco[i][j].males = (int) (m(croco[i][j].T) * b(croco[i][j].T) * croco[i][j].pondeuses 
							+ 0.86 * croco[i][j].males) ;
					
					
					}
					graphe[0][an] += croco[i][j].males + croco[i][j].femelles;
					graphe[1][an] += croco[i][j].femelles;
					graphe[2][an] += croco[i][j].males;
					
				}
			}
		}
		
	//}
	
	//Renvoie le minimum entre k et j
	private int min(int k, int j) {
		// TODO Auto-generated method stub
		if(k>=j) {
			return j;
		}else {
			return k;
		}
	}


	//Renvoie le maximum entre k et j
	private int max(int j, int k) {
		// TODO Auto-generated method stub
		if(j >= k) {
			i = j;
		}else {
			i = k;
		}
		return i;
	}

	//Renvoie la température en un point
	private double T(int i, int j) {
		
		if(j > n - i) {
			
		return - (double) (j) / n * 5.5 - (double) (i) / n * 5.5  + 37.0 + (double) (n) /n * 5.5 ;	
		
		}else {
			
		return  (double) (j) / n * 11 + (double) (i) / n * 11 + 37.0 - (double) (n) / n * 11;
		
		}
	}
	
	//Renvoie le taux de mâles chez les nouveaux-nés en fonction de la température
	public double m(double T) {
		
		if(T <= 30.0 || T >= 34.5) {
			return 0.0 ;
		}else if(T <= 30.5) {
			i = 1;
		}else if(T <= 31) {
			i = 2;
		}else if(T <= 31.83) {
			i = 3;
		}else if(T <= 32.17) {
			i = 4;
		}else if(T <= 32.33) {
			i = 5;
		}else if(T <= 33.17) {
			i = 6;
		}else if(T <= 33.5) {
			i = 7;
		}else if(T <= 34.0) {
			i = 8;
		}else{
			i = 9;
		}
		
		return tm[i-1] + (tm[i] - tm[i-1]) * (T - itm[i-1]) / (itm[i] - itm[i-1]);
	}
	
	//Renvoie le taux de naissance en fonction de la température
	public double b(double T) {
		if(T <= 34.5){
			x = 0.8;
		}else {
			x = 0.0;
		}
		return x;
	}
	
	
	//Gère les entrées à la souris
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton() == 1) {
				gauche = true;
		}else if (e.getButton() == 2) {
			init = true;
		}else{
			droit = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		gauche = false;
		droit = false;
	}



	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

