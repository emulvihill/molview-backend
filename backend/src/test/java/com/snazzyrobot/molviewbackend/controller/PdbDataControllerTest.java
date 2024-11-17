package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.configuration.GraphQlConfiguration;
import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.service.PdbDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@GraphQlTest(PdbDataController.class)
@ContextConfiguration(classes = {PdbDataController.class, GraphQlConfiguration.class})
public class PdbDataControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private PdbDataService pdbDataService;

    @Test
    void testAllPdbData() {
        List<PdbData> mockPdbDataList = List.of(new PdbData(1, null, null, "Test1", "data1", "compound1", "sha1"),
                                                  new PdbData(2, null, null, "Test2", "data2", "compound2", "sha2"));
        when(pdbDataService.getAllPdbData()).thenReturn(mockPdbDataList);

        graphQlTester.document("{ allPdbData { id, name, data, compound, sha256 } }")
                .execute()
                .path("allPdbData")
                .matchesJson("""
                        [{ name: "Test1", data: "data1", compound: "compound1", "sha256":"sha1" },
                        { name: "Test2", data: "data2", compound: "compound2", "sha256":"sha2" }
                        ]""");
    }

    @Test
    void testCreatePdbData() {
        PdbData mockPdbData = new PdbData(1, null, null, "TestCreate", "dataCreate", "compoundCreate", "shaCreate");
        when(pdbDataService.createPdbData("TestCreate", "dataCreate", "compoundCreate")).thenReturn(mockPdbData);

        String mutation = """
                mutation { createPdbData(input: { name: "TestCreate", data: "dataCreate", compound: "compoundCreate" }) { id, name, data, compound, sha256 } }""";

        graphQlTester.document(mutation)
                .execute()
                .path("createPdbData")
                .matchesJson("""
                        { "id": "1", "name":"TestCreate", "data":"dataCreate", "compound":"compoundCreate" }""");
    }

    @Test
    void testFindPdbDataById() {
        PdbData mockPdbData = new PdbData(1, null, null, "TestFind", "dataFind", "compoundFind", "shaFind");
        when(pdbDataService.getPdbDataById(1)).thenReturn(Optional.of(mockPdbData));

        graphQlTester.document("{ findPdbDataById(id: 1) { id, name, data, compound, sha256 } }")
                .execute()
                .path("findPdbDataById")
                .matchesJson("""
                        { "id": "1", "name":"TestFind", "data":"dataFind", "compound":"compoundFind", "sha256":"shaFind" }""");
    }

    @Test
    void testDeletePdbData() {
        PdbData mockPdbData = new PdbData(1, null, null, "TestDelete", "dataDelete", "compoundDelete", "shaDelete");
        when(pdbDataService.deletePdbData(anyInt())).thenReturn(Optional.of(mockPdbData));

        String mutation = "mutation { deletePdbData(id: 1) { id, name, data, compound, sha256 } }";

        graphQlTester.document(mutation)
                .execute()
                .path("deletePdbData")
                .matchesJson("""
                        { "id": "1", "name":"TestDelete", "data":"dataDelete", "compound":"compoundDelete", "sha256":"shaDelete" }""");
    }
}