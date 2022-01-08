package Application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.PosicaoXadrez;

public class Programa {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		PartidaXadrez partida = new PartidaXadrez();
		List<PecaXadrez> capturada = new ArrayList();

		while (!partida.getCheckMate()) {
			try {
				UI.limparTela();
				UI.imprimirPartida(partida, capturada);
				System.out.println();
				System.out.print("Inicial: ");
				PosicaoXadrez inicial = UI.lerPosicaoXadre(scan);
				boolean[][] movimentoPossivel = partida.movimentosPossiveis(inicial);
				UI.limparTela();
				UI.imprimirTabuleiro(partida.getPecas(),movimentoPossivel);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez target = UI.lerPosicaoXadre(scan);
				PecaXadrez pecaCapturada = partida.perfomaceMoverPeca(inicial, target);
				if(pecaCapturada != null) {
					capturada.add(pecaCapturada);
				}
		}catch(ExecaoXadrez e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}
			}
		UI.limparTela();
		UI.imprimirPartida(partida, capturada);
	}

}
