package ru.nsu.fit.kolesnik.hashcracker.manager.core.service;

import org.springframework.stereotype.Service;

@Service
public class SingleWorkerTaskPartsNumberResolver implements TaskPartsNumberResolver {
    @Override
    public int resolveTaskPartsNumber() {
        return 1;
    }
}
