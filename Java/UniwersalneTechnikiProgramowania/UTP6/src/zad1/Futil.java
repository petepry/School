package zad1;

import static java.nio.file.FileVisitResult.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil implements FileVisitor<Path> {
	
	
	static StringBuilder content;

	public static void processDir(String dirName, String resultFileName) {
		content = new StringBuilder("");
		Path path = Paths.get(dirName);
		Futil yaya = new Futil();
		try {
			Files.walkFileTree(path, yaya);
		} catch (IOException e) {
			System.out.println("I/O EXCEPTION.");
			return;
		}
		
//		System.out.println(content);
		
		OutputStreamWriter writer = null;
		try {
			writer = new OutputStreamWriter(
			        new FileOutputStream("." + "/" + resultFileName), "UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		BufferedWriter bufferedWriter = new BufferedWriter(writer);


		try {
			bufferedWriter.write(content.toString());
			bufferedWriter.close();
		} catch (IOException e) {
		e.printStackTrace();
		}

		
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attr) throws IOException {
		BufferedReader reader = null;
		if (attr.isRegularFile()) {
			if (file.getFileName().toString().endsWith(".txt")){
//				System.out.println("TXT FILE: " + file.getFileName());
//			else
//	            System.out.println("NOT A TXT FILE: " + file.getFileName());
			
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toString()), "Cp1250"));
				String line = "";
	
				while((line = reader.readLine()) != null ) {
					content.append(line);
					content.append("\n");
				}
				reader.close();
			}
        } 
		
        return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		return CONTINUE;
	}

	
	
	
	
	
	
	

}
