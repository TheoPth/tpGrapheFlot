package theoPth.polytech;

import java.util.ArrayList;

public class Arete {
	private int max, flot;
	private Sommet a, b;
	
	public Arete( Sommet a, Sommet b, int max) {
		super();
		this.max = max;
		this.a = a;
		this.b = b;
		this.flot = 0;
	}

	public int getMax() {
		return max;
	}

	public int getFlot() {
		return flot;
	}

	public Sommet getParent() {
		return a;
	}

	public Sommet getChild() {
		return b;
	}
	
	public String toString() {
		return "Arete " + a.getName() + "-" + b.getName() + " : " + flot + "/" + max;
	}
	
	// Renvoie true si l'arete est déjà au max
	public boolean isMax () {
		return flot >= max;
	}
	
	
	// Retourne true si le sommet est un des sommets de l'arete.
	public boolean possede(Sommet s) {
		return s.equals(b) || s.equals(a);
		
	}
	
	// Retourne true si la fleche part du sommet proposé
	public boolean estParent(Sommet s) throws Exception {
		if (this.possede(s)) {
			return s.equals(a);
		} else {
			throw new Exception("Le point n'est pas l'arete");
		}
	}
	
	// Return l'autre sommet, null si le sommet proposé ne fait pas parti de l'arete 
	public Sommet other(Sommet s) {
		if (s.equals(a)) {
			return b;
		} else if (s.equals(b)) {
			return a;
		}
		
		return null;
	}
	
	// Retourne la diff de flot maximum que peut supporter l'arete
	public int getFlotAugmentable() {
		if (this.isMax()) {
			return this.max;
		} else {
			return this.max - this.flot;
		}
	}

	public void setFlot(int flot) {
		this.flot = flot;
		
	}
	
	
	
}
