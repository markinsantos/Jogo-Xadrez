package xadrez.pecas;

import Tabuleiro.Board;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Board bord, Cor cor) {
		super(bord, cor);
		
	}
	
	@Override
	public String toString() {
		
		return "R";
		}

	@Override
	public boolean[][] movimentosPossiveis() {
		boolean[][] matriz = new boolean[getBord().getLinhas()][getBord().getColunas()];
		return matriz;
	}

	
}
