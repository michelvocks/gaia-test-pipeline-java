package io.gaiapipeline;

import io.gaiapipeline.javasdk.*;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Gaia Java Test Pipeline
 *
 */
public class App 
{
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        PipelineJob job1 = new PipelineJob();
        job1.setTitle("AwesomeJob");
        job1.setDescription("Awesome");
        ArrayList<PipelineArgument> pArgs1 = new ArrayList<>();
        PipelineArgument argUsername = new PipelineArgument();
        argUsername.setType(InputType.TextFieldInp);
        argUsername.setKey("username");
        argUsername.setDescription("Please provide the username:");
        pArgs1.add(argUsername);
        job1.setArgs(pArgs1);

        Handler handlerJob1 = (gaiaArgs) -> {
            LOGGER.info( "AwesomeJob execution started!" );
            for (PipelineArgument arg: gaiaArgs) {
                LOGGER.info("Key:" + arg.getKey() + ";Value:" + arg.getValue());
            }
        };
        job1.setHandler(handlerJob1);

        PipelineJob job2 = new PipelineJob();
        job2.setTitle("GetSecret");
        job2.setDescription("Get Secret from vault");
        ArrayList<PipelineArgument> pArgs2 = new ArrayList<>();
        PipelineArgument argVault = new PipelineArgument();
        argVault.setType(InputType.VaultInp);
        argVault.setKey("dbpassword");
        pArgs2.add(argVault);
        job2.setArgs(pArgs2);

        Handler handlerJob2 = (gaiaArgs) -> {
            LOGGER.info( "Get Secret execution started!" );
            for (PipelineArgument arg: gaiaArgs) {
                LOGGER.info("Key:" + arg.getKey() + ";Value:" + arg.getValue());
            }
        };
        job2.setHandler(handlerJob2);
        ArrayList<PipelineJob> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);

        Javasdk sdk = new Javasdk();
        try {
            sdk.Serve(jobs);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
