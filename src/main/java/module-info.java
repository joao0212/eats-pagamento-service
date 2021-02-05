module br.com.caelum.eats.pagamento {
	requires java.persistence;
	requires java.validation;
	requires spring.beans;
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires spring.core;
	requires spring.data.commons;
	requires spring.data.jpa;
	requires spring.web;
	requires spring.cloud.openfeign.core;
	requires spring.cloud.netflix.eureka.client;
	requires spring.amqp;
	requires spring.rabbit;
}