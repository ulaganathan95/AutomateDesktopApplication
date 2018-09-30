package com.Six.Secom.Automate.Desktop;


/*Created by Ulaganathan on 25.06.2018*/
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

public class WiniumDesktopAutomation {

	WiniumDriverService service;

	WiniumDriver driver;

	@Before
	public void DriverStartup() throws IOException {

		// To Start Winium Driver Service
		File driverPath = new File("D:\\workspace\\Winium Drivers\\Winium.Desktop.Driver.exe");

		service = new WiniumDriverService.Builder().usingDriverExecutable(driverPath).usingPort(9999).withVerbose(true)
				.withSilent(false).buildDesktopService();

		service.start();

	}

	@Test
	public void process() throws IOException, InterruptedException {

		DesktopOptions options = new DesktopOptions();

		options.setApplicationPath("C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe");

		// Finding Elements

		driver = new WiniumDriver(service, options);

		driver.findElement(By.name("Media")).click();
		driver.findElement(By.name("Open File...")).click();
		driver.findElement(By.name("Desktop")).click();
		driver.findElement(By.name("Name")).click();
		driver.findElement(By.name("Open")).click();
		driver.findElement(By.name("Maximize")).click();

		driver.findElement(By.name("Close")).click();

	}

	@After
	public void closeConnection() throws InterruptedException, IOException {
		Thread.sleep(300);
		driver.close();
		service.stop();

		Runtime.getRuntime().exec("TASKKILL /F /IM Winium.Desktop.Driver.exe");
		System.exit(0);

	}
}
