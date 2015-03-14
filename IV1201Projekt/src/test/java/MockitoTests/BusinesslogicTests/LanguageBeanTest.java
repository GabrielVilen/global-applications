package MockitoTests.BusinesslogicTests;

import java.util.List;
import java.util.Map;
import javax.faces.event.ValueChangeEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.businesslogic.LanguageBean;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 * Tests of the JobBean class
 *
 * @author Gabriel
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class LanguageBeanTest {

//    public static class LangaugeStaticTest {
//
//        public static void staticInit() {
//            languageBean.staticInit();
//        }
//    }

    private final static LanguageBean languageBean = new LanguageBean();
    private final static String EN = "en";
    private final static String SV = "sv";
    private int itemsInList;

    private List<Job> jobList;
    @Mock
    private ASDBController controller;

//    @BeforeClass
//    public static void setUpBeforeClass() {
//        Mock.redefineMethods(LanguageBean.class, LangaugeStaticTest.class);
//        
//    }

    @Before
    public void setUp() {
        System.out.println("seTUp running");
        try {
            LanguageBean.staticInit(); // TODO: FIX ERROR HERE
            itemsInList = 2;
            languageBean.setLocale(SV);
            languageBean.setLocale(EN);
        } catch (Exception e) {
            LoggerUtil.logTest(e, this);
            Assert.fail("Failed setting locale");
        }
    }

    @Test
    public void testChangeLocale() {
        languageBean.localeChanged(new ValueChangeEvent(null, EN, SV));
        if (languageBean.getLocale() != SV) {
            Assert.fail("Locale is not equal to 'sv'");
        }

    }

    @Test
    public void testJobList() {
        jobList = controller.getAllJobs(languageBean.getLocale());
        Assert.assertNotNull(jobList);
        for (Job jobList1 : jobList) {
            System.out.println(jobList1);
        }
        if (!(jobList.contains(EN) || jobList.contains(SV))) {
            Assert.fail("Joblist does not contain 'en' or 'sv'");
        }
        if (jobList.size() != itemsInList) {
            Assert.fail("Size of jobList = " + jobList.size() + ", should be " + itemsInList);
        }
    }

    @Test
    public void testLanguages() {
        Map langs = languageBean.getLanguages();
        Assert.assertNotNull(langs);

        if (!(langs.containsKey(SV) || langs.containsKey(EN))) {
            Assert.fail("language map does not contain 'sv' or 'en");
        }
        if (langs.size() != itemsInList) {
            Assert.fail("Size of language map  = " + langs.size() + ", should be " + itemsInList);
        }
    }
}
