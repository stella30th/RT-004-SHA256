package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class GeneratedTest_CE047 {

@Test
public void testPostAndRemoveJob() {
    JobMarketplace marketplace = new JobMarketplace();
    marketplace.postJob("Backend Engineer", "TechCorp", Arrays.asList("Java", "SQL"));
    assertEquals(1, marketplace.getJobListings().size());

    JobMarketplace.JobListing listing = marketplace.getJobListings().get(0);
    assertEquals("Backend Engineer", listing.getTitle());
    assertEquals("TechCorp", listing.getCompany());

    marketplace.removeJob(listing);
    assertTrue(marketplace.getJobListings().isEmpty());
}

@Test
public void testSubmitAndWithdrawResume() {
    JobMarketplace marketplace = new JobMarketplace();
    marketplace.submitResume("Alice", Arrays.asList("Java", "Spring"), "5 years");
    assertEquals(1, marketplace.getResumes().size());

    JobMarketplace.Resume resume = marketplace.getResumes().get(0);
    assertEquals("Alice", resume.getName());
    assertEquals("5 years", resume.getExperience());

    marketplace.withdrawResume(resume);
    assertTrue(marketplace.getResumes().isEmpty());
}

@Test
public void testSearchJobs() {
    JobMarketplace marketplace = new JobMarketplace();
    marketplace.postJob("Java Dev", "CompanyA", Arrays.asList("Java", "Docker"));
    marketplace.postJob("Frontend Dev", "CompanyB", Arrays.asList("JavaScript", "React"));

    List<JobMarketplace.JobListing> javaJobs = marketplace.searchJobs("Java");
    assertEquals(1, javaJobs.size());
    assertEquals("Java Dev", javaJobs.get(0).getTitle());

    List<JobMarketplace.JobListing> pythonJobs = marketplace.searchJobs("Python");
    assertTrue(pythonJobs.isEmpty());
}

@Test
public void testGetJobApplicantsAndMatchesRequirements() {
    JobMarketplace marketplace = new JobMarketplace();
    marketplace.postJob("Fullstack", "CompanyC", Arrays.asList("Java", "React"));
    JobMarketplace.JobListing job = marketplace.getJobListings().get(0);

    marketplace.submitResume("Alice", Arrays.asList("Java", "React"), "3 years"); // Exact match
    marketplace.submitResume("Bob", Arrays.asList("Java"), "2 years");            // Missing skill
    marketplace.submitResume("Charlie", Arrays.asList("Java", "React", "AWS"), "4 years"); // Extra skill -> not exact match size

    List<JobMarketplace.Resume> applicants = marketplace.getJobApplicants(job);
    assertEquals(1, applicants.size());
    assertEquals("Alice", applicants.get(0).getName());
}

}
