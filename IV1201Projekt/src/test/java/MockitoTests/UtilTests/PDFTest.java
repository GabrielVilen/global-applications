package MockitoTests.UtilTests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.integration.model.JobInterface;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.LoggerUtil;
import se.kth.iv1201projekt.util.PDFUtil;

/**
 * Tests PDF generation and logger utility
 *
 * @author Samy
 */
@RunWith(MockitoJUnitRunner.class)
public class PDFTest {

    private JobInterface job;
    private Person person;
    
    /**
     * Initializes up the test job and person
     * @throws Exception 
     */
    @Before
    public void setUp(){
        job = new Job(1, "Test type", "Test Job information", new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis()), "Test job name", 0);
        person = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
    }
    
    /**
     * Generates PDF with different parameters for job and person.
     * Tries to parse the pdf and finally after creating a checks file attributes
     */
    @Test
    public void testPdfGenerate() {

        try {
            //both not set
            parsePDF(null, null);
            Assert.fail(); //Will only come here if parsePDf doesn't throw exception
        } catch (NullPointerException | IOException | COSVisitorException ex) {
            //success
        }

        try {
            //person not set
            parsePDF(job, null);
            Assert.fail();
        } catch (NullPointerException | IOException | COSVisitorException ex) {
            //success
        }

        try {
            //job not set
            parsePDF(null, person);
            Assert.fail();
        } catch (NullPointerException | IOException | COSVisitorException ex) {
            //success
        }
        File file = null;
        try {
            //both set
            file = parsePDF(job, person);
        } catch (NullPointerException | IOException | COSVisitorException ex) {
            LoggerUtil.logTest(ex, this);
            Assert.fail();
        }
        Assert.assertNotNull(file);
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file.canExecute());
        Assert.assertTrue(file.canRead());
        Assert.assertTrue(file.isFile());
        if (file.getTotalSpace() <= 0) {
            Assert.fail("Size of PDF is less or equal to 0");
        }

    }

    private File parsePDF(JobInterface job, Person person) throws IOException, COSVisitorException {
        File file = PDFUtil.createPDF(job, person);
        file.deleteOnExit(); //To not fill the hosts memory with rubbish
        PDFParser parser = new PDFParser(new FileInputStream(file));
        parser.parse();
        return file;
    }

}