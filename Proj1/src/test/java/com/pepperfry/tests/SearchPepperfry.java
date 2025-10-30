package com.pepperfry.tests;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.pepperfry.base.BaseTest;
import com.pepperfry.pages.SearchPage;
import com.pepperfry.utilities.ExcelUtilities;
import com.pepperfry.utilities.Screenshots;

public class SearchPepperfry extends BaseTest {
	static String projectpath = System.getProperty("user.dir");
    @Test
    public void verifySearchAndFilter() throws Exception {
     
        test = extent.createTest("Search and Apply Filter Test");

        SearchPage search = new SearchPage(driver);

        try {
            // Step 1: Search for "Sofa"
            search.searchProduct("Sofa");
            Thread.sleep(2000);
            test.pass("Sofa item searched successfully");
            System.out.println("Searched");
            
            // Step 2: Apply "3 Seater" filter
            search.applyThreeSeaterFilter();
            Thread.sleep(2000);
            test.pass("Filter Applied Successfully");
            System.out.println("Filter Applied");

            // Step 3: Verify filtered results
            if (search.resultsDisplayed()) {
                test.pass("Successfully searched for 'Sofa' and applied '3 Seater' filter.");
                System.out.println("Successfully Searched");
                
            } else {
                String path = Screenshots.Capture(driver, "FilterFailed");
                test.fail("No results found after applying '3 Seater' filter.").addScreenCaptureFromPath(path);
                System.out.println("Failed Search");
            }

        } catch (Exception e) {
            String path = Screenshots.Capture(driver, "SearchFilterException");
            test.fail(" Exception during Search/Filter test: " + e.getMessage())
                .addScreenCaptureFromPath(path);
            
            System.out.println("Failed "+e.getMessage());
        }
    }
    @DataProvider
    public Object[][] logindata() throws InvalidFormatException, IOException {
        String excelpath = projectpath + "\\src\\test\\resources\\TestData\\data.xlsx";
        System.out.println("Excel Path: " + excelpath);
        return ExcelUtilities.getdata(excelpath, "Sheet1");
    }
}
