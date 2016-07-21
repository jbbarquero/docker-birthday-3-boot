package com.malsolo.docker.birthday.worker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DockerBirthday3BootWorkerApplication.class)
@WebAppConfiguration
public class DockerBirthday3BootWorkerApplicationTests {

	@Test
	public void contextLoads() {
	}

}
