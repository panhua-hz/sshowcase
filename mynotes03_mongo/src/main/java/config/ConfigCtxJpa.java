package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repository.NoteRepository;
import repository.TagRepository;

@Configuration
@Import(ConfigCtxDatabase.class)
@EnableJpaRepositories(basePackageClasses={NoteRepository.class, TagRepository.class})
public class ConfigCtxJpa {

}
