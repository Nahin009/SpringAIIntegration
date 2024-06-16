package com.example.demo.service;

import com.assemblyai.api.AssemblyAI;
import com.assemblyai.api.resources.transcripts.types.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AudioService {

    private final String ASSEMBLY_AI_API_KEY;

    public AudioService(@Qualifier("getASSEMBLY_AI_API_KEY") String ASSEMBLY_AI_API_KEY) {
        this.ASSEMBLY_AI_API_KEY = ASSEMBLY_AI_API_KEY;
    }


    public Optional<String> createTranscript() {
        System.out.println("API Key: " + ASSEMBLY_AI_API_KEY);

        AssemblyAI client = AssemblyAI.builder()
                .apiKey(ASSEMBLY_AI_API_KEY)
                .build();

        String TestAudioUrl = "https://storage.googleapis.com/aai-web-samples/5_common_sports_injuries.mp3";

        var params = TranscriptOptionalParams.builder()
                .speakerLabels(true)
                .build();

        Transcript transcript = client.transcripts().transcribe(TestAudioUrl, params);

        System.out.println(transcript.getText());

        transcript.getUtterances().ifPresent(utterances ->
                utterances.forEach(utterance ->
                        System.out.println("Speaker " + utterance.getSpeaker() + ": " + utterance.getText())
                )
        );

        return transcript.getText();
    }
}
