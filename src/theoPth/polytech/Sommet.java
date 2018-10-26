package theoPth.polytech;

public class Sommet {
	private String name;
	private boolean marked;
	
	public Sommet(String name) {
		super();
		this.name = name;
		this.marked = false;
	}

	public String getName() {
		return this.name;
	}

	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean estMarque) {
		this.marked = estMarque;
	}
	
	public void mark() {
		this.marked = true;
	}

	@Override
	public String toString() {
		return "Sommet [name=" + name + ", estMarque=" + marked + "]";
	}

	public void unmark() {
		this.marked = false;
		
	}
	
}
