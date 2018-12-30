package wwj;

import java.util.ArrayList;

public class Grupo {

	private int id;
	private ArrayList<Peregrino> membros;
	private int maxRetry;

	public Grupo(int id, int maxRetry) {
		this.id = id;
		this.membros = new ArrayList<Peregrino>();
		this.maxRetry = maxRetry;
	}

	public boolean addEl(Peregrino x, int h, int gr) {
		if (h == 0) {
			for (int i = 0; i < membros.size(); i++) {
				membros.get(i).addCol(x);
			}
			membros.add(x);
			x.grupos.add(this.getId());
		} else {
			if (!membros.contains(x)) {
				int j = 0;
				for (int i = 0; i < membros.size(); i++) {
					if (membros.get(i).isRepCol(x)) {
						if (membros.get(i).getRep() > maxRetry) {
							System.out.println("222222222222222222222222222222222");
							return false;
						}
						j++;
					}
				}
				if (j > gr / 2) {
					System.out.println("1111111111111111111111111111111111111111");
					return false;
				}
				for (int i = 0; i < membros.size(); i++) {
					if (membros.get(i).isRepCol(x)) {
						membros.get(i).rep();
					}
					membros.get(i).addCol(x);
				}
				membros.add(x);
//				x.grupos.add(this.getId());
				if(groupRep(x,this)) {
				return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String resul = "";
		for (int i = 0; i < membros.size(); i++) {
			resul += membros.get(i).getNome();
			resul += "  ";
		}
		return resul;
	}

	public int getId() {
		return this.id;
	}

	public ArrayList<Peregrino> getMembros() {
		return this.membros;
	}

	public static boolean groupRep(Peregrino p, Grupo g) {
		if (p.grupos.contains(g.getId())) {
			if (p.maxRep(4)) {
				return true;
			}
		} else {
			p.gRep();
			p.grupos.add(g.getId());
			return true;
		}
		return false;
	}
}