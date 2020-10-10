package Controller;

import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.translation.SpeechTranslationConfig;

public class MicrosoftSpeechAPI {
    public static void Read(String text, String from) {
        try {
            String speechSubscriptionKey = "87974ecd4d94432ead0761490c4bd673";
            String serviceRegion = "eastasia";
            int exitCode = 1;
            SpeechTranslationConfig config = SpeechTranslationConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            config.setSpeechSynthesisLanguage(from); // vi-VN: vietnamses, en-US: english
            SpeechSynthesizer synth = new SpeechSynthesizer(config);
            synth.SpeakText(text);
            synth.close();
        } catch (Exception e) {
        }
    }
}