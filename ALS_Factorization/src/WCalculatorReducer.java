import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.*;
import util.Vector;
import java.util.Random;

public class WCalculatorReducer extends Reducer<Text, Text, Text, Text> {
    private Random rand = new Random();
    
    public void reduce(Text key, Iterable<Text> values, Context context) 
            throws IOException, InterruptedException {
        Vector w = new Vector(10); // 10 facteurs latents
        for (int i = 0; i < 10; i++) {
            w.setValue(i, rand.nextDouble());
        }
        context.write(key, new Text(w.toString()));
    }
}
