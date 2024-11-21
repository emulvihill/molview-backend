package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.entity.AtomInfo;
import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.repository.AtomInfoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

public class AtomInfoServiceTest {

    @Mock
    private AtomInfoRepository atomInfoRepository;

    @Mock
    private AIService aiService;

    @Mock
    private PdbDataService pdbDataService;

    @InjectMocks
    private AtomInfoService atomInfoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAtomInfoWithCachedData() {
        PdbData pdbData = PdbData.builder().id(1L).data("Foo").name("foo").build();

        long pdbId = 1L;
        long atomId = 123L;
        String expectedResponse = "Sample response from AIService";
        String expectedQuestion = "Give the chemistry properties of atom with id " + atomId + " in the following PDB formatted file:\n" + pdbData.getData();

        Mockito.when(aiService.askQuestion(expectedQuestion)).thenReturn(expectedResponse);

        when(pdbDataService.getPdbDataById(1L))
                .thenReturn(Optional.of(pdbData));

        when(atomInfoRepository.getDistinctByAtomIdAndPdbData(anyLong(), any()))
                .thenReturn(AtomInfo.builder().atomId(1L).info("Sample response from AIService").build());
        String actualResponse = atomInfoService.getAtomInfo(pdbId, atomId);

        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiService, never()).askQuestion(any());
    }

    @Test
    public void testGetAtomInfoNoData() {
        long pdbId = 1L;
        long atomId = 123L;
        PdbData pdbData = PdbData.builder().id(1L).data("Foo").name("foo").build();

        String expectedResponse = "Sample response from AIService";
        String expectedQuestion = "Give the chemistry properties of atom with id " + atomId + " in the following PDB formatted file:\n" + pdbData.getData();

        Mockito.when(aiService.askQuestion(expectedQuestion)).thenReturn(expectedResponse);

        when(pdbDataService.getPdbDataById(1L))
                .thenReturn(Optional.of(pdbData));

        when(atomInfoRepository.getDistinctByAtomIdAndPdbData(anyLong(), any()))
                .thenReturn(null);
        when(atomInfoRepository.save(any()))
                .thenReturn(AtomInfo.builder().id(1L).info(expectedResponse).build());
        String actualResponse = atomInfoService.getAtomInfo(pdbId, atomId);

        Assertions.assertEquals(expectedResponse, actualResponse);
        Mockito.verify(aiService).askQuestion(expectedQuestion);
    }
}