package org.example;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.io.*;

public class GeneratedTest_CE047 {
    @Test public void behavioralCase1() throws Exception {
        JobMarketplace j=new JobMarketplace(); j.postJob("Backend","Acme",Arrays.asList("Java")); assertEquals(1,j.getJobListings().size()); assertEquals("Backend",j.searchJobs("Java").get(0).getTitle());
    }
    @Test public void behavioralCase2() throws Exception {
        JobMarketplace j=new JobMarketplace(); j.submitResume("Ann",Arrays.asList("Java"),"2y"); assertEquals(1,j.getResumes().size());
    }
}
