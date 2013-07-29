package com.ontology2.bakemono;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapred.lib.HashPartitioner;
import org.apache.hadoop.mapred.lib.MultipleOutputs;
import org.apache.hadoop.util.Tool;

public class PSE3Tool implements Tool {

	private Configuration conf;

	@Override
	public Configuration getConf() {
		return this.conf;
	}

	@Override
	public void setConf(Configuration arg0) {
		this.conf=arg0;
	}

	@Override
	public int run(String[] arg0) throws Exception {
		if(arg0.length!=2)
			Main.errorCausedByUser("You must specify both input and output paths");
		
		String input=arg0[0];
		String output=arg0[1];
		
		JobConf conf = new JobConf(this.conf,PSE3Tool.class);
		conf.setJobName("pseTriples");  
		conf.setOutputKeyClass(Text.class);  
		conf.setOutputValueClass(Text.class);  
		conf.setMapperClass(FreebaseRDFMapper.class);
		conf.setNumReduceTasks(0);
		conf.setPartitionerClass(HashPartitioner.class);
		conf.setInputFormat(TextInputFormat.class);  
		conf.setOutputFormat(TextOutputFormat.class);
		conf.setMapOutputCompressorClass(GzipCodec.class);
		
	    MultipleOutputs.addNamedOutput(conf, "rejected", TextOutputFormat.class, TextOutputFormat.class, Text.class);
		 
		FileInputFormat.addInputPath(conf,new Path(input));
		FileOutputFormat.setOutputPath(conf,new Path(output));
		FileOutputFormat.setCompressOutput(conf, true);
		FileOutputFormat.setOutputCompressorClass(conf, GzipCodec.class);
		RunningJob job=JobClient.runJob(conf);
		job.waitForCompletion();
		return job.getJobState();
	}

}