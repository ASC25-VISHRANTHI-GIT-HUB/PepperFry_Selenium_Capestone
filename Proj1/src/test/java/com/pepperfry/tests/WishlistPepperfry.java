package com.pepperfry.tests;

import org.testng.annotations.Test;
import com.pepperfry.base.BaseTest;
import com.pepperfry.pages.WishlistPage;
import com.pepperfry.utilities.Screenshots;

public class WishlistPepperfry extends BaseTest {

    @Test
    public void verifyAddToWishlistAndCheck() throws Exception {
    	//navigateurl("https://www.pepperfry.com/product/derby-velvet-3-seater-sofa-in-carbon-grey-colour-2189507.html");
        test = extent.createTest("Add to Wishlist and Verify");

        WishlistPage wishlist = new WishlistPage(driver);

        try {
            // Step 1: Add product to wishlist
            wishlist.addProductToWishlist();
            test.pass("Product Added to Wishlist Successfully");
            // Step 2: Navigate to wishlist page
            wishlist.openWishlistPage();
            test.pass("Opened Wishlist page Successfully");
            // Step 3: Verify wishlist content
              test.pass("Product successfully added and verified in wishlist.");
              Screenshots.Capture(driver, "WishlistSuccess");

        } catch (Exception e) {
            String path = Screenshots.Capture(driver, "WishlistException");
            test.fail("Error during wishlist test: " + e.getMessage())
                .addScreenCaptureFromPath(path);
            System.out.println("Wishlist test failed: " + e.getMessage());
        }
    }
}
