package com.snazzyrobot.molviewbackend.service;

import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.repository.PdbDataRepository;
import com.snazzyrobot.molviewbackend.utility.HashUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PdbDataServiceTest {

    @Mock
    private PdbDataRepository pdbDataRepository;

    @InjectMocks
    private PdbDataService pdbDataService;

    @Test
    void testCreatePdbData_Successful() {
        String name = "Test Name";
        String data = "Test Data";
        String compound = "Test Compound";
        String sha256 = HashUtil.calculateSHA256(data);

        PdbData mockPdbData =
                PdbData.builder()
                        .id(1L)
                        .created(OffsetDateTime.now())
                        .modified(OffsetDateTime.now())
                        .name(name)
                        .data(data)
                        .compound(compound)
                        .sha256(sha256)
                        .build();


        when(pdbDataRepository.save(any(PdbData.class))).thenReturn(mockPdbData);

        PdbData createdPdbData = pdbDataService.createPdbData(name, data, compound);

        assertNotNull(createdPdbData);
        assertEquals(name, createdPdbData.getName());
        assertEquals(data, createdPdbData.getData());
        assertEquals(compound, createdPdbData.getCompound());
        assertEquals(sha256, createdPdbData.getSha256());
    }

    @Test
    void testCreatePdbData_WithNullValues() {
        String name = null;
        String data = "Test Data";
        String compound = null;

        Exception exception = assertThrows(NullPointerException.class, () -> {
            pdbDataService.createPdbData(name, data, compound);
        });

        String expectedMessage = "is marked non-null but is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}