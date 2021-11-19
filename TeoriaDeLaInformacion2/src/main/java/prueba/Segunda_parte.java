package prueba;

import modelo.Canal;

public class Segunda_parte {

	public static void main(String[] args) {
		
		//------------------------------------ DATOS -------------------------------------------
		

		final double [][] p_1 = {{0.3,0.135,0.565}, {0.18,0.4,0.42}, {0.3,0.135,0.565}, {0.135,0.4,0.465}, {0.3,0.18,0.52}};
		final double [][] p_2 = {{0.2,0.135,0.2,0.465}, {0.135,0.3,0.2,0.365}, {0.135,0.2,0.2,0.465}, {0.135,0.3,0.135,0.43}};
		final double [][] p_3 = {{0.2,0.12,0.2,0.48}, {0.12,0.12,0.3,0.46}, {0.2,0.2,0.12,0.48}, {0.12,0.3,0.2,0.38}, {0.2,0.12,0.12,0.56}, {0.2,0.12,0.3,0.38}};
		
		final double [] entrada_1 = {0.45,0.2,0.2,0.135,0.015};
		final double [] entrada_2 = {0.45,0.135,0.2,0.215};
		final double [] entrada_3 = {0.45,0.2,0.1,0.135,0.065,0.05};
		
		
		System.out.println("\n----------------------- CANAL 1 ------------------------\n");
		
		
		Canal canal_1 = new Canal(p_1,entrada_1);
		System.out.println("Entropias:");
		System.out.println("\tH(A) = "+(double)Math.round(canal_1.entropia(canal_1.getEntrada()) * 10000) / 10000);
		canal_1.probabilidad_salida();
		System.out.println("\tH(B) = "+(double)Math.round(canal_1.entropia(canal_1.getSalida()) * 10000) / 10000+"\n");
		System.out.println("Entropias a posteriori:");
		canal_1.probabilidad_posteriori();
		for(int i=0; i < p_1[0].length;i++)
			System.out.println("\tH(A/b"+i+") = "+(double)Math.round(canal_1.entropia_posteriori(i) * 10000) / 10000);
		System.out.println();
		System.out.println("Equivocacion:");
		canal_1.equivocacion();
		System.out.println("\tH(A/B) = "+(double)Math.round(canal_1.getEquivocacion() * 10000) / 10000+"\n");
		System.out.println("Informacion mutua:");
		canal_1.informacion_mutua();
		System.out.println("\tI(A,B) = "+(double)Math.round(canal_1.getInformacion_mutua() * 10000) / 10000+"\n");
		System.out.println("Propiedades del canal:");
		if(canal_1.getInformacion_mutua() >= 0)
			System.out.println("\tI(A,B) >= 0 - No hay perdida de informacion al observar la salida.");
		System.out.println("\tI(A,B) = "+canal_1.getInformacion_mutua()+" = I(B,A) = "+(double)Math.round((canal_1.entropia(canal_1.getSalida()) - canal_1.perdida()) * 10000) / 10000);
		System.out.println("\tH(A,B) = "+(double)Math.round((canal_1.entropia(canal_1.getEntrada())+canal_1.entropia(canal_1.getSalida())-canal_1.getInformacion_mutua())* 10000) / 10000);
		
		
		
		
		System.out.println("\n----------------------- CANAL 2 ------------------------\n");
		
		
		Canal canal_2 = new Canal(p_2,entrada_2);
		System.out.println("Entropias:");
		System.out.println("\tH(A) = "+(double)Math.round(canal_2.entropia(canal_2.getEntrada()) * 10000) / 10000);
		canal_2.probabilidad_salida();
		System.out.println("\tH(B) = "+(double)Math.round(canal_2.entropia(canal_2.getSalida()) * 10000) / 10000+"\n");
		System.out.println("Entropias a posteriori:");
		canal_2.probabilidad_posteriori();
		for(int i=0; i < p_2[0].length;i++)
			System.out.println("\tH(A/b"+i+") = "+(double)Math.round(canal_2.entropia_posteriori(i) * 10000) / 10000);
		System.out.println();
		System.out.println("Equivocacion:");
		canal_2.equivocacion();
		System.out.println("\tH(A/B) = "+(double)Math.round(canal_2.getEquivocacion() * 10000) / 10000+"\n");
		System.out.println("Informacion mutua:");
		canal_2.informacion_mutua();
		System.out.println("\tI(A,B) = "+(double)Math.round(canal_2.getInformacion_mutua() * 10000) / 10000+"\n");
		System.out.println("Propiedades del canal:");
		if(canal_2.getInformacion_mutua() >= 0)
			System.out.println("\tI(A,B) >= 0 - No hay perdida de informacion al observar la salida.");
		System.out.println("\tI(A,B) = "+canal_2.getInformacion_mutua()+" = I(B,A) = "+(double)Math.round((canal_2.entropia(canal_2.getSalida()) - canal_2.perdida()) * 10000) / 10000);
		System.out.println("\tH(A,B) = "+(double)Math.round((canal_2.entropia(canal_2.getEntrada())+canal_2.entropia(canal_2.getSalida())-canal_2.getInformacion_mutua())* 10000) / 10000);
		
		
		System.out.println("\n----------------------- CANAL 3 ------------------------\n");
		
		
		Canal canal_3 = new Canal(p_3,entrada_3);
		System.out.println("Entropias:");
		System.out.println("\tH(A) = "+(double)Math.round(canal_3.entropia(canal_3.getEntrada()) * 10000) / 10000);
		canal_3.probabilidad_salida();
		System.out.println("\tH(B) = "+(double)Math.round(canal_3.entropia(canal_3.getSalida()) * 10000) / 10000+"\n");
		System.out.println("Entropias a posteriori:");
		canal_3.probabilidad_posteriori();
		for(int i=0; i < p_3[0].length;i++)
			System.out.println("\tH(A/b"+i+") = "+(double)Math.round(canal_3.entropia_posteriori(i) * 10000) / 10000);
		System.out.println();
		System.out.println("Equivocacion:");
		canal_3.equivocacion();
		System.out.println("\tH(A/B) = "+(double)Math.round(canal_3.getEquivocacion() * 10000) / 10000+"\n");
		System.out.println("Informacion mutua:");
		canal_3.informacion_mutua();
		System.out.println("\tI(A,B) = "+(double)Math.round(canal_3.getInformacion_mutua() * 10000) / 10000+"\n");
		System.out.println("Propiedades del canal:");
		if(canal_3.getInformacion_mutua() >= 0)
			System.out.println("\tI(A,B) >= 0 - No hay perdida de informacion al observar la salida.");
		System.out.println("\tI(A,B) = "+canal_3.getInformacion_mutua()+" = I(B,A) = "+(double)Math.round((canal_3.entropia(canal_3.getSalida()) - canal_3.perdida()) * 10000) / 10000);
		System.out.println("\tH(A,B) = "+(double)Math.round((canal_3.entropia(canal_3.getEntrada())+canal_3.entropia(canal_3.getSalida())-canal_3.getInformacion_mutua())* 10000) / 10000);
	}

}
