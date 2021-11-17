package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Compresion {
	
	public String datos;
	private Fuente fuente;
	
	
	public Compresion(String nombre_archivo) {
		this.fuente = new Fuente();
		this.readFile(nombre_archivo);
		this.genera_Fuente();
	}
	
	


	public void readFile(String nombre_archivo) {
		
		try {
			String linea;
			
			BufferedReader in = new BufferedReader(new FileReader(nombre_archivo));
			StringBuilder text = new StringBuilder();
	        
			while ((linea = in.readLine()) != null)
	        	text.append(linea + "\n");
	        	
	        datos = text.toString();
	        
	        in.close();
	        
	    }
	    catch (IOException e) {
	        System.out.println("excepcion IO" + e);
	    }	
	}
	
	
	public void writeFile(String nombre_archivo, String codificacion) {
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(nombre_archivo, false));
			
			bw.write(codificacion);
			bw.close();
		}
		catch (IOException e) {
			System.out.println("excepcion IO" + e);
		}
	}
	
	
	public void genera_Fuente() {
		int i;
		char c;
		String simbolo;
		
		this.fuente.getSimbolos().add(new Simbolo(" "));
        for(i = 0; i < datos.length(); i++) {
        	c = datos.charAt(i);
        	
        	if(c < 65 || c =='¿' || c =='¡') {
	        	Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
				Simbolo s = (Simbolo) it.next();
				while(it.hasNext() && !s.getId().equals(String.valueOf(c)))
					s = it.next();
				if(s.getId().equals(String.valueOf(c)))
					s.setFrecuencia(s.getFrecuencia()+1);
				else
					this.fuente.getSimbolos().add(new Simbolo(String.valueOf(c)));
        	}
        }
        
        StringTokenizer st = new StringTokenizer(datos, " ¡¿:.,;!?\n\"");
        while (st.hasMoreTokens()) {
			i++;
        	simbolo = st.nextToken();
        	Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
			Simbolo s = (Simbolo) it.next();
			while(it.hasNext() && !s.getId().equals(simbolo))
				s = it.next();
			if(s.getId().equals(simbolo))
				s.setFrecuencia(s.getFrecuencia()+1);
			else
				this.fuente.getSimbolos().add(new Simbolo(simbolo));
		}
        this.probabilidad_Independiente(i);
	}
	
	
	public Fuente getFuente() {
		return fuente;
	}


	public void setFuente(Fuente fuente) {
		this.fuente = fuente;
	}


	public void probabilidad_Independiente(int size) {
		
		Iterator<Simbolo> it = this.fuente.getSimbolos().iterator();
		while(it.hasNext()) {
			Simbolo s = it.next();
			s.setProbabilidad((double)s.getFrecuencia()/size);
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
	
	public void compression_Rate(String nombre_archivo) {
		int auxFile;
		double tamComprimido=622,tamOrig=0;
		
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(nombre_archivo);
			br = new BufferedReader(fr);
			
			while ((auxFile = br.read()) != -1) {
				String binary = Integer.toBinaryString(auxFile);
				tamOrig+=binary.length();	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double N = tamOrig/tamComprimido;
		System.out.println("Tasa Compresion "+N+":1");
		
	}
	
	
	public void rebuild_File(String nombre_archivo,Fuente huffman) {
		char c;
		int i;
		
		StringBuilder codificacion = new StringBuilder();
		StringBuilder simbolo = new StringBuilder();
		
		for(i = 0; i < datos.length(); i++) {
			c = datos.charAt(i);
			
			if(c < 65 || c =='¿' || c =='¡') {
				Iterator<Simbolo> it;
				Simbolo s;
				
				if(simbolo.length() > 0) {
					it = this.fuente.getSimbolos().iterator();
					s = (Simbolo) it.next();
					while(it.hasNext() && !s.getId().equals(simbolo.toString()))
						s = it.next();
					codificacion.append(s.getCodigo());
					simbolo = new StringBuilder();
				}
				
				it = this.fuente.getSimbolos().iterator();
				s = (Simbolo) it.next();
				while(it.hasNext() && !s.getId().equals(String.valueOf(c)))
					s = it.next();
				codificacion.append(s.getCodigo());
			}
			else
				simbolo.append(c);
		}
		
		this.writeFile(nombre_archivo,codificacion.toString());
	}
}


