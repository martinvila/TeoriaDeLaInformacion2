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
	
	
	public Compresion() {
		this.fuente = new Fuente();
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
		int i,size;
		char c;
		String simbolo;
		
		size=0;
		this.fuente.getSimbolos().add(new Simbolo(" "));
		this.fuente.getSimbolos().get(0).setFrecuencia(0);
        for(i = 0; i < datos.length(); i++) {
        	c = datos.charAt(i);
        	
        	if(c < 65 || c =='¿' || c =='¡' || c =='«' || c =='»') {
        		size++;
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
        
        StringTokenizer st = new StringTokenizer(datos, " '»«-¡¿:.,;!?\n\"");
        while (st.hasMoreTokens()) {
			size++;
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
        this.probabilidad_Independiente(size);
	}
	
	
	public void genera_fuente_img() {
		int i,size;
		char c;
		String simbolo;
		
		size=0;
		this.fuente.getSimbolos().add(new Simbolo("0"));
		this.fuente.getSimbolos().get(0).setFrecuencia(0);
        for(i = 9; i < datos.length(); i++) {
        	c = datos.charAt(i);
        	
        	if(c != 10) {
        		size++;
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
        this.probabilidad_Independiente(size);
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
	
	public void compression_Rate(double sizeCompr, String nombre_archivo) {
		int c = 0;
		double n,sizeOrig;
		String binary="";
		
		sizeOrig=0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(nombre_archivo));
			
			while ((c = in.read()) != -1) {
				binary = Integer.toBinaryString(c);
				sizeOrig+=binary.length();	
			}
			in.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		n = sizeOrig/sizeCompr;
		System.out.println("Tasa de compresion " + n + ":1");
		
	}
	
	public void shannonFano(Fuente fuente_ShannonFano,int i, int k, int m) {
		double sum,sum2,dif,min;
		int j;
		Simbolo simbolo;
		
		dif = 0;
		min = 1;
		if( (m-i) == 2) {
			fuente_ShannonFano.getSimbolos().get(i).setCodigo(fuente_ShannonFano.getSimbolos().get(i).getCodigo() +"1");
			fuente_ShannonFano.getSimbolos().get(k).setCodigo(fuente_ShannonFano.getSimbolos().get(k).getCodigo() +"0");
		}
		else if ((m-i) != 1) {
			while(dif <= min) {
				sum = 0;
				sum2 = 0;
				for(j = i; j <= k; j++) {
					sum += fuente_ShannonFano.getSimbolos().get(j).getProbabilidad();
				}
				for(j = k+1; j < m; j++) {
					sum2 += fuente_ShannonFano.getSimbolos().get(j).getProbabilidad();
				}
				dif = Math.abs(sum-sum2);
				if(dif < min)
					min = dif;
				k++;
			}
			for(j = i; j < k-1; j++) {
				simbolo = fuente_ShannonFano.getSimbolos().get(j);
				if(simbolo.getCodigo()==null)
					simbolo.setCodigo("1");
				else
					simbolo.setCodigo(simbolo.getCodigo() +"1");
			}
			for(j = k-1; j < m; j++) {
				simbolo = fuente_ShannonFano.getSimbolos().get(j);
				if(simbolo.getCodigo()==null)
					simbolo.setCodigo("0");
				else
					simbolo.setCodigo(simbolo.getCodigo() +"0");
			}
			shannonFano(fuente_ShannonFano, i, i+1, k-1);
			shannonFano(fuente_ShannonFano, k-1, k, m);
		}
	}
	
	public void rlc(String nombre_archivo) {
		int i,frecuencia;
		char c,c_ant;
		
		StringBuilder codificacion = new StringBuilder();
		
		i = 0;
		while( i < datos.length()-1) {
        	c_ant = datos.charAt(i);
        	c = datos.charAt(++i);
        	frecuencia = 1;
        	while((i < datos.length()-1) && c == c_ant) {
        		frecuencia++;
        		c_ant = c;
        		c = datos.charAt(++i);
        	}
        	codificacion.append(c_ant);
        	codificacion.append(frecuencia);

		}
		System.out.println(codificacion.toString());
		
		this.writeFile(nombre_archivo,codificacion.toString());     
        	
	}
	
	public void rebuild_File(String nombre_archivo,Fuente huffman) {
		char c;
		int i;
		
		StringBuilder codificacion = new StringBuilder();
		StringBuilder simbolo = new StringBuilder();
		
		for(i = 0; i < datos.length(); i++) {
			c = datos.charAt(i);
			
			if(c < 65 || c =='¿' || c =='¡' || c =='«' || c =='»') {
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


