package Application;

import java.util.Scanner;

import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();

		while (true) {

			UI.imprimirTabuleiro(partida.getPecas());
			System.out.println();
			System.out.print("Inicial: ");
			PosicaoXadrez inicial = UI.lerPosicaoXadre(scan);
			System.out.println();
			System.out.print("Destino: ");
			PosicaoXadrez target = UI.lerPosicaoXadre(scan);
			PecaXadrez pecaCapturada = partida.perfomaceMoverPeca(inicial, target);
		}
	}

}
