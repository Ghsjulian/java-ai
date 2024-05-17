package com.my.ghs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;
import android.content.Context;

public class Myvoice extends Service {
	private SpeechRecognizer speechRecognizer;
	private Speech ttsActivity;
	private static final String CHANNEL_ID = "my_channel_01";

	@Override
	public void onCreate() {
		super.onCreate();
		speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
		ttsActivity = new Speech();
		ttsActivity.init(this);
		speechRecognizer.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle params) {
				// Called when the speech recognizer is ready to start listening
			}

			@Override
			public void onBeginningOfSpeech() {
				// Called when the user starts speaking
			}

			@Override
			public void onRmsChanged(float rmsdB) {
				// Called when the speech recognizer detects a change in the audio signal
			}

			@Override
			public void onBufferReceived(byte[] buffer) {
				// Called when the speech recognizer receives audio data
			}

			@Override
			public void onEndOfSpeech() {
				// Called when the user stops speaking
				// Restart the speech recognition
				startListening();
			}

			@Override
			public void onError(int error) {
				// Called when an error occurs
				// Restart the speech recognition
				startListening();
			}

			@Override
			public void onResults(Bundle results) {
				// Called when the speech recognizer returns results
				ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				// Process the recognition results
				if (matches != null && matches.size() > 0) {
					String mostLikelyThingHeard = matches.get(0);
					Toast.makeText(Myvoice.this, "You said: " + mostLikelyThingHeard, Toast.LENGTH_SHORT).show();
					// Call the speak() method after the recognition results are received
					ttsActivity.speak("Hello, this is a test!");
				}
				// Restart the speech recognition
				startListening();
			}

			@Override
			public void onPartialResults(Bundle partialResults) {
				// Called when the speech recognizer returns partial results
			}

			@Override
			public void onEvent(int eventType, Bundle params) {
				// Called when an event occurs
			}
		});
		startListening();

		// Create a notification channel
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			CharSequence name = "My Channel";
			String description = "This is my channel";
			int importance = NotificationManager.IMPORTANCE_DEFAULT;
			NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
			channel.setDescription(description);
			NotificationManager notificationManager = getSystemService(NotificationManager.class);
			notificationManager.createNotificationChannel(channel);
		}

		// Create a notification
		Intent notificationIntent = new Intent(this, Myvoice.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		Notification notification = new Notification.Builder(this, CHANNEL_ID).setContentTitle("My Voice Service")
				.setContentText("Running in the background")
				//.setSmallIcon(R.drawable.ic_notification)
				.setContentIntent(pendingIntent).build();

		// Start the service as a foreground service
		startForeground(1, notification);
	}

	private void startListening() {
		Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
		recognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now");
		recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);
		speechRecognizer.startListening(recognizerIntent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		speechRecognizer.stopListening();
		speechRecognizer.destroy();
		ttsActivity.shutdown();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}