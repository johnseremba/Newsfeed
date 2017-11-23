package com.serionz.newsfeed.main.global_news;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serionz.newsfeed.R;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by johnpaulseremba on 22/11/2017.
 */

public class Controller {

	private static final String TAG = Controller.class.getSimpleName();
	private Context mContext;
	private NewsroomAPI getNewsAPI;

	public Controller(Context context) {
		mContext = context;
		this.createNewsFeedAPI();
	}

	private HttpLoggingInterceptor getLoggingInterceptor() {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		return loggingInterceptor;
	}

	private OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
			.addInterceptor(getLoggingInterceptor())
			.addInterceptor(new Interceptor() {
				@Override public okhttp3.Response intercept(Chain chain) throws IOException {
					Request originalRequest = chain.request();
					Request.Builder builder = originalRequest.newBuilder().header("Authorization",
							mContext.getString(R.string.newsroom_api_key));
					Request newRequest = builder.build();
					return chain.proceed(newRequest);
				}
			})
			.build();

	private void createNewsFeedAPI() {
		Gson gson = new GsonBuilder().create();
		Retrofit.Builder builder =
				new Retrofit.Builder()
						.baseUrl(NewsroomAPI.BASE_URL)
						.addConverterFactory(GsonConverterFactory.create(gson));

		Retrofit retrofit = builder.client(okHttpClient).build();
		getNewsAPI = retrofit.create(NewsroomAPI.class);
	}

	public Call<NewsList> fetchGlobalNews() {
		NewsList result;
		String source = "bbc-news";

		Call<NewsList> call = getNewsAPI.loadNews(source);
		return call;
	}

}
