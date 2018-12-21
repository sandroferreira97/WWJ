package wwj;

import java.util.ArrayList;

public class Peregrino {
	String nome;
	int id;
	ArrayList<Peregrino> colegas;
	ArrayList <Integer> grupos;
	int rep=0;
	
	public Peregrino(String nome, int id) {
		this.nome=nome;
		this.id =id;
		this.colegas=new ArrayList<Peregrino>();
		this.grupos = new ArrayList<Integer>();
	}
	
	public void reset() {
		this.colegas=new ArrayList<Peregrino>();
		this.grupos = new ArrayList<Integer>();
		this.rep=0;
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
	
	public int getRep() {
		return this.rep;
	}
	
	
	
	public boolean isRepCol(Peregrino x) {
		return this.colegas.contains(x);
	}
	
}
