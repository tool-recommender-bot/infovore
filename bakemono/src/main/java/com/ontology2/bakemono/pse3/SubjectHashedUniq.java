package com.ontology2.bakemono.pse3;

import com.hp.hpl.jena.graph.Triple;
import com.ontology2.bakemono.jena.WritableTriple;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SubjectHashedUniq extends Reducer<WritableTriple, WritableTriple, Triple, LongWritable> {
    private static org.apache.commons.logging.Log logger = LogFactory.getLog(SubjectHashedUniq.class);
    final LongWritable ONE = new LongWritable(1);

    @Override
    protected void reduce(WritableTriple key, Iterable<WritableTriple> value,
                          Context context)
            throws IOException, InterruptedException {

        WritableTriple last=null;
        for(WritableTriple v:value) {
            incrementCounter(context, UniqCounters.TOTAL_VALUES, 1);
            if(last==v)
                continue;

            incrementCounter(context,UniqCounters.DISTINCT_KEYS,1);

            context.write(v.getTriple(), ONE);
        };
    }

    //
    // this code prevents failing test because the mock object Context we are passing back
    // always returns null from getCounter...  With a more sophisticated mock object perhaps
    // the system will produce individual mocks for each counter so we can watch what
    // happens with counters
    //

    private void incrementCounter(Context context,Enum <?> counterId,long amount) {
        Counter counter=context.getCounter(counterId);
        if(counter!=null) {
            counter.increment(amount);
        };
    };
}
