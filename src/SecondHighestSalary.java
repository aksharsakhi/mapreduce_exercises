import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SecondHighestSalary {
    public static class SalaryMapper extends Mapper<Object, Text, Text, Text> {
        private Text constKey = new Text("salary");
        private Text data = new Text();
        
        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            data.set(value.toString());
            context.write(constKey, data);
        }
    }

    public static class SalaryReducer extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int max1 = Integer.MIN_VALUE;
            int max2 = Integer.MIN_VALUE;
            String name1 = "";
            String name2 = "";

            for (Text val : values) {
                String[] parts = val.toString().split(",");
                if (parts.length == 2) {
                    int salary = Integer.parseInt(parts[1]);
                    if (salary > max1) {
                        max2 = max1;
                        name2 = name1;
                        max1 = salary;
                        name1 = parts[0];
                    } else if (salary > max2 && salary != max1) {
                        max2 = salary;
                        name2 = parts[0];
                    }
                }
            }
            context.write(new Text(name2), new Text(String.valueOf(max2)));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "second highest salary");
        job.setJarByClass(SecondHighestSalary.class);
        job.setMapperClass(SalaryMapper.class);
        job.setReducerClass(SalaryReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}