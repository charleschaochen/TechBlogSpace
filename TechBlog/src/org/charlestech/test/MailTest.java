package org.charlestech.test;

import org.charlestech.utils.MailUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.sun.mail.util.LineInputStream;
public class MailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
		MailUtils mu = context.getBean("mailUtils", MailUtils.class);
		mu.sendSimpleMail("399931011@qq.com", null, null, "test", "<a href='http://www.charlestech.org'>test</a>", true);
		mu.sendSimpleMail("bbao.cc@163.com", null, null, "test", "<a href='http://www.charlestech.org'>test</a>", true);
	}

}
