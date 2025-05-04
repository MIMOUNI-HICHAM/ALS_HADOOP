// src/MatrixLineMapper.java
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import java.io.IOException;

public class MatrixLineMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text rowId = new Text();
    private Text vector = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) 
            throws IOException, InterruptedException {
        rowId.set("row_" + key.get());
        vector.set(value.toString());
        context.write(rowId, vector);
    }
}
