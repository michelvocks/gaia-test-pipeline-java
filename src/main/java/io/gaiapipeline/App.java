package io.gaiapipeline;

import io.gaiapipeline.javasdk.Handler;
import io.gaiapipeline.javasdk.Javasdk;
import io.gaiapipeline.javasdk.PipelineJob;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        PipelineJob job1 = new PipelineJob();
        job1.setTitle("AwesomeJob");
        job1.setPriority(0);
        job1.setDescription("Awesome");

        Handler handler = (gaiaArgs) -> {
            System.out.println( "Hello World!" );
        };
        job1.setHandler(handler);
        ArrayList<PipelineJob> jobs = new ArrayList<>();
        jobs.add(job1);

        Javasdk sdk = new Javasdk();
        try {
            sdk.Serve(jobs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
