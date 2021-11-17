package prueba;

import modelo.Compresion;

public class Prueba {

	public static void main(String[] args) {
		Compresion compressing = new Compresion("Argentina.txt");
		compressing.huffman(compressing.getFuente());
		System.out.println(compressing.getFuente());
		compressing.rebuild_File("Argentina.Huf", compressing.getFuente());
		compressing.compression_Rate("Argentina.txt");
	}

}
