package commons;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;;

@Sources({ "classpath:${envProperties}.properties" })
public interface Environment extends Config {
	
	@Key("app.url")
	String url();
	
	@Key("app.username")
	String appUsername();
	
	@Key("app.password")
	String appPassword();
	
	@Key("DB.Host")
	String databaseHostname();
	
	@Key("DB.username")
	String databaseUser();
	
	@Key("DB.password")
	String databasePassword();

}
