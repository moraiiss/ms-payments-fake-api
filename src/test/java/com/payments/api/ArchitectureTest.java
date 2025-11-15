package com.payments.api;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.DisplayName;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@DisplayName("Architecture Tests")
@AnalyzeClasses(packages = "com.payments.api")
public class ArchitectureTest {

    @ArchTest
    static final ArchRule controllers_should_be_suffixed = classes()
        .that()
        .resideInAPackage("..controller..")
        .and().areAnnotatedWith(RestController.class)
        .should()
        .haveSimpleNameEndingWith("Controller")
        .allowEmptyShould(true);

    @ArchTest
    static final ArchRule usecase_should_be_suffixed = classes()
        .that()
        .resideInAPackage("..usecase..")
        .and().areAnnotatedWith(Service.class)
        .should()
        .haveSimpleNameEndingWith("UseCase")
        .allowEmptyShould(true);

    @ArchTest
    static final ArchRule repository_should_be_suffixed = classes()
        .that()
        .resideInAPackage("..repository..")
        .and().areAnnotatedWith(Repository.class)
        .should()
        .haveSimpleNameEndingWith("Repository")
        .allowEmptyShould(true);

    @ArchTest
    static final ArchRule layer_dependencies_should_be_respected = layeredArchitecture()
        .consideringAllDependencies()

        .layer("Controllers").definedBy("com.payments.api.controller..")
        .layer("UseCases").definedBy("com.payments.api.usecase..")
        .layer("Repository").definedBy("com.payments.api.repository..")
        .layer("Services").definedBy("com.payments.api.core.service..")

        .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
        .whereLayer("UseCases").mayOnlyBeAccessedByLayers("Controllers")
        .whereLayer("Repository").mayOnlyBeAccessedByLayers("UseCases", "Services");
}
