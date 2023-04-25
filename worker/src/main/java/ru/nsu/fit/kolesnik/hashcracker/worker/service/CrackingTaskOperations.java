package ru.nsu.fit.kolesnik.hashcracker.worker.service;

import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import ru.nsu.fit.kolesnik.hashcracker.schema.manager.request.CrackingTaskManagerRequest;
import ru.nsu.fit.kolesnik.hashcracker.worker.utils.MD5HashGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.paukov.combinatorics.CombinatoricsFactory.createPermutationWithRepetitionGenerator;
import static org.paukov.combinatorics.CombinatoricsFactory.createVector;

public final class CrackingTaskOperations {

    private CrackingTaskOperations() {
    }

    public static List<String> executeTask(CrackingTaskManagerRequest managerRequest) {
        final BigDecimal allPossibleWordsNumber = countNumberOfAllPossibleWords(managerRequest.getAlphabet(),
                managerRequest.getMaxLength());
        final BigDecimal wordsProPart = countNumberOfWordsProPart(allPossibleWordsNumber, managerRequest.getPartsNumber());
        final ICombinatoricsVector<String> alphabetVector = createVector(managerRequest.getAlphabet().getSymbols());
        BigInteger wordsCounter = BigInteger.ZERO;
        int currentWordLength = 1;
        Generator<String> generator = createPermutationWithRepetitionGenerator(alphabetVector, currentWordLength);
        Iterator<ICombinatoricsVector<String>> iterator = generator.iterator();
        while (wordsCounter.compareTo(wordsProPart.multiply(BigDecimal
                .valueOf(managerRequest.getPartIndex())).toBigInteger()) < 0) {
            if (iterator.hasNext()) {
                wordsCounter = wordsCounter.add(BigInteger.ONE);
                iterator.next();
            } else {
                currentWordLength++;
                generator = createPermutationWithRepetitionGenerator(alphabetVector, currentWordLength);
                iterator = generator.iterator();
            }
        }
        BigInteger currentPartWordsCounter = BigInteger.ZERO;
        final StringBuilder stringBuilder = new StringBuilder();
        final List<String> suitableWords = new ArrayList<>();
        while (wordsCounter.compareTo(allPossibleWordsNumber.toBigInteger()) < 0 && currentPartWordsCounter
                .compareTo(wordsProPart.toBigInteger()) < 0) {
            if (iterator.hasNext()) {
                final ICombinatoricsVector<String> vector = iterator.next();
                final String currentWord = getWordFrom(vector, stringBuilder);
                if (managerRequest.getHash().equals(MD5HashGenerator
                        .generateHashFrom(currentWord))) {
                    suitableWords.add(currentWord);
                }
                stringBuilder.setLength(0);
                wordsCounter = wordsCounter.add(BigInteger.ONE);
                currentPartWordsCounter = currentPartWordsCounter.add(BigInteger.ONE);
            } else {
                currentWordLength++;
                generator = createPermutationWithRepetitionGenerator(alphabetVector, currentWordLength);
                iterator = generator.iterator();
            }
        }
        return suitableWords;

    }

    private static BigDecimal countNumberOfAllPossibleWords(CrackingTaskManagerRequest.Alphabet alphabet, int maxWordLength) {
        BigDecimal allPossibleWordsNumber = BigDecimal.ZERO;
        BigDecimal currentLengthPossibleWordsNumber = BigDecimal.ONE;
        for (int i = 0; i < maxWordLength; i++) {
            currentLengthPossibleWordsNumber = currentLengthPossibleWordsNumber.multiply(BigDecimal
                    .valueOf(alphabet.getSymbols().size()));
            allPossibleWordsNumber = allPossibleWordsNumber.add(currentLengthPossibleWordsNumber);
        }
        return allPossibleWordsNumber;
    }

    private static BigDecimal countNumberOfWordsProPart(BigDecimal allPossibleWordsNumber, int partCount) {
        return allPossibleWordsNumber.divide(BigDecimal.valueOf(partCount), RoundingMode.CEILING);
    }

    private static String getWordFrom(ICombinatoricsVector<String> vector, StringBuilder stringBuilder) {
        for (String vectorElem : vector) {
            stringBuilder.append(vectorElem);
        }
        return stringBuilder.toString();
    }

}
