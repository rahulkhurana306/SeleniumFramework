
package RKProject.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;



import RKProject.TestComponents.BaseTest;
import RKProject.TestComponents.Retry;
import RKProject.pageobjects.Lect159_ProductCatalogPage;
import RKProject.pageobjects.Lect161_CartPage;

public class Lect167_ErrorValTest extends BaseTest {

	@Test( groups = { "ErrorHandling" } , retryAnalyzer=Retry.class)
	

	public void LoginErrorValidation() throws IOException {

		landingPage.LoginApplication("rahul1234rahul@gmail.com", "Rahul@1234");
		// System.out.println(landingPage.GetErrorMessage());
		Assert.assertEquals(landingPage.GetErrorMessage(), "Incorrect email or password.");
	}

	@Test
	public void ProductErrorValidation() {
		String productName = "ZARA COAT 3";

		// Lect158_LandingPage landingPage = LaunchApplication();
		Lect159_ProductCatalogPage productCatalogPage = landingPage.LoginApplication("rahul1234rahul1234@gmail.com",
				"Rahul@1234");
		productCatalogPage.addProductToCart(productName);
		Lect161_CartPage cartPage = productCatalogPage.goToCart();
		Boolean match = cartPage.searchProductInCart("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
