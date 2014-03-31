package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = 
			"com.bignerdranch.android.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = 
			"com.bignerdranch.android.geoquiz.answer_shown";
	private static final String KEY_ANSWER_SHOWN = "answer_shown";

	private boolean mAnswerIsTrue;
	private boolean mAnswerShown = false;
	private TextView mAnswerTextView;
	private Button mShowAnswer;

	private void setAnswerShownResult(boolean isAnswerShown) {
		Intent data = new Intent();
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		setResult(RESULT_OK, data);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(KEY_ANSWER_SHOWN, mAnswerShown);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		mAnswerIsTrue = getIntent()
				.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		if (savedInstanceState != null) {
			mAnswerShown = savedInstanceState.getBoolean(KEY_ANSWER_SHOWN,
					false);
		}
		setAnswerShownResult(mAnswerShown);
		if (mAnswerShown) {
			if (mAnswerIsTrue) {
				mAnswerTextView.setText(R.string.true_button);
			} else {
				mAnswerTextView.setText(R.string.false_button);
			}
		}
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				mAnswerShown = true;
				if (mAnswerIsTrue) {
					mAnswerTextView.setText(R.string.true_button);
				} else {
					mAnswerTextView.setText(R.string.false_button);
				}
				setAnswerShownResult(mAnswerShown);
			}
		});
	}
}
