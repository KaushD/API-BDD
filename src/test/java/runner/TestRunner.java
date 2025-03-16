package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions(
        features  = "src/test/java/features",
        glue = {"src/test/java/stepDefinitions"},
plugin = {"pretty","html:target/cucumber-reports","json:target/cucumber.json"},
        monochrome = true,
        publish = true

)
public class TestRunner extends AbstractTestNGCucumberTests {
//    @Override
    @DataProvider(parallel = true) //this will execute the Scenario Outline instances parallelly
    public Object[] scenario(){
        return super.scenarios();
    }
}
