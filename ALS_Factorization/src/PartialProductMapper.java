// src/PartialProductMapper.java
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import java.io.IOException;

public class PartialProductMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    protected void map(Text key, Text value, Context context) 
            throws IOException, InterruptedException {
        // À compléter selon la logique ALS
        context.write(key, value);
    }
}
