package modelo;
import java.util.ArrayList;
import java.util.Iterator;

public class Fuente implements Cloneable {
	
	private ArrayList<Simbolo> simbolos = new ArrayList<Simbolo>();
	private double entropia;
	private double longitud_media;
	private double rendimiento;
	
	
	public Fuente() {
		this.entropia = 0;
		this.longitud_media = 0;
		this.rendimiento = 0;
	}

	
	public ArrayList<Simbolo> getSimbolos() {
		return simbolos;
	}
	
	
	public void setSimbolos(ArrayList<Simbolo> simbolos) {
		this.simbolos = simbolos;
	}
	
	
	public double getEntropia() {
		return entropia;
	}

	
	public double getLongitud_media() {
		return longitud_media;
	}

	
	public void setLongitud_media(double longitud_media) {
		this.longitud_media = longitud_media;
	}

	
	public double getRendimiento() {
		return rendimiento;
	}

	
	public void setRendimiento(double rendimiento) {
		this.rendimiento = rendimiento;
	}

	
	public void calcula_cantInformacion() {
		
		Iterator<Simbolo> it = this.simbolos.iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.calcula_cantInformacion();
		}
	}
	
	
	public void calcula_entropia() {
		
		Iterator<Simbolo> it = this.simbolos.iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			this.entropia += s.getCantidad_informacion()*s.getProbabilidad();
		}
	}

	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		Fuente fuenteClonada = null;
		fuenteClonada = (Fuente) super.clone();
		fuenteClonada.setSimbolos(new ArrayList<Simbolo>());
		for(int i=0; i < this.simbolos.size(); i++) {
			fuenteClonada.getSimbolos().add((Simbolo)this.simbolos.get(i).clone());
		}
		
		return fuenteClonada;
	}


	@Override
	public String toString() {
		return "Fuente " + simbolos;
	}
	
	
	
}
