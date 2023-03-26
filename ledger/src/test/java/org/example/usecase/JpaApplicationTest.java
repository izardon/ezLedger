package org.example.usecase;

import org.example.application.springboot.web.EzLedgerWebMain;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@ComponentScan(basePackages={"org.example"}, excludeFilters= {
        @ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, value=EzLedgerWebMain.class)})
@EntityScan(basePackages={"org.example"})
@SpringBootApplication
public abstract class JpaApplicationTest {
}

