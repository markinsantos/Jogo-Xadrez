package xadrez;

import Tabuleiro.Board;
import Tabuleiro.Peca;

public class PecaXadrez extends Peca {

	private Cor cor;

	public PecaXadrez(Board bord, Cor cor) {
		super(bord);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

		
	
}
