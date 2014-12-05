package org.concordion.ext.demo;

import org.concordion.api.extension.Extensions;
import org.concordion.ext.LoggingTooltipExtension;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

/**
 * A fixture class for the LoggingDemo.html specification.
 * <p>
 * This adds the Logging Tooltip Extension to Concordion to show logging detail in the Concordion output. The extension picks up any logging output written to
 * Logback.
 * <p>
 * For the purposes of demonstration, we are logging details of WebDriver (Selenium 2) events using the SeleniumEventLogger. (This logging uses slf4j, which
 * writes to Logback when run with the slf4j-jdk14 jar).
 * <p>
 * Run this class as a JUnit test to produce the Concordion results.
 */
@RunWith(ConcordionRunner.class)
@Extensions(LoggingTooltipExtension.class)
public class LogbackLoggingDemo extends AcceptanceTest {

	private Integer result;

	public void multiply(final String calulation) {
		String values[] = calulation.split("[*]");
		
		getLogger().trace("TRACE level logging should NOT appear in the console, but SHOULD appear in the test log");
		getLogger().debug("DEBUG level logging  should NOT appear in the console, but SHOULD appear in the test log");
		getLogger().info("INFO level logging  should appear in the console, and the test log");
		getLogger().warn("WARN level logging  should appear in the console, and the test log");
		getLogger().error("ERROR level logging  should appear in the console, and the test log");
		getLogger().info("NOTE: none of this logging will appear in the tooltips, but the tooltip logging below will appear in the test log");
		
		addConcordionTooltip(String.format("Found value %s", values[0]));
		addConcordionTooltip(String.format("Found value %s", values[1]));
		
		result = Integer.parseInt(values[0].trim()) * Integer.parseInt(values[1].trim());		
	}

	public String getCalculatorResult() {
		addConcordionTooltip(String.format("Returning result %s", result));
		
		return result.toString();
	}
}
