package Tabuleiro;

public class Peca {
	
	protected Posicao posicao;
	private Board bord;
	
	
	public Peca(Board bord) {
			this.bord = bord;
			posicao = null;
	}


	protected Board getBord() {
		return bord;
	}


		

}
