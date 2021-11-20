package prueba;

import java.util.Collections;
import modelo.Compresion;

public class Argentina {

	public static void main(String[] args) {
		
		System.out.println("\n----------------------- HUFFMAN ------------------------\n");
		
		Compresion compression_huffman = new Compresion();
		
		compression_huffman.readFile("Argentina.txt");
		compression_huffman.genera_Fuente();
		compression_huffman.huffman(compression_huffman.getFuente());
		compression_huffman.getFuente().calcula_cantInformacion();
		compression_huffman.getFuente().calcula_entropia();
		compression_huffman.longitud_media();
		compression_huffman.getFuente().setRendimiento((double)Math.round(compression_huffman.getFuente().getEntropia() / compression_huffman.getFuente().getLongitud_media() * 10000) / 10000);
		compression_huffman.rebuild_File("Argentina.Huf", compression_huffman.getFuente());
		compression_huffman.compression_Rate(4976,"Argentina.txt");
		System.out.println("Rendimiento: "+compression_huffman.getFuente().getRendimiento()*100+"%");
		System.out.println("Redundancia: "+(double)Math.round(((1-compression_huffman.getFuente().getRendimiento())* 100) * 10000) / 10000 +"%");
		
		
		
		System.out.println("\n--------------------- SHANNON FANO ---------------------\n");
		
		Compresion compression_shannon_fano = new Compresion();
		
		compression_shannon_fano.readFile("Argentina.txt");
		compression_shannon_fano.genera_Fuente();
		Collections.sort(compression_shannon_fano.getFuente().getSimbolos());
		compression_shannon_fano.shannonFano(compression_shannon_fano.getFuente(), 0, 1, compression_shannon_fano.getFuente().getSimbolos().size());
		compression_shannon_fano.getFuente().calcula_cantInformacion();
		compression_shannon_fano.getFuente().calcula_entropia();
		compression_shannon_fano.longitud_media();
		compression_shannon_fano.getFuente().setRendimiento((double)Math.round(compression_shannon_fano.getFuente().getEntropia() / compression_shannon_fano.getFuente().getLongitud_media() * 10000) / 10000);
		compression_shannon_fano.rebuild_File("Argentina.Fan", compression_shannon_fano.getFuente());
		compression_shannon_fano.compression_Rate(5420,"Argentina.txt");
		System.out.println("Rendimiento: "+compression_shannon_fano.getFuente().getRendimiento()*100+"%");
		System.out.println("Redundancia: "+(double)Math.round(((1-compression_shannon_fano.getFuente().getRendimiento())* 100) * 10000) / 10000 +"%");
		
		
		System.out.println("\n------------------------- RLC --------------------------\n");
		
		Compresion compression_rlc = new Compresion();

		compression_rlc.readFile("Argentina.txt");
		compression_rlc.genera_fuente_2(true);
		compression_rlc.getFuente().calcula_cantInformacion();
		compression_rlc.getFuente().calcula_entropia();
		compression_rlc.getFuente().setLongitud_media(16);
		compression_rlc.getFuente().setRendimiento((double)Math.round(100*compression_rlc.getFuente().getEntropia() / compression_rlc.getFuente().getLongitud_media() * 100) / 100);
		compression_rlc.rlc("Argentina.rlc",0,1);
		compression_rlc.compression_Rate(31171, "Argentina.txt");
		System.out.println("Rendimiento: "+compression_rlc.getFuente().getRendimiento()+"%");
		System.out.println("Redundancia: "+(double)Math.round(((100-compression_rlc.getFuente().getRendimiento())) * 100) / 100 +"%");
	}

}
