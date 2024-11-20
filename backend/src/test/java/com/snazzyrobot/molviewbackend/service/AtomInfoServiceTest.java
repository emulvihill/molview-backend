package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.repository.AtomInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AtomInfoServiceTest {

    @Mock
    private AtomInfoRepository atomInfoRepository;

    @Mock
    private AIService aiService;

    @InjectMocks
    private AtomInfoService atomInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAtomInfo() {
        long pdbId = 1L;
        long atomId = 123L;
        String expectedResponse = "Sample response from AIService";
        String expectedQuestion = "Give the chemistry properties of atom with id " + atomId + " in the following PDB Id:\n" + pdbId;

        Mockito.when(aiService.askQuestion(expectedQuestion)).thenReturn(expectedResponse);

        String actualResponse = atomInfoService.getAtomInfo(pdbId, atomId);

        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiService).askQuestion(expectedQuestion);
    }
}