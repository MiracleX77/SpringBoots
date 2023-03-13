package com.example.test_spring;

import com.example.test_spring.entity.Address;
import com.example.test_spring.entity.Social;
import com.example.test_spring.entity.User;
import com.example.test_spring.exception.BaseException;
import com.example.test_spring.service.AddressService;
import com.example.test_spring.service.SocialService;
import com.example.test_spring.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

	@Autowired
	private UserService userService;
	@Autowired
	private SocialService socialService;

	@Autowired
	private AddressService addressService;
	@Order(1)
	@Test
	void testCreate() throws BaseException {
		User user = userService.create(
				TestData.email,
				TestData.password,
				TestData.name
		);
		//check not null
		Assertions.assertNotNull(user);
		Assertions.assertNotNull(user.getId());
		//check equals
		Assertions.assertEquals(TestData.email,user.getEmail());

		boolean match = userService.matchPassword(TestData.password,user.getPassword());
		Assertions.assertTrue(match);
		Assertions.assertEquals(TestData.name,user.getName());
	}

	@Order(2)
	@Test
	void testUpdate() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();
		User updatedUser = userService.updateName(user.getId(),TestDataUpdate.name);

		Assertions.assertNotNull(updatedUser);
		Assertions.assertEquals(updatedUser.getName(),TestDataUpdate.name);
	}
	@Order(3)
	@Test
	void testCreateSocial() throws BaseException {
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());

		User user = opt.get();

		Social social = user.getSocial();
		Assertions.assertNull(social);

		social=socialService.create(user,
				TestSocialCreate.facebook,
				TestSocialCreate.line,
				TestSocialCreate.instagram,
				TestSocialCreate.tiktok);

		Assertions.assertNotNull(social);
		Assertions.assertEquals(social.getFacebook(),TestSocialCreate.facebook);
	}
	@Order(4)
	@Test
	void testCreateAddress(){
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();
		List<Address> addresses= user.getAddresses();
		Assertions.assertTrue(addresses.isEmpty());

		createAddress(user,TestAddressCreate.line1,TestAddressCreate.line2,TestAddressCreate.zipcode);
		createAddress(user,TestAddressCreate2.line1,TestAddressCreate2.line2,TestAddressCreate2.zipcode);


	}
	private void createAddress(User user,String line1,String line2,String zipcode){
		Address address = addressService.create(user,line1,line2,zipcode);
		Assertions.assertNotNull(address);
		Assertions.assertEquals(line1,address.getLine1());
		Assertions.assertEquals(line2,address.getLine2());
		Assertions.assertEquals(zipcode,address.getZipcode());
	}

	@Order(9)
	@Test
	void testDelete() {
		Optional<User> opt = userService.findByEmail(TestData.email);
		Assertions.assertTrue(opt.isPresent());
		User user = opt.get();

		Social social=user.getSocial();
		Assertions.assertNotNull(social);
		Assertions.assertEquals(social.getFacebook(),TestSocialCreate.facebook);

		List<Address> addresses = user.getAddresses();
		Assertions.assertEquals(2,addresses.size());
		Assertions.assertFalse(addresses.isEmpty());

		userService.deleteById(user.getId());
		Optional<User> optDelete = userService.findByEmail(TestData.email);
		Assertions.assertTrue(optDelete.isEmpty());

	}

	interface TestData{
		String email = "big1@test.com";
		String password = "miracle";
		String name = "big";
	}

	interface TestDataUpdate{
		String name = "biggest";
	}
	interface TestSocialCreate{
		String facebook = "phatchara";

		String line="";

		String instagram = "";

		String tiktok = "";

	}
	interface TestAddressCreate{
		String line1 ="bigzaa852";

		String line2 = "asd";

		String zipcode = "84160";
	}
	interface TestAddressCreate2{
		String line1 ="bigzaa8522";

		String line2 = "asdasd";

		String zipcode = "84161";
	}

}
