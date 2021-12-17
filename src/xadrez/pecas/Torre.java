package xadrez.pecas;

import Tabuleiro.Board;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Board bord, Cor cor) {
		super(bord, cor);
		
	}
	
	@Override
	public String toString() {
		
		return "T";
		}

}
