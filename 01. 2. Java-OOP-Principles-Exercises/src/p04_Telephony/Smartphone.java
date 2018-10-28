package p04_Telephony;

public class Smartphone implements Callable, Browsable {

    public Smartphone() {
    }


    @Override
    public void browse(String site) {
        System.out.printf("Browsing: %s!%n", site);
    }

    @Override
    public void calling(String number) {
        System.out.printf("Calling... %s%n", number);
    }
}
