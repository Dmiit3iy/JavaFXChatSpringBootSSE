package org.dmiit3iy.retrofit;

import org.dmiit3iy.dto.ResponseResult;
import org.dmiit3iy.model.Message;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface MessageService {
    @POST("message/{idUser}")
    Call<ResponseResult<Message>> post(@Path("idUser") long idUser, @Body Message message);

    @GET("{idUser}")
    void get(@Path("idUser") long idUser);

    @GET(".")
    Call<ResponseResult<List<Message>>> getAll();

}
