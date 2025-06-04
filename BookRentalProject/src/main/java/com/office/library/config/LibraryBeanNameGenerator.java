package com.office.library.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;

public class LibraryBeanNameGenerator implements BeanNameGenerator {
	
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		//Spring 애플리케이션의 애플리케이션 컨텍스트에 정의된 이 Spring Bean의 클래스 이름 반환
		return definition.getBeanClassName();
	}

}
