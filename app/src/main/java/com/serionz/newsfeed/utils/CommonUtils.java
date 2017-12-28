package com.serionz.newsfeed.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import com.serionz.newsfeed.R;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by johnpaulseremba on 06/12/2017.
 */

public final class CommonUtils {

	private CommonUtils() { }

	public static ProgressDialog showDialogLoading(Context context) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.show();

		if (progressDialog.getWindow() != null) {
			progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		}

		progressDialog.setContentView(R.layout.progress_dialog);
		progressDialog.setIndeterminate(true);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		return progressDialog;
	}

}
