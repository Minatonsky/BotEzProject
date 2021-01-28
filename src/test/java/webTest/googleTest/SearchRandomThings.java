package webTest.googleTest;

import org.junit.Test;
import parentWebTest.ParentTest;

import static libs.Utils.waitABit;

public class SearchRandomThings extends ParentTest {

    @Test
    public void searchRandomThings() throws Exception {
        googlePage.openPage("google.com");

        waitABit(10);
    }
}
