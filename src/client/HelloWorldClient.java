
package client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dao.SaveDataDao;
import com.service.SaveDataService;


public class HelloWorldClient {
	
	public static void main(String[] args) {		
		ApplicationContext context = new ClassPathXmlApplicationContext("dao-context.xml");		
		SaveDataService service = (SaveDataService)context.getBean("saveDataService");	
		
		HelloWorldClientSub thread1 = new HelloWorldClientSub(service, 1);
		thread1.start();
		HelloWorldClientSub thread2 = new HelloWorldClientSub(service, 2);
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((ClassPathXmlApplicationContext)context).close();
	}
	
}


class HelloWorldClientSub extends Thread {
	SaveDataService service = null;
	int  num = 0;
	
	public HelloWorldClientSub(SaveDataService service, int num) {
		this.service = service;
		this.num = num;
	}
	
	public void run() {
		if (num == 1)
		service.saveDataToDatabase();
		else 
		service.readFromDatabase();
	}
}



































