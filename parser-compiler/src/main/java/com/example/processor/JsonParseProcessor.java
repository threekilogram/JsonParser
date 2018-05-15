package com.example.processor;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author wuxio
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes({"com.example.annotation.GenerateParser"})
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class JsonParseProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set< ? extends TypeElement > annotations, RoundEnvironment roundEnv) {

        return false;
    }
}
