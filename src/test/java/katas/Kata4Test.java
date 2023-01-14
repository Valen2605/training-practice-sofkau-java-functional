package katas;
//import org.slf4j.LoggerFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.equalTo;


public class Kata4Test {
     //private static  final Logger log = LoggerFactory.getLogger(Kata4Test.class);
    @Test
    public void testExecute() {
        Assert.assertThat(Kata4.execute().size(), equalTo(4));

    }
}
