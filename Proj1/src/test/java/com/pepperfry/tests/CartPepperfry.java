package com.pepperfry.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pepperfry.base.BaseTest;
import com.pepperfry.pages.loginPage;
import com.pepperfry.pages.CartPage;
import com.pepperfry.utilities.ExcelUtilities;
import com.pepperfry.utilities.Screenshots;

public class CartPepperfry extends BaseTest {

	@Test
	public void verifyAddToCartFunctionality() {
		navigateurl("https://www.pepperfry.com/product/derby-velvet-3-seater-sofa-in-carbon-grey-colour-2189507.html");
	    test = extent.createTest("Add To Cart and verified");
	    CartPage cartPage = new CartPage(driver);

	    try {
	        cartPage.clickAddToCart();
	        test.pass("Clicked Add to Cart");
	        Thread.sleep(2000);
	        cartPage.clickGoToCart();
	        test.pass("Opened Cart page Successfully");
	        if(cartPage.Proceed())
	        {
        	   test.pass("Product successfully added in cart.");
   	        } else {
   	            test.fail("Product not added in cart");
   	        }


	    } catch (Exception e) {
	        test.fail("Exception during cart test: " + e.getMessage());
	        System.out.println("Error during test: " + e.getMessage());
	    }
	}

    
}
