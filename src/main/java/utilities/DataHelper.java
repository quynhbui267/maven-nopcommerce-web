package utilities;

import com.github.javafaker.Faker;

public class DataHelper {
	// Nếu dùng data cho 1 quốc gia nào đó thì thêm param Locales
	private Faker faker = new Faker();

	public static DataHelper getDataHelper() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.address().firstName();
	}
	public String getLastName() {
		return faker.address().lastName();
	}
	
	public String getEmail() {
		return faker.internet().emailAddress();
	}
	
	public String getPassword() {
		return faker.internet().password();
	}

}
