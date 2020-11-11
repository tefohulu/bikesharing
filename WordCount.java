import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
    public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable>{
        private final static IntWritable one = new IntWritable(1);
        private Text seasons = new Text();
        private Text stations = new Text();

        public void map(Object key, Text value, Context context) 
        throws IOException, InterruptedException {
            StringTokenizer itr = new StringTokenizer(value.toString(), " ,"); // Splits when a comma detected
            if (itr.countTokens() == 8){ // Detects total token, if there are 8 tokens
                while (itr.hasMoreTokens()){    // Running through the file until the end of file
                    itr.nextToken();    // Skip next token
                    seasons.set(itr.nextToken());   // Set second token (seasons) to seasons variable
                    itr.nextToken(); // Skip next token
                    itr.nextToken(); // Skip next token
                    itr.nextToken(); // Skip next token
                    itr.nextToken(); // Skip next token
                    itr.nextToken(); // Skip next token
                    stations.set(itr.nextToken()); // Set eight token (stations) to stations variable
                    // Seasons subfunction
                    // If the seasons job runs, uncomment this section and comment stations section
//	    	        String sea = seasons.toString();    // Convert the seasons token
//	    	        if(!(sea.equals("seasons"))){
//	    		        context.write(seasons, one);    // Send to reducer
//	    	        }
                    // Stations subfunction
                    // If the stations job runs, uncomment this section and comment seasons section
                    String sta = stations.toString();   // Convert the stations token
                    if(!(sta.equals("stations"))){
                        context.write(stations, one);   // Send to reducer
                    }
                }
            }
        }
    }

    public static class IntSumReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        private IntWritable result = new IntWritable();
        public void reduce(Text key, Iterable<IntWritable> values, Context context) 
        throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(TokenizerMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
