package com.gdj.boot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	
	@Scheduled(fixedRate = 1000, initialDelayString = "2000")
	public void useFixRate() {
		System.out.println("Fix Rate");
	}
	
	@Scheduled(fixedDelayString = "2000", initialDelay = 2000)
	public void useFixDelay() {
		System.out.println("Fix delay");
	}
	
	@Scheduled(cron = "10 * * * * *")
	public void useCron() {
		System.out.println("Cron");
	}
	
}
