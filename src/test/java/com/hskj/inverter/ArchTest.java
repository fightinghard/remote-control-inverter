package com.hskj.inverter;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.hskj.inverter");

        noClasses()
            .that()
            .resideInAnyPackage("com.hskj.inverter.service..")
            .or()
            .resideInAnyPackage("com.hskj.inverter.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.hskj.inverter.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
