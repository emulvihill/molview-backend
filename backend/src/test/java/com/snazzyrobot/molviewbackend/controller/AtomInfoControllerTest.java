package com.snazzyrobot.molviewbackend.controller;

import com.snazzyrobot.molviewbackend.configuration.GraphQlConfiguration;
import com.snazzyrobot.molviewbackend.entity.PdbData;
import com.snazzyrobot.molviewbackend.service.AtomInfoService;
import com.snazzyrobot.molviewbackend.service.PdbDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.when;

@GraphQlTest(AtomInfoController.class)
@ContextConfiguration(classes = {AtomInfoController.class, GraphQlConfiguration.class})
public class AtomInfoControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private AtomInfoService atomInfoService;

    @MockBean
    private PdbDataService pdbDataService;

    @BeforeEach
    public void setUp() {
        when(atomInfoService.getAtomInfo(1L, 123L))
                .thenReturn("Sample Atom Info");
        when(pdbDataService.getPdbDataById(1L))
                .thenReturn(Optional.of(PdbData.builder().id(1L).data("Foo").name("foo").build()));
    }

    @Test
    public void testAtomInfoQuery() {
        String query = """
                query ($pdbId: ID!, $atomId: ID!) {
                    atomInfo(pdbId: $pdbId, atomId: $atomId)
                }
                """;

        graphQlTester.document(query)
                .variable("pdbId", 1L)
                .variable("atomId", 123L)
                .execute()
                .path("data.atomInfo")
                .matchesJson("\"Sample Atom Info\"");
    }
}