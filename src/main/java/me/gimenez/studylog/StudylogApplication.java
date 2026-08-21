package me.gimenez.studylog;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

@SpringBootApplication
public class StudylogApplication {
    @Bean
    ChatClient chatClient (
            ChatClient.Builder builder,
            @Value("classpath:/prompt/system-prompt.st") Resource systemPrompt
    ) {
        return builder.defaultSystem(systemPrompt).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(StudylogApplication.class, args);
    }

}
