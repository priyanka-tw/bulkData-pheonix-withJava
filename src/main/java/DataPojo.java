import org.apache.commons.math3.random.RandomDataGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

public class DataPojo {

    private Long accountKey;
    private String accountType;
    private Long accountNumber;
    private String assetClassL1;
    private String assetClassL2;
    private String accountGroupId;
    private Double balance;
    private String currency;
    private LocalDate date;

    private static final String[] ACCOUNT_TYPE_ARRAY = {"SAVING", "SAVING_PLUS", "CURRENT"};
    private static final String[] ASSET_CLASS_ARRAY = {"CASH", "EQUITY"};
    private static final String[] ASSET_CLASS_LTWO_ARRAY = {"CASH_EQUIVALANT", "INVESTMENT"};
    private static final String[] ACCOUNT_GROUP_ARRAY = {"111", "222", "333", "444", "555", "666"};
    private static final String[] CURRENCY_ARRAY = {"USD", "CAD", "INR"};

    public DataPojo() {

        this.accountKey = generateRandomLong();
        this.accountType = getRandomElement(ACCOUNT_TYPE_ARRAY);
        this.accountNumber = generateRandomLong();
        this.assetClassL1 = getRandomElement(ASSET_CLASS_ARRAY);
        this.assetClassL2 = getRandomElement(ASSET_CLASS_LTWO_ARRAY);
        this.accountGroupId = getRandomElement(ACCOUNT_GROUP_ARRAY);
        this.balance = generateRandomDouble();
        this.currency = getRandomElement(CURRENCY_ARRAY);
        this.date = generateRandomDate();
    }

    @Override
    public String toString() {
        return accountKey + "," +
                "\'" + accountType + "\'" + "," +
                accountNumber + "," +
                "\'" + assetClassL1 + "\'" + "," +
                "\'" + assetClassL2 + "\'" + "," +
                "\'" + accountGroupId + "\'" + "," +
                balance + "," +
                "\'" + currency + "\'" + "," +
                "\'" + date + "\'";
    }

    private Long generateRandomLong() {
        return new RandomDataGenerator().nextLong(20000000L, 40000000L);
    }

    private Double generateRandomDouble() {
        return new Random().nextDouble();
    }

    private String getRandomElement(String[] array) {
        return array[new RandomDataGenerator().nextInt(0, (array.length - 1))];
    }

    private LocalDate generateRandomDate() {

        Random random = new Random();
        int minDay = (int) LocalDate.of(1950, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2018, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}
