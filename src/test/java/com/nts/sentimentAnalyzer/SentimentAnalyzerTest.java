package com.nts.sentimentAnalyzer;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import org.ejml.simple.SimpleMatrix;
import org.junit.Test;
import java.util.Properties;

public class SentimentAnalyzerTest {

    @Test
    public void test() {

        String text = "Seeing your face makes me reconsider living on this planet.";
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        if (text != null && text.length() > 0) {

            // run all Annotators on the text
            Annotation annotation = pipeline.process(text);

            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                // this is the parse tree of the current sentence
                Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
                SimpleMatrix sm = RNNCoreAnnotations.getPredictions(tree);
                String sentimentType = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

                System.out.println("setVeryPositive"+(double)Math.round(sm.get(4) * 100d));
                System.out.println("setPositive"+(double)Math.round(sm.get(3) * 100d));
                        System.out.println("setNeutral"+(double)Math.round(sm.get(2) * 100d));
                                System.out.println("setNegative"+(double)Math.round(sm.get(1) * 100d));
                                        System.out.println("setVeryNegative"+(double)Math.round(sm.get(0) * 100d));
                System.out.println(RNNCoreAnnotations.getPredictedClass(tree));
                System.out.println(sentimentType);
            }

        }

    }
}
