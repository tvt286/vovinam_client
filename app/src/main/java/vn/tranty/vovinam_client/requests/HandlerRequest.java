package vn.tranty.vovinam_client.requests;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.tranty.vovinam_client.mics.Contanst;


public class HandlerRequest {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.SECONDS).writeTimeout(1, TimeUnit.SECONDS);
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Contanst.API.URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient.build()).build();

        return retrofit.create(serviceClass);
    }

}
