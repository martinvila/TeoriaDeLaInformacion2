package modelo;

public class Canal {
	
	private double[][] matriz_p;
	private double[][] prob_posteriori;
	private double[] entrada;
	private double[] salida;
	private double equivocacion;
	private double informacion_mutua;


	public Canal(double[][] matriz_p, double[] entrada) {
		this.matriz_p = matriz_p;
		this.entrada = entrada;
		this.salida = new double[matriz_p[0].length];
		this.prob_posteriori = new double[matriz_p.length][matriz_p[0].length];
		this.equivocacion = 0;
	}

	
	public double entropia(double[] simbolos) {
		double resultado=0;
		
		for(int i=0; i < simbolos.length; i++) {
			resultado += (Math.log(1/simbolos[i])/Math.log(2)) * simbolos[i];
		}
		return resultado;
	}
	
	
	public void probabilidad_salida() {
		
		for(int i=0; i < this.matriz_p[0].length; i++)
			salida[i] = 0;
		for(int i=0; i < this.entrada.length; i++) {
			for(int j=0; j < this.matriz_p[0].length; j++) {
				salida[j] += entrada[i] * matriz_p[i][j];
			}
		}
	}
	
	
	public void probabilidad_posteriori() {
		
		for(int i=0; i < this.entrada.length; i++) {
			for(int j=0; j < this.matriz_p[0].length; j++) {
				prob_posteriori[i][j] = (matriz_p[i][j] * entrada[i]) / salida[j];
			}
		}
	}
	
	
	public double entropia_posteriori(int simbolo) {
		double resultado=0;
		
		for(int i=0; i < prob_posteriori.length; i++) {
			if (prob_posteriori[i][simbolo] != 0)
				resultado += (Math.log(1/prob_posteriori[i][simbolo])/Math.log(2)) * prob_posteriori[i][simbolo];
		}
		return resultado;
	}
	
	
	public void equivocacion() {
		
		for(int i=0; i < this.salida.length; i++) {
			this.equivocacion += salida[i] * this.entropia_posteriori(i);
		}
	}
	
	
	public void informacion_mutua() {
		
		this.informacion_mutua = (double)Math.round((this.entropia(this.entrada) - this.equivocacion) * 10000) / 10000;
	}
	
	
	public double perdida() {
		double resultado=0;
		
		for(int i=0; i < this.entrada.length; i++) {
			for(int j=0; j < this.matriz_p[0].length; j++)
				if(matriz_p[i][j] != 0)
					resultado += matriz_p[i][j] * entrada[i] * (Math.log(1/matriz_p[i][j])/Math.log(2));
		}
		return resultado;
	}


	public double getInformacion_mutua() {
		return informacion_mutua;
	}


	public double[][] getProb_posteriori() {
		return prob_posteriori;
	}


	public double getEquivocacion() {
		return equivocacion;
	}


	public double[][] getMatriz_p() {
		return matriz_p;
	}


	public void setMatriz_p(double[][] matriz_p) {
		this.matriz_p = matriz_p;
	}


	public double[] getEntrada() {
		return entrada;
	}


	public void setEntrada(double[] entrada) {
		this.entrada = entrada;
	}
	
	
	public double[] getSalida() {
		return salida;
	}
}
