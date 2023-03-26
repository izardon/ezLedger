package org.example.application.springboot.web;

import org.example.model.DomainEventBus;
import org.example.usecase.eventHandler.NotifyLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"} )
public class EzLedgerWebMain implements CommandLineRunner {

    private DomainEventBus domainEventBus;

    private NotifyLedger notifyLedger;

    @Autowired
    public void setDomainEventBus(DomainEventBus domainEventBus, NotifyLedger notifyLedger) {
        this.domainEventBus = domainEventBus;
        this.notifyLedger = notifyLedger;
    }

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(EzLedgerWebMain.class) ;
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        System.out.println("ezChargeWebMain run");

        domainEventBus.register(notifyLedger);
    }
}
