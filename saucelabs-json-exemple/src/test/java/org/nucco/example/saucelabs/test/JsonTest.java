package org.nucco.example.saucelabs.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;

import com.saucelabs.junit.Parallelized;
import com.sebuilder.interpreter.IO;
import com.sebuilder.interpreter.Script;
import com.sebuilder.interpreter.webdriverfactory.Remote;

@RunWith(Parallelized.class)
public class JsonTest {

    private static final Log LOG = LogFactory.getLog(JsonTest.class);

    private HashMap<String, String> config;

    private String browser;
    private String os;
    private String version;

    public JsonTest(String os, String version, String browser) {
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static LinkedList<String[]> browsersStrings() throws Exception {
        LinkedList<String[]> browsers = new LinkedList<String[]>();
        browsers.add(new String[]{Platform.XP.toString(), "21", "firefox"});
        return browsers;
    }

    @Before
    public void setUp() throws Exception {
        String username = Utils.readPropertyOrEnv("SAUCE_USER_NAME", "");
        String accessKey = Utils.readPropertyOrEnv("SAUCE_API_KEY", "");
        String seleniumHost = Utils.readPropertyOrEnv("SELENIUM_HOST", "");
        String seleniumPort = Utils.readPropertyOrEnv("SELENIUM_PORT", "");

        config = new HashMap<String, String>();
        config.put("url", "http://" + username + ":" + accessKey + "@" + seleniumHost + ":" + seleniumPort + "/wd/hub");
        config.put(CapabilityType.BROWSER_NAME, browser);
        config.put(CapabilityType.VERSION, version);
        config.put(CapabilityType.PLATFORM, os);
    }

    @Test
    public void json() throws IOException, JSONException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("org/nucco/example/saucelabs/test/JsonTest.json");
        Script script = IO.read(new InputStreamReader(is));
        script.run(LOG, new Remote(), config);
    }

}
