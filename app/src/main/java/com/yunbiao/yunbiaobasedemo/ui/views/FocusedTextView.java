package com.yunbiao.yunbiaobasedemo.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class FocusedTextView extends TextView {
	public FocusedTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean isFocused() {
		return true;
	}
}
