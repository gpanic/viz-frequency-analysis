package fa;

/**
 * @author Gregor Panič
 *
 */
public class Frekvenca implements Comparable<Frekvenca> {
	
	private String crka;
	private int stevilo;
	
	public Frekvenca(String crka, int stevilo) {
		this.crka = crka;
		this.stevilo = stevilo;
	}

	public String getCrka() {
		return crka;
	}

	public void setCrka(String crka) {
		this.crka = crka;
	}

	public int getStevilo() {
		return stevilo;
	}

	public void setStevilo(int stevilo) {
		this.stevilo = stevilo;
	}
	
	@Override
	public String toString() {
		return crka+"="+stevilo;
	}

	@Override
	public int compareTo(Frekvenca o) {
		if(this.stevilo>o.getStevilo()) {
			return -1;
		} else if(this.stevilo==o.getStevilo()) {
			return 0;
		} else {
			return 1;
		}
	}

}
