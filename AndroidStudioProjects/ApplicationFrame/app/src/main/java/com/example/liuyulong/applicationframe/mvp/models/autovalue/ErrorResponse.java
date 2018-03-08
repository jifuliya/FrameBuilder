package com.example.liuyulong.applicationframe.mvp.models.autovalue;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import static com.example.liuyulong.applicationframe.mvp.models.autovalue.Error.createDefaultError;

@AutoValue
public abstract class ErrorResponse {

    public static TypeAdapter<ErrorResponse> typeAdapter(Gson gson) {
        return new AutoValue_ErrorResponse.GsonTypeAdapter(gson)
                .setDefaultError(createDefaultError());
    }

    @SerializedName("error")
    public abstract Error error();
}
