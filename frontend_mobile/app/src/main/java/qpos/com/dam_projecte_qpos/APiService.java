package qpos.com.dam_projecte_qpos;

import android.accounts.AccountAuthenticatorResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APiService {

    // Ejemplo de solicitud POST para el inicio de sesión
    @FormUrlEncoded
    @POST("login/")  // Reemplaza "login/" con la ruta real de inicio de sesión en tu API
    Call<AuthResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

}
