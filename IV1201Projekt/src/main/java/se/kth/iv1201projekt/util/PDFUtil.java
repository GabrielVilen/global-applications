package se.kth.iv1201projekt.util;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import se.kth.iv1201projekt.integration.model.JobInterface;
import se.kth.iv1201projekt.integration.model.Person;

/**
 * A class for creation of PDF forms
 *
 * @author Samy
 */
public class PDFUtil {

    private static final PDRectangle PAGESIZE = PDPage.PAGE_SIZE_A4;
    private static final int NEWLINESPACE = -15;
    private static final PDFont HEADERFONT = PDType1Font.HELVETICA_BOLD;
    private static final int HEADERFONTSIZE = 18;
    private static final PDFont TEXTFONT = PDType1Font.COURIER;
    private static final int TEXTFONTSIZE = 14;

    /**
     * This method uses PDFbox from Apache to create a simple PDF form.
     * Adds info about the job and the applicant 
     * 
     * @param job
     * @param person
     * @return
     * @throws IOException
     * @throws COSVisitorException 
     */
    public static File createPDF(JobInterface job, Person person) throws IOException, COSVisitorException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PAGESIZE);
            float width = PAGESIZE.getWidth();
            float height = PAGESIZE.getHeight();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.beginText();
            contentStream.moveTextPositionByAmount(width - 9 * (width / 10), height - height / 7);

            //Job info
            contentStream.setFont(HEADERFONT, HEADERFONTSIZE);
            contentStream.drawString("Job application form");
            newLine(contentStream);
            contentStream.setFont(TEXTFONT, TEXTFONTSIZE);
            contentStream.drawString("Job name: " + job.getName());
            newLine(contentStream);
            contentStream.drawString("Job type: " + job.getType());
            newLine(contentStream);
            contentStream.drawString("Job information: " + job.getInformation());
            newLine(contentStream);
            contentStream.drawString("Start Date: " + job.getStartDate());
            newLine(contentStream);
            contentStream.drawString("End Date: " + job.getEndDate());

            //Applicant Info
            newLine(contentStream, 3);
            contentStream.setFont(HEADERFONT,HEADERFONTSIZE);
            contentStream.drawString("Applicant Information");
            newLine(contentStream);
            contentStream.setFont(TEXTFONT, TEXTFONTSIZE);
            contentStream.drawString("Name: " + person.getName() + " " + person.getSurname());
            newLine(contentStream);
            contentStream.drawString("Email: " + person.getEmail());
            newLine(contentStream);
            contentStream.drawString("SSN: " + person.getSsn());
            newLine(contentStream);
            contentStream.endText();
            contentStream.close();

            File file = File.createTempFile("JobApplication", ".pdf").getAbsoluteFile();
            document.save(file);
            return file;
        }
    }

    private static void newLine(PDPageContentStream content) throws IOException {
        content.moveTextPositionByAmount(0, NEWLINESPACE);
    }

    private static void newLine(PDPageContentStream content, int multiplier) throws IOException {
        content.moveTextPositionByAmount(0, NEWLINESPACE * multiplier);
    }
}
