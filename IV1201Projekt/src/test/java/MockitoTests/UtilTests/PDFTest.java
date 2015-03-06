package MockitoTests.UtilTests;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import se.kth.iv1201projekt.integration.model.Job;
import se.kth.iv1201projekt.integration.model.Person;
import se.kth.iv1201projekt.util.PDFUtil;

/**
 * Tests the PDF file generator.
 *
 * @author Gabriel
 */
@RunWith(MockitoJUnitRunner.class)
public class PDFTest {

    @Test
    public void testPDF() {
        Person person = new Person(1l, "Per", "Strand", "19671212-1211", "per@strand.kth.se", 1);
        Job job = new Job(1, "programmer", "test", new Date(), new Date(), "test", 1);

        File file = null;
        try {
            file = PDFUtil.createPDF(job, person);
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
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

}
