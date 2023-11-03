package Page;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(value=Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/reporthtml.html"},
		dryRun = false,
		features = "classpath:",
		glue = "Page",
		tags = "",
		monochrome = true
)
public class Runner {
}