package wwj;

import java.util.ArrayList;

public class Peregrino {
	String nome;
	int id;
	ArrayList<Peregrino> colegas;
	ArrayList<Integer> grupos;
	int rep = 0;
	int gRep = 0;

	public Peregrino(String nome, int id) {
		this.nome = nome;
		this.id = id;
		this.colegas = new ArrayList<Peregrino>();
		this.grupos = new ArrayList<Integer>();
	}

	public void reset() {
		this.colegas = new ArrayList<Peregrino>();
		this.grupos = new ArrayList<Integer>();
		this.rep = 0;
		this.gRep = 0;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void addCol(Peregrino x) {
		this.colegas.add(x);
	}

	public void rep() {
		this.rep++;
	}
	
	public void gRep() {
		this.gRep++;
	}

	public int getRep() {
		return this.rep;
	}
	
	public int getGRep() {
		return this.gRep;
	}
	
	public boolean maxRep(int max) {
		this.gRep();
		if (this.gRep > max) {
			return false;
		}
		
		return true;
	}

	public boolean isRepCol(Peregrino x) {
		return this.colegas.contains(x);
	}

}
