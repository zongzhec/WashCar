package foo.zongzhe.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataUtil {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		initialize();
		calculateAngle();
	}

	private static void calculateAngle() throws IOException {
		// TODO Auto-generated method stub
		String inputFileName = "F:/calangle.txt";
		String outputFileName = "F:/result.txt";
		String input = "", output = "";
		Double x1 = new Double(0.0);
		Double y1 = new Double(0.0);
		Double x2 = new Double(0.0);
		Double y2 = new Double(0.0);

		File inFile = new File(inputFileName);
		File outFile = new File(outputFileName);
		if (outFile.exists()) {
			outFile.delete();
		}

		FileReader reader = new FileReader(inFile);
		BufferedReader bfReader = new BufferedReader(reader);
		input = bfReader.readLine();
		while ((input = bfReader.readLine()) != null) {
			String subStr1, subStr2;
			String strX1 = input.substring(0, input.indexOf(' '));
			subStr1 = input.substring(input.indexOf(' ') + 1, input.length());
			String strY1 = subStr1.substring(0, subStr1.indexOf(' '));
			subStr2 = subStr1.substring(subStr1.indexOf(' ') + 1, subStr1.length());
			String strX2 = subStr2.substring(0, subStr2.indexOf(' '));
			String strY2 = subStr2.substring(subStr2.indexOf(' ') + 1, subStr2.length());

			x1 = Double.valueOf(strX1);
			y1 = Double.valueOf(strY1);
			x2 = Double.valueOf(strX2);
			y2 = Double.valueOf(strY2);
			System.out.println("x1 = " + x1 + ", y1 = " + y1 + "， x2 = " + x2 + ", y2 = " + y2);

			Double angle;
			if (x1 > x2) {
				angle = Math.atan2(x1 - x2, y1 - y2);
			} else {
				angle = Math.atan2(x2 - x1, y2 - y1);
			}

			angle = angle * 180 / Math.PI;

			while (Math.abs(angle) > 180) {
				angle = Math.abs(angle - 360);
			}

			output = String.format("%.2f", angle);

			output = output + "\n";
			FileWriter writer = new FileWriter(outFile, true);
			System.out.println("output: " + output);
			writer.write(output);
			writer.close();
		}

		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		reader.close();

	}

	private static void initialize() {
		// TODO Auto-generated method stub

	}

}
