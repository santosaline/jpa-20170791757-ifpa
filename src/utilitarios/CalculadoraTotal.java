package utilitarios;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

public class CalculadoraTotal {

	public static Double calculaTotal(int quantidade, String nomeProduto) {
	    //preço do produto x quantidade
		//produtos: celular 800, notebook 5000, tablet 400 
		String produto = "celular";
		int quant = quantidade;
		Double preco = 1.0;
		switch(nomeProduto) {
		case "celular":
			preco = 800.00;
			break;
		case "notebook":
			preco = 5000.00;
			break;
		case "tablet":
			preco = 400.00;
			break;
		
		}
		
		Double n = (preco * quant);
		
		return n;
	}
	
}
