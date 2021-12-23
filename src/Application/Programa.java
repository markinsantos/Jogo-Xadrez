package Application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();

		while (true) {
			try {
				UI.limparTela();
				UI.imprimirTabuleiro(partida.getPecas());
				System.out.println();
				System.out.print("Inicial: ");
				PosicaoXadrez inicial = UI.lerPosicaoXadre(scan);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez target = UI.lerPosicaoXadre(scan);
				PecaXadrez pecaCapturada = partida.perfomaceMoverPeca(inicial, target);
		}catch(ExecaoXadrez e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}
			}
	}

}
