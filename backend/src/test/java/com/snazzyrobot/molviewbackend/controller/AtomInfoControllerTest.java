package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.configuration.GraphQlConfiguration;
import com.snazzyrobot.molviewbackend.service.AtomInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.when;

@GraphQlTest(AtomInfoController.class)
@ContextConfiguration(classes = {AtomInfoController.class, GraphQlConfiguration.class})
public class AtomInfoControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private AtomInfoService atomInfoService;

    @BeforeEach
    public void setUp() {
        when(atomInfoService.getAtomInfo("example.pdb", 123))
                .thenReturn("Sample Atom Info");
    }

    @Test
    public void testAtomInfoQuery() {
        String query = """
            query ($pdbFile: String!, $atomId: Int!) {
                atomInfo(pdbFile: $pdbFile, atomId: $atomId)
            }
            """;

        graphQlTester.document(query)
                .variable("pdbFile", "example.pdb")
                .variable("atomId", 123)
                .execute()
                .path("data.atomInfo")
                .matchesJson("\"Sample Atom Info\"");
    }
}