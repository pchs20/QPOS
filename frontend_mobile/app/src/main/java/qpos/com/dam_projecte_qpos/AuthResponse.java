package qpos.com.dam_projecte_qpos;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("token")
    private String token;
    @SerializedName("id")
    private int id;

    public String getToken() {
        return token;
    }

    public int getId() {
        return id;
    }
}
