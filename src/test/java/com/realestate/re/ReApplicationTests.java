package com.realestate.re;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("test")
public class ReApplicationTests {

	@Test
	public void contextLoads() {
		Assert.assertEquals("test1" , "test1");
	}

}
