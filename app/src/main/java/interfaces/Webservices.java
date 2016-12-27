package interfaces;

import java.util.Map;

import model.M_locresponce;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Webservices {

    @FormUrlEncoded
    @POST("user/signup/post")
    Call<M_locresponce> signInMapForm(@FieldMap Map<String, String> map);
}
