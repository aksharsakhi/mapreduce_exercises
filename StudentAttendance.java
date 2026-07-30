import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class StudentAttendance {
    public static class AttendanceMapper extends Mapper<Object, Text, Text, Text> {
        private Text studentId = new Text();
        private Text status = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String[] parts = value.toString().split(",");
            if (parts.length == 2) {
                studentId.set(parts[0]);
                status.set(parts[1]);
                context.write(studentId, status);
            }
        }
    }

    public static class AttendanceReducer extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int total = 0;
            int present = 0;
            for (Text val : values) {
                total++;
                if (val.toString().equals("Present")) present++;
            }
            double percentage = (double) Math.round(((double) present / total) * 10000) / 100;
            context.write(key, new Text(percentage + "%"));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "student attendance");
        job.setJarByClass(StudentAttendance.class);
        job.setMapperClass(AttendanceMapper.class);
        job.setReducerClass(AttendanceReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}