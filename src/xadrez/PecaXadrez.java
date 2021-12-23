package xadrez;

import Tabuleiro.Board;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca {

	private Cor cor;

	public PecaXadrez(Board bord, Cor cor) {
		super(bord);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}

	protected boolean	existePecaAdversaria(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez) getBord().peca(posicao);
		return peca != null && peca.getCor() != cor;
	}
	
}
