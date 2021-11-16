package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

public class Compresion {
	
	private String datos;
	private Fuente fuente;
	
	
	public Compresion() {
		this.fuente = new Fuente();
		this.readFile();
		this.genera_Fuente();
	}


	public void readFile( ) {
		
		try {
			  
			BufferedReader in = new BufferedReader(new FileReader("anexo1.txt"));
			
	        datos = in.readLine();
	        in.close();
	        
	    }
	    catch (IOException e) {
	        System.out.println("excepcion IO" + e);
	    }	
	}
	
	
	public void writeFile(String nombre_archivo, String resultado) {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre_archivo, false));
			
			bw.write(resultado);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("excepcion IO" + e);
		}
	}
	
	
	public void genera_Fuente() {
		int a,b;
		String codigo;
		
		a = 0;
		//b = this.digitos;
		/*
		codigo = datos.substring(a, b);
		this.fuente.getSimbolos().add(new Simbolo(codigo));
		while ( b < datos.length() ) {
			a = b;
			//b += this.digitos;
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(codigo))
				s = it.next();
			if(s.getCodigo().equals(codigo))
				s.setFrecuencia(s.getFrecuencia()+1);
			else
				this.fuente.getSimbolos().add(new Simbolo(codigo));
		}
		*/	
	}
	
	
	public Fuente getFuente() {
		return fuente;
	}


	public void setFuente(Fuente fuente) {
		this.fuente = fuente;
	}


	public void probabilidad_Independiente() {
		
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.setProbabilidad((double)s.getFrecuencia()/(datos.length()/*/this.digitos*/));
		}
	}
	
	
	public void longitud_media() {
		int longitud;
		double suma,probabilidad;
		
		suma = 0;
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			longitud = s.getCodigo().length();
			probabilidad = s.getProbabilidad();
			suma += probabilidad * longitud;
		}
		this.fuente.setLongitud_media(suma);
	}
	
	
	public void huffman(Fuente fuente_Huffman) {
		Simbolo ant_simbolo, ult_simbolo;
		
		Collections.sort(fuente_Huffman.getSimbolos());
		if (fuente_Huffman.getSimbolos().size() == 2 ) {
			fuente_Huffman.getSimbolos().get(0).setCodigo("0");
			fuente_Huffman.getSimbolos().get(1).setCodigo("1");
		}
		else {
			ant_simbolo = fuente_Huffman.getSimbolos().get(fuente_Huffman.getSimbolos().size()-2);
			ult_simbolo = fuente_Huffman.getSimbolos().get(fuente_Huffman.getSimbolos().size()-1);
			ant_simbolo.setProbabilidad(ant_simbolo.getProbabilidad() + ult_simbolo.getProbabilidad());
			fuente_Huffman.getSimbolos().remove(ult_simbolo);
			huffman(fuente_Huffman);
			fuente_Huffman.getSimbolos().add(ult_simbolo);
			ant_simbolo.setProbabilidad(ant_simbolo.getProbabilidad() - ult_simbolo.getProbabilidad());
			Collections.sort(fuente_Huffman.getSimbolos());
			ult_simbolo.setCodigo(ant_simbolo.getCodigo() +"1");
			ant_simbolo.setCodigo(ant_simbolo.getCodigo() +"0");
		}
	}
	
	
	public void rebuild_File(String nombre,Fuente huffman) {
		int a,b;
		String codigo;
		
		a = 0;
		//b = this.digitos;
		
		StringBuilder codificacion = new StringBuilder();
		/*
		while ( b <= datos.length() ) {
			
			codigo = datos.substring(a, b);
			
			Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getCodigo().equals(codigo))
				s = it.next();
			
			it = huffman.getSimbolos().iterator();
			Simbolo sH = (Simbolo) it.next();
			while(it.hasNext() && s.getId() != sH.getId() )
				sH = it.next();
			
			
			codificacion.append(sH.getCodigo());
			
			
			//a += this.digitos;
			//b += this.digitos;
			
		}
		*/
		this.writeFile(nombre,codificacion.toString());
	}
}


