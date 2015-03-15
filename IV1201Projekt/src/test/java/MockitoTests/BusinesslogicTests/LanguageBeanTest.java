package MockitoTests.BusinesslogicTests;

import MockitoTests.UtilTests.ContextMocker;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.businesslogic.LanguageBean;
import se.kth.iv1201projekt.integration.ASDBController;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.util.LoggerUtil;

/**
 * Tests of the LanguageBean class.
 *
 * @author Gabriel
 */
@RunWith(MockitoJUnitRunner.class)
public class LanguageBeanTest {

    private static LanguageBean languageBean;
    private final static String EN = "en";
    private final static String SV = "sv";
    private int itemsInList;

    @Mock
    private Job job1, job2;
    @Mock
    private ASDBController controller;

    /**
     * Set up the language and job list.
     */
    @Before
    public void setUp() {
        try {
            itemsInList = 2;

            ArrayList<Locale> locList = new ArrayList();
            locList.add(new Locale(SV, ""));
            FacesContext context = ContextMocker.mockFacesContext();
            when(context.getApplication().getDefaultLocale()).thenReturn(Locale.ENGLISH);
            when(context.getApplication().getSupportedLocales()).thenReturn(locList.iterator());
            languageBean = new LanguageBean();

            List<Job> jobList = new ArrayList<>();
            job1 = new Job(1, "type_1", "information_1", new Date(), new Date(), "name_1", 1);
            job2 = new Job(2, "type_2", "information_2", new Date(), new Date(), "name_2", 1);
            jobList.add(job1);
            jobList.add(job2);
            Mockito.when(controller.getAllJobs(languageBean.getLocale())).thenReturn(jobList);
        } catch (Exception e) {
            LoggerUtil.logTest(e, this);
        }
    }

    /**
     * Test changing the locale (language) in the language bean.
     */
    @Test
    public void testChangeLocale() {
        UIComponent component = Mockito.mock(UIComponent.class);
        languageBean.localeChanged(new ValueChangeEvent(component, EN, SV));
        if (!(languageBean.getLocale().equals(SV))) {
            Assert.fail("Locale is not equal to 'sv'");
        }

    }

    /**
     * Test if the job list contains the correct data.
     */
    @Test
    public void testJobList() {
        List<Job> jobList = controller.getAllJobs(languageBean.getLocale());
        Assert.assertNotNull(jobList);

        if (!(jobList.contains(job1) || jobList.contains(job2))) {
            Assert.fail("Joblist does not contain  " + job1 + "  or " + job2);
        }
        if (jobList.size() != itemsInList) {
            Assert.fail("Size of jobList = " + jobList.size() + ", should be " + itemsInList);
        }
        for (Job job : jobList) {
            Assert.assertNotNull(job.getName());
            Assert.assertNotNull(job.getInformation());
            Assert.assertNotNull(job.getType());
            Assert.assertNotNull(job.getId());
            Assert.assertNotNull(job.getVersion());
            Assert.assertNotNull(job.getStartDate());
        }
    }

    /**
     * Test if the language map contains the correct data.
     */
    @Test
    public void testLanguages() {
        Map langs = languageBean.getLanguages();
        Assert.assertNotNull(langs);

        if (!(langs.containsKey(new Locale(SV, "").getDisplayLanguage()) || langs.containsKey((Locale.ENGLISH).getDisplayLanguage()))) {
            Assert.fail("language map does not contain 'sv' or 'en");
        }
        if (langs.size() != itemsInList) {
            Assert.fail("Size of language map  = " + langs.size() + ", should be " + itemsInList);
        }
    }
}
