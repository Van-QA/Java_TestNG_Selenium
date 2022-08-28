package constants;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalVars {

    @Getter
    final String userDir = System.getProperty("user.dir");
    @Getter
    final String osPlatform = System.getProperty("os.name");

    @Getter
    final String resPath = userDir.concat("/src/test/resources");
    @Getter
    final String configProp = resPath.concat("/Config.properties");
    @Getter
    final String dataFilePath = resPath.concat("/data.properties");
    @Getter
    final String allureResultsPath = userDir.concat("/allure-results/");
    @Getter
    final String JSONDataDir = "JSONdata";
    @Getter
    final String JSONMembers = JSONDataDir.concat("/members.json");
    @Getter
    final int maxLength = 50;
    @Getter
    final String dateTimeFormat1 = "dd_MM_hh_mm";
    @Getter
    final String dateTimeFormat2 = "dd/MM/yyyy HH:mm:ss";

}
