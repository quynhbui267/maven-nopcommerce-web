package commons;

import java.io.File;

public class GlobalConstants {
	//System info
		public static final String PROJECT_PATH= System.getProperty("user.dir");
		public static final String OS_NAME= System.getProperty("os.name");
		public static final String JAVA_VERSION = System.getProperty("java.version");
				
		//App info_User
		public static final String DEV_USER_URL= "https://demo.nopcommerce.com/";
		
		//App info_Admin
		public static final String DEV_ADMIN_URL= "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F";
		public static final String ADMIN_USERNAME= "admin@yourstore.com";
		public static final String ADMIN_PASSWORD= "admin";
		
		//Wait info
		public static final int SHORT_TIMEOUT= 10;
		public static final int LONG_TIMEOUT= 30;
		
		//Dowwnload/Upload file
		public static final String UPLOAD_PATH = PROJECT_PATH + File.separator+ "uploadFiles"+File.separator;
		public static final String DOWNLOAD_PATH = PROJECT_PATH +  File.separator + "downloadFiles" +  File.separator;
		
		//Retry Case failed
		//public static final int RETRY_COUNT = 3;
		
		//Browser Logs/Extension
		public static final String REPORTING_SCREENSHOT = PROJECT_PATH + "/testEvidence/screenshot/";
		public static final String FIREFOX_LOGFILE = PROJECT_PATH + "/testEvidence/FirefoxLog.log";
		
		//HTML report folders
		public static final String EXTENT_REPORTING_PATH = PROJECT_PATH + "/testEvidence/extentReportV5.html";
		public static final String ALLURE_REPORTING_PATH = PROJECT_PATH + "/testEvidence/allure-results";
	
		
}
