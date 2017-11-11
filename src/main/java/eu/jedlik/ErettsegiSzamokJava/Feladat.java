package eu.jedlik.ErettsegiSzamokJava;

public class Feladat implements Comparable<Feladat> { 
	private int pont;
	private Integer válasz;
	private String kérdes;
	private String téma;

	public int getPont() {
		return pont;
	}

	public Integer getVálasz() {
		return this.válasz;
	}

	public String getKérdes() {
		return this.kérdes;
	}

	public String getTéma() {
		return this.téma;
	}

	public Feladat(String kerdes, String[] adat) {
		this.kérdes = kerdes;
		this.válasz = Integer.parseInt(adat[0]);
		this.pont = Integer.parseInt(adat[1]);
		this.téma = adat[2];
	}

	public int compareTo(Feladat f) {
		return this.válasz.compareTo(f.getVálasz());
	}

	public String toString() {
	      return this.pont + " " + this.válasz + " " + this.kérdes;
	}
}