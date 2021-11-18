package prueba;

import java.util.Collections;

import modelo.Compresion;

public class Imagen {

	public static void main(String[] args) {
		//----------------- Huffman --------------------------
		
		Compresion compression_huffman = new Compresion();

		compression_huffman.readFile("imagen.raw");
		compression_huffman.genera_Fuente();
		compression_huffman.huffman(compression_huffman.getFuente());
		compression_huffman.getFuente().calcula_cantInformacion();
		compression_huffman.getFuente().calcula_entropia();
		compression_huffman.longitud_media();
		compression_huffman.getFuente().setRendimiento(compression_huffman.getFuente().getEntropia() / compression_huffman.getFuente().getLongitud_media());
		compression_huffman.rebuild_File("imagen.Huf", compression_huffman.getFuente());
		compression_huffman.compression_Rate(4976, "imagen.raw");
				
				
		//----------------- Shannon Fano --------------------------
				
		Compresion compression_shannon_fano = new Compresion();

		compression_shannon_fano.readFile("imagen.raw");
		compression_shannon_fano.genera_Fuente();
		Collections.sort(compression_shannon_fano.getFuente().getSimbolos());
		compression_shannon_fano.shannonFano(compression_shannon_fano.getFuente(), 0, 1,compression_shannon_fano.getFuente().getSimbolos().size());
		compression_shannon_fano.getFuente().calcula_cantInformacion();
		compression_shannon_fano.getFuente().calcula_entropia();
		compression_shannon_fano.longitud_media();
		compression_shannon_fano.getFuente().setRendimiento(compression_shannon_fano.getFuente().getEntropia() / compression_shannon_fano.getFuente().getLongitud_media());
		compression_shannon_fano.rebuild_File("imagen.Fan", compression_shannon_fano.getFuente());
		compression_shannon_fano.compression_Rate(5420,"imagen.raw");
				
				
		//----------------- RLC --------------------------
				
		Compresion compression_rlc = new Compresion();

		compression_rlc.readFile("imagen.raw");
//		compression_rlc.genera_Fuente();
		compression_rlc.rlc("imagen.rlc");
//		compression_rlc.getFuente().calcula_cantInformacion();
//		compression_rlc.getFuente().calcula_entropia();
//		compression_rlc.longitud_media();
//		compression_rlc.getFuente().setRendimiento(compression_rlc.getFuente().getEntropia() / compression_rlc.getFuente().getLongitud_media());
		compression_rlc.compression_Rate(5420, "imagen.raw");

	}

}
