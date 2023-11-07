package lotto;

public class Buying {
    private Integer price;

    public Buying(String readLine) {
        Integer price = validate(readLine);
        this.price = price;
    }

    public Integer getPrice() {
        return this.price;
    }

    public Integer getBuyingCount() {
        return this.price / 1000;
    }

    private Integer validate(String readLine) {
        String refinedReadLine = removeEmpty(readLine);
        checkDigit(refinedReadLine);
        Integer price = translateToPrice(refinedReadLine);
        checkBoundary(price);
        checkRest(price);
        return price;
    }

    private String removeEmpty(String readLine) {
        return readLine.replaceAll("\\s", "");
    }

    private void checkDigit(String readLine) {
        if (readLine.length() > 6) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 6자리 이하여야 합니다.");
        }
    }

    private void checkBoundary(Integer price) {
        if (price < 1000 || price > 100000) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 이상, 100000원 이하여야 합니다.");
        }
    }

    private void checkRest(Integer price) {
        if (price % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    private Integer translateToPrice(String refinedReadLine) {
        try {
            return Integer.parseInt(refinedReadLine);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자만 입력되어야 합니다.");
        }
    }
}
