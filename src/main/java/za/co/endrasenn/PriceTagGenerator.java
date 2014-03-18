package za.co.endrasenn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class PriceTagGenerator {

	public static void main(String[] args) {
		if ( args.length != 2){
			System.out.println("Usage: <Input File>");
			return;
		}
		
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		if ( !inputFile.exists()){
			System.out.println("Invalid file specified: " + args[0]);
			return;
		}
		try {
			System.out.println("Processing input file: " + args[0]);
			FruitBuilder fruitBuilder = new FruitBuilder();
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			BufferedWriter bw = new BufferedWriter( new FileWriter(outputFile));
			String line;
			String output[];
			line = br.readLine(); // Read header
			while ( (line = br.readLine()) != null){
				output = fruitBuilder.buildFruit( line).generatePriceTag().toArray(new String[]{});
				for (String outputLine : output) {
					bw.write(outputLine + "\r\n");
				}
			}	
			bw.flush();
			br.close();
			bw.close();
			System.out.println("Processed output file: " + args[1]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
}
