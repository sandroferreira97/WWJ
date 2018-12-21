package wwj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Functions {

	public static ArrayList<Peregrino> Load(File f) {
		Scanner scan;
		ArrayList<Peregrino> p = new ArrayList<Peregrino>();
		try {
			scan = new Scanner(f);

			int i = 1;
			while (scan.hasNextLine()) {
				p.add(new Peregrino(scan.nextLine(), i));
				i++;

			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public static int[] Divison(int nPeregrinos, int nGrupos) {
		int[] grupos = new int[nGrupos];
		if (nPeregrinos % nGrupos == 0) {
			for (int i = 0; i < nGrupos; i++) {
				grupos[i] = nPeregrinos / nGrupos;

			}
		} else {
			for (int i = 0; i < nGrupos; i++) {
				grupos[i] = nPeregrinos / nGrupos;
			}
			grupos[nGrupos - 1] += (nPeregrinos % nGrupos);
			int i = nGrupos - 2;
			while (grupos[nGrupos - 1] - grupos[0] > 1) {
				grupos[i] += 1;
				grupos[nGrupos - 1] -= 1;
				i--;
			}

		}
		return grupos;

	}

	public static boolean Sort(ArrayList<Peregrino> per, int[] grupos, int nDias, int retry,int maxRetry) {
		int f = 0;
		boolean corre = true;

		if (corre) {

			for (int k = 0; k < nDias; k++) {
				ArrayList<Grupo> group = new ArrayList<Grupo>();
				 System.out.println("-------DIA " + k + "--------");
				ArrayList<Peregrino> temp = new ArrayList<Peregrino>();
				for (int p = 0; p < per.size(); p++) {
					temp.add(per.get(p));
				}
				for (int i = 0; i < grupos.length; i++) {

					group.add(new Grupo(i + 1,maxRetry));
					for (int j = 0; j < grupos[i]; j++) {
						f = (int) (Math.random() * temp.size());
//						if(Grupo.groupRep(temp.get(f), group.get(i)))
						if(!group.get(i).getMembros().contains(temp.get(f))) {
							if (group.get(i).addEl(temp.get(f),retry,grupos[i])) {
//								temp.get(f).grupos.add(group.get(i).getId());
								temp.remove(f);
							}else{
								return false;
							}
						}else {
							return false;
						}
					}
					

					 System.out.println(":::::::::GRUPO " + i + "::::::::::");
					 System.out.println(group.get(i).toString());
					 
					 
				}
				System.out.println("");
				System.out.println("");
			}
			
			
		}
		for(int i = 0; i< per.get(0).grupos.size();i++) {
		System.out.println(per.get(0).grupos.get(i));
		}
		System.out.println(per.get(0).getNome());
	
		
		
		return true;
	}
	
	public static boolean isGrupoRep(Peregrino p, Grupo g) {
		if(p.grupos.contains(g.getId())) {
			return true;
		}else {
//			p.grupos.add(g);
			return false;
		}
	}

}
