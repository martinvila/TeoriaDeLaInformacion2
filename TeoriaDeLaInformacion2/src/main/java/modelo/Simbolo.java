package modelo;

public class Simbolo implements Cloneable, Comparable<Object>{
	
	private String codigo;
	private static int sig_id = 0;
	private int id;
	private int frecuencia;
	private double cantidad_informacion;
	private double probabilidad;
	
	
	public Simbolo(String codigo) {
		this.codigo = codigo;
		this.frecuencia = 1;
		this.cantidad_informacion = 0;
		this.probabilidad = 0;
		this.id = ++sig_id;
	}

	
	public String getCodigo() {
		return codigo;
	}

	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public double getProbabilidad() {
		return probabilidad;
	}

	
	public void setProbabilidad(double probabilidad) {
		this.probabilidad = probabilidad;
	}
	
	
	public void calcula_cantInformacion() {
		this.cantidad_informacion = Math.log(1/this.probabilidad)/Math.log(2);
	}

	
	public double getCantidad_informacion() {
		return cantidad_informacion;
	}
	
	
	public int getFrecuencia() {
		return frecuencia;
	}

	
	public void setFrecuencia(int frecuencia) {
		this.frecuencia = frecuencia;
	}
	
	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public static int getSig_id() {
		return sig_id;
	}


	public static void setSig_id(int sig_id) {
		Simbolo.sig_id = sig_id;
	}


	@Override
	public String toString() {
		return "\nSimbolo [id="+ id +", codigo=" + codigo + ", probabilidad=" + (double)Math.round(probabilidad * 100000d) / 100000d
				+ "]";
	}
	
	
	@Override
	public int compareTo(Object o) {
		Simbolo s = (Simbolo) o; 
		
		if (this.getProbabilidad() > s.getProbabilidad())
			return -1;
		else
			if (this.getProbabilidad() == s.getProbabilidad())
				return 0;
			else
				return 1;
	}

	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
		
}
