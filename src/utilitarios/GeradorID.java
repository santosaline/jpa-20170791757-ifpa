package utilitarios;

import java.util.Date;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
 
public class GeradorID {
	static Integer id;

	public static Integer geraNumeroID() {
		// gera um numero aleatorio para a chave primaria ID
		long timeNow = new Date().getTime();
		long aleatorio =  new Random().nextLong();
		Integer n = (int) (aleatorio * timeNow);
		n = Math.abs(n);  // converte para numero absoluto (positivo)
		// System.out.println("Num-Aleat="+aleatorio + " time="+timeNow + "  -> N="+n);
		return n;
	}
	

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		GeradorID.id = id;
	}
}
