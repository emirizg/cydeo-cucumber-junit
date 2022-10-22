package com.cydeo.tests;

import com.cydeo.pages.VyTrackDashboardPage;
import com.cydeo.pages.VyTrackLoginPage;
import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VyTrackLoginDDTTest {

    VyTrackLoginPage loginPage = new VyTrackLoginPage();
    VyTrackDashboardPage dashboardPage = new VyTrackDashboardPage();

    //@Before
    public void setUp(){
        //Driver.getDriver().get("http://qa2.vytrack.com/user/login");
    }

    //@After
    public void tearDown(){

    }

    @Test
    public void loginDDTTest() throws IOException, InterruptedException {

        Driver.getDriver().get("http://qa2.vytrack.com/user/login");

        String filePath = "VyTrackQa2Users.xlsx";
        FileInputStream in = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(in);
        XSSFSheet sheet = workbook.getSheet("data");

        for (int rowNum = 1; rowNum<=sheet.getLastRowNum(); rowNum++){

            String userName = sheet.getRow(rowNum).getCell(0).toString();
            String password = sheet.getRow(rowNum).getCell(1).toString();
            String firstName = sheet.getRow(rowNum).getCell(2).toString();
            String lastName = sheet.getRow(rowNum).getCell(3).toString();



            loginPage.login(userName,password);

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
            WebElement loaderMask = Driver.getDriver().findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));

            String actualFullName = dashboardPage.fullName.getText();

            XSSFCell resultCell = sheet.getRow(rowNum).getCell(4);

            if ( actualFullName.contains(firstName) && actualFullName.contains(lastName) ){
                System.out.println("PASS");
                resultCell.setCellValue("PASS");
            }else {
                System.out.println("FAIL");
                resultCell.setCellValue("FAIL");
            }


            dashboardPage.logout();
        }

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);

        in.close();
        out.close();
        workbook.close();

        Driver.closeDriver();

    }

}
