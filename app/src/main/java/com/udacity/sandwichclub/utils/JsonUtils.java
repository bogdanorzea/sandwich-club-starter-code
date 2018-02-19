package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject("name");
            String mainName = name.getString("mainName");
            List<String> alsoKnownAs = getStrings(name.getJSONArray("alsoKnownAs"));
            String placeOfOrigin = jsonObject.getString("placeOfOrigin");
            String description = jsonObject.getString("description");
            String image = jsonObject.getString("image");
            List<String> ingredients = getStrings(jsonObject.getJSONArray("ingredients"));

            sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            Log.e("LOG_TAG", "Failed to parse JSON: " + json, e);
        }

        return sandwich;
    }

    private static List<String> getStrings(JSONArray jsonArray) throws JSONException {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            returnList.add(jsonArray.getString(i));
        }

        return returnList;
    }
}
