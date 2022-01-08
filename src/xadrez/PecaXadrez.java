package xadrez;

import Tabuleiro.Board;
import Tabuleiro.Peca;
import Tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca {

	private Cor cor;
	private int contadorMovimentos;

	public PecaXadrez(Board bord, Cor cor) {
		super(bord);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	} 
	
	public int getContadorMovimentos() {
		return contadorMovimentos;
	}

	
	public void incrementarMovimentos(){
		contadorMovimentos++;
	}
	public void decrementarMovimentos(){
		contadorMovimentos--;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.fromPosicao(posicao);
	}

	protected boolean	existePecaAdversaria(Posicao posicao) {
		PecaXadrez peca = (PecaXadrez) getBord().peca(posicao);
		return peca != null && peca.getCor() != cor;
	}
	
}
