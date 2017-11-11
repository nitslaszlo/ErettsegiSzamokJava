package eu.jedlik.ErettsegiSzamokJava;

import java.awt.Label;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Szamok {

	public Szamok() {

		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Számok - 2013.05.13");
		f.setVisible(true);

		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.setVisible(true);
		f.add(p);

		List<Feladat> feladatok = new ArrayList<Feladat>();

		p.add(new Label("1. feladat: Adatok beolvasása"));
		try {
			List<String> sorok = Files.readAllLines(Paths.get("felszam.txt"));
			for (int i = 0; i < sorok.size(); i += 2) {
				feladatok.add(new Feladat(sorok.get(i), sorok.get(i + 1).split(" ")));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f, e.getMessage());
		}

		p.add(new Label("2. feladat:"));
		p.add(new Label("Az adatfájlban " + feladatok.size() + " kérdes van."));

		p.add(new Label("3. feladat"));
		int[] matPontDb = new int[4];
		for (Feladat i : feladatok) {
			if (i.getTéma().equals("matematika")) {
				matPontDb[0]++;
				matPontDb[i.getPont()]++;
			}
		}
		String ki = "Az adatfájlban " + matPontDb[0] + " matematika feladat van";
		for (int i = 1; i < matPontDb.length; i++) {
			ki += ", " + i + " pontot er " + matPontDb[i] + " feladat";
		}
		p.add(new Label(ki + "."));

		p.add(new Label("4. feladat"));
		p.add(new Label("A válaszok számértéke a [" + Collections.min(feladatok).getVálasz() + ", "
		 + Collections.max(feladatok).getVálasz() + "] intervallumban van."));

		p.add(new Label("5. feladat"));
		HashSet<String> temakHalmaza = new HashSet<String>();
		for (Feladat i : feladatok) temakHalmaza.add(i.getTéma());
		p.add(new Label("A kérdesek témakörei: "));
		p.add(new Label(String.join(", ", temakHalmaza)));

		p.add(new Label("6. feladat"));
		f.pack();
		Random rnd = new Random();
		String tema = JOptionPane.showInputDialog("Adja meg a téma nevét: ");

		List<Feladat> témaKérdései = new ArrayList<Feladat>();
		for (Feladat i : feladatok) if (i.getTéma().equals(tema)) témaKérdései.add(i);

		if (témaKérdései.size() > 0) {
			Feladat kisorsolt = témaKérdései.get(rnd.nextInt(témaKérdései.size()));
			String sValasz = JOptionPane.showInputDialog(kisorsolt.getKérdes());
			int valasz = Integer.parseInt(sValasz);
			if (kisorsolt.getVálasz() != valasz) {
				p.add(new Label("A válasz 0 pontot ér."));
				p.add(new Label("A helyes válasz: " + kisorsolt.getVálasz()));
			} else {
				p.add(new Label("A válasz " + kisorsolt.getPont() + " pontot ér."));
			}
		}

		p.add(new Label("7. feladat - tesztfel.txt"));
		HashSet<Integer> indexek = new HashSet<Integer>();
		do {
			indexek.add(rnd.nextInt(feladatok.size()));
		} while (indexek.size()<10);

		List<String> sorok = new ArrayList<String>();
		int osszPont = 0;
		for (Integer i : indexek) {
			sorok.add(feladatok.get(i).toString());
			osszPont += feladatok.get(i).getPont();
		}
		sorok.add("");
		sorok.add("A feladatsorra összesen " + osszPont + " pont adható.");
		try {
			Files.write(Paths.get("tesztfel.txt"), sorok);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(f, e.getMessage());
		}
		f.pack();
	}
}