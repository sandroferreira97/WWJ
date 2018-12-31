package wwj;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Functions {

	public static ArrayList<Grupo> groupFinal = new ArrayList<Grupo>();
//	public static ArrayList<ArrayList<Grupo>> groupFinals = new ArrayList<ArrayList<Grupo>>();

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

	public static boolean Sort(ArrayList<Peregrino> per, int[] grupos, int nDias, int retry, int maxRetry) {
		int f = 0;
		int z = 0;
		boolean corre = true;
		ArrayList<ArrayList<Grupo>> groupFinals = new ArrayList<ArrayList<Grupo>>();
		if (corre) {

			for (int k = 0; k < nDias; k++) {
				ArrayList<Grupo> group = new ArrayList<Grupo>();
				ArrayList<Peregrino> temp = new ArrayList<Peregrino>();
				for (int p = 0; p < per.size(); p++) {
					temp.add(per.get(p));
				}

				for (int i = 0; i < grupos.length; i++) {

					group.add(new Grupo(i + 1, maxRetry));
					z = (int) (Math.random() * group.size());
					for (int j = 0; j < grupos[i]; j++) {
						f = (int) (Math.random() * temp.size());
						if (group.get(i).addEl(temp.get(f), retry, grupos[i])) {
							temp.remove(f);
						} else {
							return false;
						}
					}

				}
				groupFinals.add(group);
			}
		}

		System.out.println("tamanho: " + groupFinals.size());
		for (int i = 0; i < groupFinals.size(); i++) {
			for (int j = 0; j < grupos.length; j++) {
				System.out.println("grupo -- " + groupFinals.get(i).get(j).getId());
				System.out.println(groupFinals.get(i).get(j).toString());
			}
		}

		for (int i = 0; i < per.get(0).grupos.size(); i++) {
			System.out.println(per.get(0).grupos.get(i));
		}
		System.out.println(per.get(0).getNome());

		PrintWriter writer = null;
		try {
			writer = new PrintWriter("resultados.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		writer.print("");
		writer.close();

		try (FileWriter fw = new FileWriter("resultados.txt", true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			for (int i = 0; i < groupFinals.size(); i++) {
				int dia = i +1;
				out.println("Dia---" + dia);
				for (int j = 0; j < grupos.length; j++) {
					out.print("grupo-" + groupFinals.get(i).get(j).getId() + ", ");
					out.println(groupFinals.get(i).get(j).toString());
				}
				out.println("");
			}
			

		} catch (IOException e) {
			// exception handling left as an exercise for the reader
		}

//		PrintWriter pw = null;
//		try {
//			pw = new PrintWriter(new File("resultados.csv"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < groupFinals.size(); i++) {
////			sb.append("dia");
//			for (int j = 0; j < grupos.length; j++) {
//				sb.append("Grupo");
//				sb.append(',');
//				sb.append("Peregrino");
//				sb.append('\n');
//
//				sb.append(groupFinals.get(i).get(j).getId());
//				sb.append(',');
//				sb.append(groupFinals.get(i).get(j).toString());
//				sb.append('\n');
//			}
//		}
//
//		pw.write(sb.toString());
//		pw.close();

		return true;
	}

}
