package qpos.com.dam_projecte_qpos;

import java.util.List;

import qpos.com.dam_projecte_qpos.ui.cupons.CupoClient;
import qpos.com.dam_projecte_qpos.ui.cupons.Cupons;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius.ActiusFragment;
import qpos.com.dam_projecte_qpos.ui.cupons.fragmentsTab.actius.ActualitzarPunts;
import qpos.com.dam_projecte_qpos.ui.events.Event;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.AssistanceRequestBody;
import qpos.com.dam_projecte_qpos.ui.events.detallAssistent.Assistencies;
import qpos.com.dam_projecte_qpos.ui.factures.Compra;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APiService {

    // Ejemplo de solicitud POST para el inicio de sesión
    @FormUrlEncoded
    @POST("login/clients/")
    Call<AuthResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login/admins/")
    Call<AuthResponse> loginAdmin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("clients/{usuari}/")
    Call<Client> obtenerDatos(@Header("Authorization") String token,
                              @Path("usuari") int user
    );

    @POST("signup/clients/")
    Call<Void> registrarClient(@Body Client cliente);

    @GET("esdeveniments/")
    Call<List<Event>> obtenirEsdeveniments(@Header("Authorization") String token);
    @Headers("Cache-Control: no-cache")
    @PUT("clients/{usuari}/")
    Call<Void> modificarClient(@Header("Authorization") String token,
                               @Path("usuari") int user,
                                @Body Client cliente);

    @POST("assistencies/")
    Call<Void> afegirAssitencia(
            @Header("Authorization") String token,
            @Body AssistanceRequestBody assistanceRequestBody
            );

    @DELETE("clients/{usuari}/")
    Call<Void> eliminarClient(@Header("Authorization") String token,
                              @Path("usuari") int user);

    @DELETE("assistencies/")
    Call<Void> eliminarAssistencies(@Header("Authorization") String token,
                                @Query("client") int clientId,
                                @Query("esdeveniment") int eventId
    );

    @GET("cupons/")
    Call<List<Cupons>> cuponsDisponibles();

    @POST("cuponsclientss/")
    Call<Void> afegirCupo(@Header("Authorization") String token,
                          @Body CupoClient nouCupo
                          );

    @GET("cuponsclientss/")
    Call<List<CupoClient>> obtenirCupons(@Header("Authorization") String token,
                                   @Query("client") int clientid
    );
    @PATCH("cuponsclientss/{id}/")
    Call<Void> actualizarCupons(@Header("Authorization") String token,
                                      @Path("id") int id,
                                      @Body CupoClient cupoClient
    );
    @GET("cupons/{id}")
    Call<Cupons> cuponsDisponibles(@Header("Authorization") String token,
                                         @Path("id") int cupoId
    );

    @POST ("clients/punts/")
    Call<Void> actualitzarPunts(@Header("Authorization") String token,
                                   @Body ActualitzarPunts punts
    );

    @GET("compres/")
    Call<List<Compra>> obtenirCompres(@Header("Authorization") String token,
                                      @Query("client__usuari__user__id") int idClient
    );

    @GET("compres/")
    Call<List<Compra>> obtenirCompresFiltrades(@Header("Authorization") String token,
                                                @Query("client__usuari__user__id") int idClient,
                                                @Query("metodePagament__in") String tipusPagament
    );





}


