package org.example.scheduler;

public class SchedulerThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            Scheduler.getInstance()
                    .getJobs()
                    .stream()
                    .forEach(job->job.execute());
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
