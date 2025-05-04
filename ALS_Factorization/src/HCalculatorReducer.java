// src/HCalculatorReducer.java
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class HCalculatorReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) 
            throws IOException, InterruptedException {
        // À compléter selon la logique ALS
        for (Text value : values) {
            context.write(key, value);
        }
    }
}
