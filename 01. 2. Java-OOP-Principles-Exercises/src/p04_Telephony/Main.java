package p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] numbers = reader.readLine().split("\\s+");
        String[] sites = reader.readLine().split("\\s+");

        Pattern patternNumbers = Pattern.compile("^\\d+$");
        Pattern patternSites = Pattern.compile("^(\\D*)$");

        Smartphone phone = new Smartphone();

        for (String number : numbers) {
            Matcher matcherNums = patternNumbers.matcher(number);
            if (matcherNums.find()) {
                phone.calling(number);
            } else {
                System.out.println("Invalid number!");
            }
        }

        for (String site : sites) {
            Matcher matcherSites = patternSites.matcher(site);
            if (matcherSites.find()) {
                phone.browse(site);
            } else {
                System.out.println("Invalid URL!");
            }
        }

    }
}
