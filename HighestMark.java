import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HighestMark {
    public static class MarkMapper extends Mapper<Object, Text, Text, Text> {
        private Text dept = new Text();
        private Text details = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split(",");
            if (parts.length == 3) {
                dept.set(parts[0]);
                details.set(parts[1] + "," + parts[2]);
                context.write(dept, details);
            }
        }
    }

    public static class MarkReducer extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int maxMark = 0;
            String topStudent = "";
            for (Text val : values) {
                String[] parts = val.toString().split(",");
                int mark = Integer.parseInt(parts[1]);
                if (mark > maxMark) {
                    maxMark = mark;
                    topStudent = parts[0];
                }
            }
            context.write(key, new Text(topStudent + " " + maxMark));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "highest mark dept");
        job.setJarByClass(HighestMark.class);
        job.setMapperClass(MarkMapper.class);
        job.setReducerClass(MarkReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}