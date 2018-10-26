package theoPth.polytech;

import java.util.ArrayList;

public class Graphe {
	
	private ArrayList<Arete> listArete;
	private Sommet source, puit;
	private ArrayList<Sommet> sommets;
	
	public Graphe(ArrayList<Arete> listArete, Sommet source, Sommet puit, ArrayList<Sommet> sommets) {
		super();
		this.listArete = listArete;
		this.source = source;
		this.puit = puit;
		this.sommets = sommets;
	}

	public ArrayList<Arete> getListArete() {
		return listArete;
	}
	
	// Renvoie la liste des sommets enfants du sommet s, vide si puit
	public ArrayList<Sommet> getChildren(Sommet s) {
		ArrayList<Sommet> listChild = new ArrayList<>();
		
		for (Arete a : this.listArete) {
			if (a.getParent().equals(s)) {
				listChild.add(a.getChild());
			}
		}
		
		return listChild;
	}
	
	// Renvoie la liste des sommets parents du sommet s, vide si source
	public ArrayList<Sommet> getParents(Sommet s) {
		ArrayList<Sommet> listParent = new ArrayList<>();
		
		for (Arete a : this.listArete) {
			if (a.getChild().equals(s)) {
				listParent.add(a.getParent());
			}
		}
		
		return listParent;
	}
	
	// Renvoie la liste des sommets en lien avec le sommet s, vide si point en dehors du graphe
	public ArrayList<Sommet> getAdjacents(Sommet s) {
		ArrayList<Sommet> listParent = new ArrayList<>();
		
		for (Arete a : this.listArete) {
			listParent.add(a.other(s));
		}
		
		return listParent;
	}
	
	public boolean estSource(Sommet s) {
		return this.getParents(s).isEmpty();
	}
	
	public boolean estPuit(Sommet s) {
		return this.getChildren(s).isEmpty();
	}
	
	public String toString() {
		String strGraphe = "";
		
		for (Arete a : this.listArete) {
		
			strGraphe += a.toString() +"\n";
		}
		
		return strGraphe;
	}

	// Renvoie true si le sommet adjacent est atteignable selon les regles du chemin améliorant
	public boolean isAttainable(Sommet s, Sommet adj) {
		Arete arr = getArete (s, adj);
		try {
			// Si on peut aller vers l'autre sommet selon les regles du chemin améliorant on renvoie true 
			
			return (!arr.isMax() && arr.estParent(s)) || (arr.isMax() && !arr.estParent(s));
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return false;
	}
	
	// retourne l'arrete entre les deux points, null si les pts ne sont pas reliés
	public Arete getArete(Sommet a, Sommet b) {
		for (Arete arr : this.listArete) {
			if (arr.possede(a) && arr.possede(b)) {
				return arr;
			}
		}
		return null;
	}

	public Sommet getSource() {
		return source;
	}

	public Sommet getPuit() {
		return puit;
	}
	
	
	// Recoit une liste de points, renvoie les aretes entre les points, les points doivent qui se suivent doivent posséder une arete
	public ArrayList<Arete> getListeArreteSommets(ArrayList<Sommet> chemin) {
		ArrayList<Arete> aretes = new ArrayList<>();
		
		for (int i = 0; i < chemin.size() - 1; i++) {
			aretes.add(this.getArete(chemin.get(i), chemin.get(i+1)));
		}
		
		return aretes;
	}

	public void unmarkSommet() {
		for (Sommet s : this.sommets) {
			s.unmark();
		}
		
	}
}
