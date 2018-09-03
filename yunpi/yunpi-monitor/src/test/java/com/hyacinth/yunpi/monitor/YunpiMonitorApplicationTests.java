package com.hyacinth.yunpi.monitor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = YunpiMonitorApplication.class)
public class YunpiMonitorApplicationTests {

	@Test
	public void contextLoads() {
	}

	/*@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendSimpleMail() throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("1208539256@qq.com");
		message.setTo("1208539256@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}*/
}
