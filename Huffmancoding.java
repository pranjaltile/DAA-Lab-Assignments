import java.util.*;


class HuffmanNode {
    char data;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
        left = right = null;
    }
}


class HuffmanComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode n1, HuffmanNode n2) {
        return Integer.compare(n1.frequency, n2.frequency);
    }
}

public class Huffmancoding {

 
    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(new HuffmanComparator());

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            priorityQueue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode node = new HuffmanNode('\0', left.frequency + right.frequency);
            node.left = left;
            node.right = right;
            priorityQueue.add(node);
        }

        return priorityQueue.poll();
    }


    private static void generateHuffmanCodes(HuffmanNode root, StringBuilder prefix, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.data, prefix.toString());
        }

        prefix.append('0');
        generateHuffmanCodes(root.left, prefix, huffmanCodes);
        prefix.deleteCharAt(prefix.length() - 1);

        prefix.append('1');
        generateHuffmanCodes(root.right, prefix, huffmanCodes);
        prefix.deleteCharAt(prefix.length() - 1);
    }


    private static double calculateAverageCodeLength(Map<Character, String> huffmanCodes, Map<Character, Integer> freqMap) {
        double totalLength = 0;
        int totalFrequency = 0;

        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            char character = entry.getKey();
            String code = entry.getValue();
            int frequency = freqMap.get(character);

            totalLength += code.length() * frequency;
            totalFrequency += frequency;
        }

        return totalLength / totalFrequency;
    }

    private static int calculateTotalLength(Map<Character, String> huffmanCodes, String text) {
        int totalLength = 0;
        for (char c : text.toCharArray()) {
            totalLength += huffmanCodes.get(c).length();
        }
        return totalLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of characters:");
        int numChars = scanner.nextInt();
        scanner.nextLine();  

        Map<Character, Integer> freqMap = new HashMap<>();
        System.out.println("Enter the characters and their frequencies:");

        for (int i = 0; i < numChars; i++) {
            System.out.println("Enter character and frequency (e.g., A 5):");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            char character = parts[0].charAt(0);
            int frequency = Integer.parseInt(parts[1]);
            freqMap.put(character, frequency);
        }

        HuffmanNode root = buildHuffmanTree(freqMap);

        Map<Character, String> huffmanCodes = new HashMap<>();
        generateHuffmanCodes(root, new StringBuilder(), huffmanCodes);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        double averageCodeLength = calculateAverageCodeLength(huffmanCodes, freqMap);
        System.out.println("Average Code Length: " + averageCodeLength);

        StringBuilder originalText = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                originalText.append(entry.getKey());
            }
        }

        int totalLength = calculateTotalLength(huffmanCodes, originalText.toString());
        System.out.println("Total Length of Encoded Text: " + totalLength);
    }
}

