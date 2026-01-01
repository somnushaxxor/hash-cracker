package ru.nsu.fit.kolesnik.hashcracker.worker.core.service;

import lombok.RequiredArgsConstructor;
import org.paukov.combinatorics3.Generator;
import org.springframework.stereotype.Service;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.model.TaskPart;
import ru.nsu.fit.kolesnik.hashcracker.worker.core.util.MD5HashGenerator;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskPartResultResolverImpl implements TaskPartResultResolver {
    private final MD5HashGenerator md5HashGenerator;

    @Override
    public List<String> resolveTaskPartResult(TaskPart taskPart) {
        List<String> resultWords = new ArrayList<>();
        List<String> alphabet = taskPart.getAlphabet().toStringList();
        for (int length = 1; length <= taskPart.getMaxLength(); length++) {
            int possibleWordsNumber = (int) Math.pow(alphabet.size(), length);
            int start = start(taskPart.getIndex(), taskPart.getPartsNumber(), possibleWordsNumber);
            int partWordsNumber = partWordsNumber(taskPart.getIndex(), taskPart.getPartsNumber(), possibleWordsNumber);
            resultWords.addAll(
                    Generator.permutation(alphabet)
                            .withRepetitions(length)
                            .stream()
                            .skip(start)
                            .limit(partWordsNumber)
                            .map(word -> String.join("", word))
                            .filter(
                                    word -> {
                                        String currentWordHash = md5HashGenerator.generateHashFrom(word);
                                        if (taskPart.getHash().equals(currentWordHash)) {
                                            return true;
                                        }
                                        return false;
                                    })
                            .toList()
            );
        }
        return resultWords;
    }

    private int start(int partIndex, int partsNumber, int wordsNumber) {
        return partIndex * (int) Math.ceil((double) wordsNumber / partsNumber);
    }

    private int partWordsNumber(int partIndex, int partsNumber, int wordsNumber) {
        int numberProPart = (int) Math.ceil((double) wordsNumber / partsNumber);
        if (wordsNumber < numberProPart * (partIndex + 1)) {
            return wordsNumber - numberProPart * partIndex;
        }
        return numberProPart;
    }
}
