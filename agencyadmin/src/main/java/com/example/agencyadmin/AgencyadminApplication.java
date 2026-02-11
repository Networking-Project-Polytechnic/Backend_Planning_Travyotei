package com.example.agencyadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication(exclude = {
		ElasticsearchRestClientAutoConfiguration.class,
		ElasticsearchDataAutoConfiguration.class,
		ElasticsearchRepositoriesAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "com.example.agencyadmin.Repositories")
@EnableElasticsearchRepositories(basePackages = "com.example.agencyadmin.Elasticsearch")
public class AgencyadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgencyadminApplication.class, args);
	}

}
