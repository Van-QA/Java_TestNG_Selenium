package utilities;


import constants.GlobalVars;
import objects.Member;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    @DataProvider(name = "getMembers")
    public Object[] getMembers() throws IOException {
        return JacksonUtils.deSerializationJSON(GlobalVars.getJSONMembers(), Member[].class);
    }

    /**
     * format date/time
     *
     * @return - String
     */
    public static String formatTimeSDF() {
        return new SimpleDateFormat(GlobalVars.getDateTimeFormat1()).format(new Date());
    }

}
