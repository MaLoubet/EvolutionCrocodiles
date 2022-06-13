import javax.swing.JFrame;

//Fenêtre qui affiche les graphes
public class Graphes extends JFrame{


	public Graphes(int[][] t1, int max) {
		
		this .setTitle("Evolution de la population au cours du temps");
		this.setSize(1100, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(new Trace(t1, max));
		
		
	}
	
}
