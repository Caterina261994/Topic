package Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Topic {
    private String name;
    private int quantityVariants;
    private List<String> variants = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<String> getVariants() {
        return variants;
    }

    public Topic topicMaker(BufferedReader bufferedReader) throws IOException {

        System.out.println("Введите вопрос");
        name = bufferedReader.readLine();
        System.out.println("Введите количество вариантов ответа");
        try {
            quantityVariants = Integer.parseInt(bufferedReader.readLine());
        }catch ( NumberFormatException e){
            System.out.println("Incorrect input(it should be numeric for instance '1,2...55' Please try again)");
            return topicMaker(bufferedReader);
        }
        System.out.println("Введите варианты ответов");
        int i = 0;
        while (i < quantityVariants) {
            String variant = bufferedReader.readLine();
            variants.add(variant);
            i++;
        }
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityVariants() {
        return quantityVariants;
    }

    public void setQuantityVariants(int quantityVariants) {
        this.quantityVariants = quantityVariants;
    }

    public void setVariants(List<String> variants) {
        this.variants = variants;
    }

    @Override
    public String toString() {
        return name + ":\n" + String.join("\n", variants);
    }
}
