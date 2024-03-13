package com.gdj.boot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
	
	@Scheduled(fixedRate = 1000000, initialDelayString = "2000000")
	public void useFixRate() {
		System.out.println("Fix Rate");
	}
	
	@Scheduled(fixedDelayString = "2000000", initialDelay = 2000000)
	public void useFixDelay() {
		System.out.println("Fix delay");
	}
	
	@Scheduled(cron = "10 * * 1 * *")
	public void useCron() {
		System.out.println("Cron");
	}
	
}
