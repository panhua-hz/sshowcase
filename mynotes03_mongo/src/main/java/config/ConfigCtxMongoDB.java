package config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import repository.DocNoteRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses={DocNoteRepository.class})
public class ConfigCtxMongoDB extends AbstractMongoConfiguration{

	@Override
	protected String getDatabaseName() {
		return "mydb1";
	}

	@Override
	public Mongo mongo() throws Exception {
		//MongoCredential credential = MongoCredential.createMongoCRCredential("andrew", "mydb1", "aaa".toCharArray());
		//return new MongoClient(new ServerAddress("192.168.0.106",27017), Arrays.asList(credential));
		
		return new MongoClient("192.168.0.106");
	}
}
