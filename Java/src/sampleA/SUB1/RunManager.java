package sampleA.SUB1;


import mq.FileReaderH;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class RunManager {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		String line = scanner.nextLine();
//		Path path = Paths.get("src/");
//		System.out.println(path);
		List<String> strings = FileReaderH.readAll(line + ".txt");
		System.out.println(strings.get(0));
	}

}
