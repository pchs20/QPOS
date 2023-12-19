package qpos.com.dam_projecte_qpos.ui.events.detallAssistent;

import com.google.gson.annotations.SerializedName;

public class AssistanceRequestBody {
    @SerializedName("client")
    private String client;

    @SerializedName("esdeveniment")
    private int event;

    public AssistanceRequestBody(String client, int event) {
        this.client = client;
        this.event = event;
    }
}

