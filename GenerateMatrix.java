import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class GenerateMatrix {
public static void main(String[] args) throws IOException {
FileWriter writer = new FileWriter("matrix.txt");
Random rand = new Random();
int rows = 10000;
int cols = 512;
for (int i = 0; i < rows; i++) {
StringBuilder sb = new StringBuilder();
for (int j = 0; j < cols; j++) {
sb.append(rand.nextDouble());
if (j < cols - 1) sb.append(",");
}
writer.write(sb.toString() + "\n");
}
writer.close();
}
}
