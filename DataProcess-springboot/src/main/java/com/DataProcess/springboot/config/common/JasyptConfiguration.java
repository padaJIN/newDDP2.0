//package com.DataProcess.springboot.config.common;
//
//import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
//import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySources;
//import org.jasypt.encryption.StringEncryptor;
//import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
//import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@EncryptablePropertySources({
//        @EncryptablePropertySource("classpath:application.yml"),
//})
//public class JasyptConfiguration {
//    @Bean
//    @Qualifier("jasyptStringEncryptor")
//    public StringEncryptor stringEncryptor() {
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword("RaND0m$tr0nG_KeY_2022#Jasypt");
////        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
////        config.setKeyObtentionIterations("1000");
////        config.setPoolSize("1");
////        config.setProviderName("SunJCE");
////        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
////        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
////        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
//        return encryptor;
//    }
//}
