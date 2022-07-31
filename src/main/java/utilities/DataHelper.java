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

	public String getCity() {
		return faker.address().cityName();
	}

	public String getAddress() {
		return faker.address().fullAddress();
	}

	public String getPostalCode() {
		return faker.address().zipCode();
	}

	public String getPhoneNumber() {
		return faker.phoneNumber().phoneNumber();

	}

	public String getDay() {
		return String.valueOf(faker.number().numberBetween(0, 29));
	}

	public String getYear() {
		return String.valueOf(faker.number().numberBetween(1950, 2021));
	}

}
