import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

/**
 * Created by Nout on 7/7/16.
 */
public class DataAnalysisDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        String inputFile = "input/firstResult.csv";
        Configuration configuration = new Configuration();
        Job analysisJob = Job.getInstance(configuration);


        FileInputFormat.setInputPaths(analysisJob,inputFile);
        FileOutputFormat.setOutputPath(analysisJob,new Path("secondResult.csv"));


        analysisJob.setInputFormatClass(TextInputFormat.class);
        analysisJob.setOutputFormatClass(TextOutputFormat.class);


        analysisJob.setOutputKeyClass(IntWritable.class);
        analysisJob.setOutputValueClass(Text.class);

        analysisJob.setMapperClass(AnalysisMapper.class);
        analysisJob.setReducerClass(AnalysisReducer.class);

        analysisJob.setJarByClass(DataAnalysisDriver.class);
        analysisJob.setJobName("First Written Job");

        analysisJob.waitForCompletion(true);

    }
}
