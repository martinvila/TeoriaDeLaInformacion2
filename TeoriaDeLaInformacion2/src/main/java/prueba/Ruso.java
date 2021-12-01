package prueba;

import java.util.Collections;

import modelo.Compresion;

public class Ruso {

	public static void main(String[] args) {
		
		System.out.println("\n----------------------- HUFFMAN ------------------------\n");
		
		Compresion compression_huffman = new Compresion();
		
		compression_huffman.readFile("ruso.txt");
		compression_huffman.genera_fuente(true);
		compression_huffman.huffman(compression_huffman.getFuente());
		compression_huffman.getFuente().calcula_cantInformacion();
		compression_huffman.getFuente().calcula_entropia();
		compression_huffman.longitud_media();
		compression_huffman.getFuente().setRendimiento((double)Math.round(compression_huffman.getFuente().getEntropia() / compression_huffman.getFuente().getLongitud_media() * 10000) / 10000);
		compression_huffman.rebuild_file("ruso.Huf", compression_huffman.getFuente());
		compression_huffman.compression_Rate(12345, "ruso.txt");
		System.out.println("Rendimiento: "+compression_huffman.getFuente().getRendimiento()*100+"%");
		System.out.println("Redundancia: "+(double)Math.round(((1-compression_huffman.getFuente().getRendimiento())* 100) * 10000) / 10000 +"%");
		
		
		System.out.println("\n--------------------- SHANNON FANO ---------------------\n");
		
		Compresion compression_shannon_fano = new Compresion();
		
		compression_shannon_fano.readFile("ruso.txt");
		compression_shannon_fano.genera_fuente(true);
		Collections.sort(compression_shannon_fano.getFuente().getSimbolos());
		compression_shannon_fano.shannonFano(compression_shannon_fano.getFuente(), 0, 1, compression_shannon_fano.getFuente().getSimbolos().size());
		compression_shannon_fano.getFuente().calcula_cantInformacion();
		compression_shannon_fano.getFuente().calcula_entropia();
		compression_shannon_fano.longitud_media();
		compression_shannon_fano.getFuente().setRendimiento((double)Math.round(compression_shannon_fano.getFuente().getEntropia() / compression_shannon_fano.getFuente().getLongitud_media() * 100000) / 100000);
		compression_shannon_fano.rebuild_file("ruso.Fan", compression_shannon_fano.getFuente());
		compression_shannon_fano.compression_Rate(12534,"ruso.txt");
		System.out.println("Rendimiento: "+compression_shannon_fano.getFuente().getRendimiento()*100+"%");
		System.out.println("Redundancia: "+(double)Math.round(((1-compression_shannon_fano.getFuente().getRendimiento())* 100) * 10000) / 10000 +"%");
		
		
		System.out.println("\n------------------------- RLC --------------------------\n");
		
		Compresion compression_rlc = new Compresion();

		compression_rlc.readFile("ruso.txt");
		compression_rlc.genera_fuente(true);
		compression_rlc.getFuente().calcula_cantInformacion();
		compression_rlc.getFuente().calcula_entropia();
		compression_rlc.getFuente().setLongitud_media(16);
		compression_rlc.getFuente().setRendimiento((double)Math.round(compression_rlc.getFuente().getEntropia() / compression_rlc.getFuente().getLongitud_media() * 10000) / 10000);
		compression_rlc.rlc("ruso.rlc",0,1);
		compression_rlc.compression_Rate(38477, "ruso.txt");
		System.out.println("Rendimiento: "+compression_rlc.getFuente().getRendimiento()*100+"%");
		System.out.println("Redundancia: "+(double)Math.round(((1-compression_rlc.getFuente().getRendimiento())* 100) * 10000) / 10000 +"%");
	}

}
