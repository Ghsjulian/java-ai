package com.my.ghs;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import java.util.Locale;



public class Speech implements OnInitListener {
	private TextToSpeech tts;
	
	public void speak(String text) {
		tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}
	public void shutdown() {
		if (tts != null) {
			tts.shutdown();
		}
	}
	public void init(Context context) {
		tts = new TextToSpeech(context, this);
	}
	
	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = tts.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
				// Handle error
			}
			} else {
			// Handle error
		}
	}
}



/*
import android.content.Context;
import android.speech.tts.TextToSpeech;

public class Speech {
	private TextToSpeech textToSpeech;

	public Speech(Context context) {
		textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) {
				if (status == TextToSpeech.SUCCESS) {
					// Set the language and voice here
					int result = textToSpeech.setLanguage(Locale.US);
					if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
						// Handle language not supported
					}
				} else {
					// Handle initialization failed
				}
			}
		});
	}

	public void speak(String text) {
		if (textToSpeech != null) {
			textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
		}
	}

	public void shutdown() {
		if (textToSpeech != null) {
			textToSpeech.shutdown();
		}
	}
}
*/