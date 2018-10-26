package theoPth.polytech;

import java.util.ArrayList;
import java.util.Iterator;


public class Main {
	public static void main(String[] args) {
		// Permet de créer le graphe sur lequel on va chercher le flot maximum
		Graphe g = initGraphe();
		System.out.println("Graphe de base : ");
		System.out.println(g);
		
		// Recherche un chemin possible de la source jusqu'au puit
		ArrayList<Sommet> chemin = explorer(g, g.getSource());
		
		// Tant qu'un chemin est possible entre la source et le puit, on continue la recherche
		while (!chemin.isEmpty()) {
			
			// Récupération des arêtes à partir des sommets qui appartiennent au meilleur chemin
			ArrayList<Arete> aretesChemin = g.getListeArreteSommets(chemin);
			
			// Recherche le flot à soustraire ou ajouter au arête pour les maximiser
			int miniFlot = miniArete(aretesChemin);
			
			// Changement du flot sur les arêtes
			changementFlot(aretesChemin, miniFlot);
			
			chemin = explorer(g, g.getSource());
		}
		System.out.println("Résultats :");
		System.out.println(g);
	}
	
	// change le flot des arêtes
	private static void changementFlot(ArrayList<Arete> aretesChemin, int miniFlot) {
		for (Arete arete : aretesChemin) {
			// Si l'arete est au max, on la traversé à l'envers, il faut lui enlever du flot
			if (arete.isMax())  {
				arete.setFlot(arete.getFlot() - miniFlot);
			} else {
				arete.setFlot(arete.getFlot() + miniFlot);
			}
		}
		
	}

	// Retourne le flot augmentable d'une liste d'arete, la liste d'arete doit etre non vide
	private static int miniArete(ArrayList<Arete> aretesChemin) {
		int minFlot = aretesChemin.get(0).getFlotAugmentable();
		
		for (int i = 1; i < aretesChemin.size(); i++) {
			
			Arete a = aretesChemin.get(i);
			if (a.getFlotAugmentable() < minFlot ) {
				minFlot = a.getFlotAugmentable();
			}
		}
		return minFlot;
	}

	// Recherche un chemin possible du sommet jusque le puit
	// Return un graphe vide si le puit n'est pas atteint
	// Renvoie la liste des sommets par lequel il faut passer
	
	static ArrayList<Sommet> explorer (Graphe g, Sommet s) {
		ArrayList<Sommet> chemin = explorerBis(g, g.getSource(),  new ArrayList<>(), new ArrayList<>());
		
		// Si le chemin n'est pas vide, on ajoute la source au chemin (on part toujours de la source)
		if (!chemin.isEmpty())
			chemin.add(0, g.getSource());
		// Les sommets sont marqués après l'exploration
		g.unmarkSommet();
		
		return chemin;
	}
	static ArrayList<Sommet> explorerBis (Graphe g, Sommet s, ArrayList<Sommet> cheminRes, ArrayList<Sommet> cheminInter) {
		s.mark();
		
		// quand on atteind le puit on a trouver un chemin possible qui parcourt le graphe
		if (g.estPuit(s)) {
			cheminRes = (ArrayList<Sommet>) cheminInter.clone();
		}
		
		
		for (Sommet child : g.getChildren(s)) {
			if (!child.isMarked() && g.isAttainable(s, child)) {
				cheminInter.add(child);
				ArrayList<Sommet> cheminBis = (ArrayList<Sommet>) cheminInter.clone();
				return explorerBis(g, child, cheminRes, cheminInter);
			}
		}
		
		return cheminRes;
	}

	// Initialisation du graphe
	// Pour créer un graphe : creer les points, creer les aretes qui relie ces points, donner dans une ArrayList les points et les aretes au graphe
	static Graphe initGraphe() {
		// Création des pts
		Sommet s, a, b, c, d, e, t;
		s = new Sommet("S");
		a = new Sommet("A");
		b = new Sommet("B");
		c = new Sommet("C");
		d = new Sommet("D");
		e = new Sommet("E");
		t = new Sommet("T");
		
		ArrayList<Sommet> sommets = new ArrayList<>();
		sommets.add(s);
		sommets.add(a);
		sommets.add(b);
		sommets.add(c);
		sommets.add(d);
		sommets.add(e);
		sommets.add(t);
		
		// Création de la liste des arretes
		ArrayList<Arete> arr = new ArrayList<>();
		arr.add(new Arete(s, a, 4));
		arr.add(new Arete(a, b, 2));
		arr.add(new Arete(a, d, 3));
		arr.add(new Arete(s, e, 9));
		arr.add(new Arete(e, d, 8));
		arr.add(new Arete(d, t, 3));
		arr.add(new Arete(d, c, 1));
		arr.add(new Arete(c, t, 1));
		arr.add(new Arete(b, t, 10));
		arr.add(new Arete(b, c, 4));
		
		Graphe g = new Graphe(arr, s, t, sommets);
		return g;
	}

}
