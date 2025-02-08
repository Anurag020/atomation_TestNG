package tests;
import org.apache.logging.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger log = LogManager.getLogger("Logger Demo");
		System.out.println("This is Logger Demo");
		
		log.info("for info only");
		log.debug("for debug");
		log.error("error message");
		log.warn("for debug");
	}

}
