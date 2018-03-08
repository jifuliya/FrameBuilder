package com.example.liuyulong.applicationframe.mvp.models.autovalue;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Error {
    public static TypeAdapter<Error> typeAdapter(Gson gson) {
        return new AutoValue_Error.GsonTypeAdapter(gson)
                .setDefaultCode(0)
                .setDefaultMessage("http error");
    }

    public static Error createDefaultError() {
        return new AutoValue_Error(0, "http error");
    }

    @SerializedName("code")
    public abstract int code();

    @SerializedName("message")
    public abstract String message();

}
