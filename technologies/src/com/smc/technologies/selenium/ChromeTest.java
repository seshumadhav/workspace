package com.smc.technologies.selenium;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get("file:///Users/seshu/indeed/appmagic/sample-page1.html");
		Thread.sleep(3000);
		
		String pageSourceBefore = driver.getPageSource();
		toFile(pageSourceBefore, "sample-page1-take-pagesource-before-clicking-using-driverpagesource.html");
		
		
		System.out.println("Clicking button now!");
		WebElement button = driver.findElement(By.id("b1"));
		button.click();
		Thread.sleep(3000);
		
		String pageSourceAfter = driver.getPageSource();
		toFile(pageSourceAfter, "sample-page1-take-pagesource-after-clicking-using-driverpagesource.html");
		Thread.sleep(3000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Object obj = js.executeScript("var html = document.documentElement.outerHTML; return html;");
		String pageSourceFromJS = (String) obj;
		toFile(pageSourceFromJS, "sample-page1-take-dom-after-clicking-using-driverexecutejs.html");
		Thread.sleep(3000);
		
		driver.quit();
	}

	public static void toFile(String content, String filepath) {
		System.out.println("Writing content to " + filepath);
		try {
			File file = new File(filepath);

			if (!file.exists( )) {
				file.createNewFile( );
			}

			FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
			BufferedWriter bw = new BufferedWriter( fw );
			bw.write(content);
			bw.close( );
		} catch( IOException e ) {
			System.out.println("Error: " + e);
			e.printStackTrace( );
		}
	}
}