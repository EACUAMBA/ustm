import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.w3c.dom.Node;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.Objects.nonNull;

public class GeneratingAllPossibleUuids {

    private static  Node node = new Node();

    public static void main(String[] args) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        BigInteger two = BigInteger.valueOf(2);
        BigInteger hundredTwentyEight = BigInteger.valueOf(128);

        BigInteger twoPowHundredTwentyEightResult = two.pow(hundredTwentyEight.intValue());
        System.out.printf("2^128 => %d%n", twoPowHundredTwentyEightResult);

        boolean exists = Files.exists(Paths.get("F:\\count_till_ twoPowHundredTwentyEight.json"));
        if(exists){
            Files.move(Paths.get("F:\\count_till_ twoPowHundredTwentyEight.json"), Paths.get("F:\\count_till_ twoPowHundredTwentyEight - " + System.nanoTime() + ".json"));
        }
        Path file = Files.createFile(Paths.get("F:\\count_till_ twoPowHundredTwentyEight.json"));

        try (
                FileWriter fileWriter = new FileWriter(file.toFile().getAbsoluteFile(), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ) {
            bufferedWriter.append("[");
            for (BigInteger i = BigInteger.ONE; check(twoPowHundredTwentyEightResult, i); i = i.add(BigInteger.ONE)) {
                String randomUUID;
                boolean alreadyContains = true;
                do {
                    randomUUID = UUID.randomUUID().toString();
                    if (!node.contains(randomUUID)) {
                        alreadyContains = false;
                    }else {
                        bufferedWriter.newLine();
                        bufferedWriter.newLine();
                        bufferedWriter.append(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)).append(" UUID: ").append(randomUUID).append(" already contains in set.");
                    }
                    node = node.add(randomUUID);
                } while (alreadyContains);

                if(i.mod(BigInteger.valueOf(1_000)).equals(BigInteger.ZERO)){{
                    Node n = node;

                   while(nonNull(n)){

                   }
                    String writeValueAsString = jsonMapper.writeValueAsString(node);
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                    bufferedWriter.append(writeValueAsString);
                    bufferedWriter.flush();
                    System.out.println("i = " + i);

                    BigInteger index = node.getIndex();
                    Node node1 = new Node();
                    node1.setIndex(index.add(BigInteger.ONE));
                    node = node1;
                }}
            }
            bufferedWriter.append("]");
        } catch (IOException e) {
            System.out.println("Something happened writing into a file " + file.toFile().getAbsoluteFile());
            System.out.println("Error: " + e.getMessage());
            e.fillInStackTrace();
        }

    }

    public static boolean check(BigInteger maxIndex, BigInteger currentIndex) {
        int compareResult = currentIndex.compareTo(maxIndex);

        return compareResult <= 0;
    }



    static class Node implements Serializable {
        BigInteger index = BigInteger.ZERO;
        Object value;
        Node next;
        Node previus;

        public BigInteger getIndex() {
            return index;
        }

        public void setIndex(BigInteger index) {
            this.index = index;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getPrevius() {
            return previus;
        }

        public void setPrevius(Node previus) {
            this.previus = previus;
        }

        public synchronized Node add(Object value){
            if(Objects.isNull(this.value)){
                this.value = value;
                return this;
            }

            Node node = new Node();
            node.value = value;
            this.next = node;
            node.previus = this;
            node.index = this.index.add(BigInteger.ONE);
            return node;
        }

        public synchronized Boolean contains(Object value){
            Node node = this;

            while (nonNull(node)){
                if(Objects.equals(node.value, value)){
                    return true;
                }else{
                    node = node.previus;
                }
            }

            return false;
        }
    }


}
