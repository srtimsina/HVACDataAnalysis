import java.io.IOException;
import java.util.List;

/**
 * Created by Nout on 7/7/16.
 */
public class AnalysisMapper extends Mapper<LongWritable,Text,IntWritable,Text>{

    private Splitter splitter;
    private Joiner joiner;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        splitter = Splitter.on(",").trimResults();
        joiner = Joiner.on(",");
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        List<String> values = Lists.newArrayList(splitter.split(value.toString()));
        String buildingId = values.get(0);
        values.remove(0);
        String remainedValue = joiner.join(values);
        context.write(new IntWritable(Integer.parseInt(buildingId)),new Text(remainedValue));
    }

}
