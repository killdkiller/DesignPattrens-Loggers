package org.epam.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.epam.driver.SetUpSelenium;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustomListener implements ITestListener,ISuiteListener,IInvokedMethodListener {
	
	private Logger logger = LogManager.getRootLogger();

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("started the test :"+result.getName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Successfully completed the test :"+result.getName());
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("Taking Screen shot after failing"+result.getName());
		takeScreenshot(result.getName());
		logger.error("Failed while executing test :"+result.getName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.error("skipped executing test :"+result.getName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite suite) {
		logger.info("Started executing the Suite"+suite.getName());
		
	}

	@Override
	public void onFinish(ISuite suite) {
		logger.info("Finished executing the Suite"+suite.getName());
		
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
	}
	
	private void takeScreenshot(String methodName){
        File screenCapture = ((TakesScreenshot)SetUpSelenium.getDriver())
                					.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/"+methodName+"screenshot.png"));
        } catch (IOException e) {
        	logger.info("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

}
