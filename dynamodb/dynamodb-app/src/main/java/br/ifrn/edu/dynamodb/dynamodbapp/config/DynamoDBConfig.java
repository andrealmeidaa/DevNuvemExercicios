package br.ifrn.edu.dynamodb.dynamodbapp.config;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
@EnableDynamoDBRepositories(basePackages="br.ifrn.edu.dynamodb.dynamodbapp.repository")
public class DynamoDBConfig{
    @Bean
    public AmazonDynamoDB amazonDynamoDB(){
        AmazonDynamoDB amazonDynamoDB=AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
        return amazonDynamoDB;
    }
    @Bean
    @Primary
    public DynamoDBMapperConfig dynamoDBMapperConfig(){
        return DynamoDBMapperConfig.DEFAULT;
    }
   @Bean
   @Primary
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,DynamoDBMapperConfig config){
        return new DynamoDBMapper(amazonDynamoDB, config);
    }
   

}
