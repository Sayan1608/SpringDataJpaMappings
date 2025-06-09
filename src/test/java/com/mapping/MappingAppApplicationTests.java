package com.mapping;

import com.mapping.services.MovieSeatBookingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
class MappingAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MovieSeatBookingService bookingService;

	@Test
	public void testConcurrentBookingOptimistic() throws InterruptedException {
		Thread t1 = new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName() + " attempting to book seat");
                bookingService.bookSeatUsingOptimisticLock(4L,1L);
                System.out.println(Thread.currentThread().getName() + " successfully booked seat");
            } catch (Exception e) {
				System.out.println(Thread.currentThread().getName() + " failed to book seat");
            }
        });

		Thread t2 = new Thread(()->{
			try {
				System.out.println(Thread.currentThread().getName() + " attempting to book seat");
				bookingService.bookSeatUsingOptimisticLock(4L,2L);
				System.out.println(Thread.currentThread().getName() + " successfully booked seat");
			} catch (Exception e) {
				System.out.println(Thread.currentThread().getName() + " failed to book seat");
			}
		});

		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}

}
