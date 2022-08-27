package utilities;


import objects.Member;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    @DataProvider(name = "getMembers")
    public Object[] getMembers() throws IOException {
        return JacksonUtils.deSerializationJSON("members.json", Member[].class);
    }

    /**
     * format date/time
     *
     * @return - String
     */
    public static String formatTimeSDF() {
        return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
    }

}
