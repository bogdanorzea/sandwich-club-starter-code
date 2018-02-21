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
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        try {
            JSONObject jsonObject = new JSONObject(json);

            if (jsonObject.has(NAME)) {
                JSONObject name = jsonObject.getJSONObject(NAME);

                String mainName = "";
                if (name.has(MAIN_NAME)) {
                    mainName = name.optString(MAIN_NAME);
                }

                List<String> alsoKnownAs = null;
                if (name.has(ALSO_KNOWN_AS)) {
                    alsoKnownAs = getStrings(name.getJSONArray(ALSO_KNOWN_AS));
                }

                String placeOfOrigin = "";
                if (jsonObject.has(PLACE_OF_ORIGIN)) {
                    placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN);
                }

                String description = "";
                if (jsonObject.has(DESCRIPTION)) {
                    description = jsonObject.optString(DESCRIPTION);
                }

                String image = "";
                if (jsonObject.has(IMAGE)) {
                    image = jsonObject.optString(IMAGE);
                }

                List<String> ingredients = null;
                if (jsonObject.has(INGREDIENTS)) {
                    ingredients = getStrings(jsonObject.getJSONArray(INGREDIENTS));
                }

                sandwich = new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Failed to parse JSON: " + json, e);
        }

        return sandwich;
    }

    private static List<String> getStrings(JSONArray jsonArray) throws JSONException {
        List<String> returnList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            returnList.add(jsonArray.getString(i));
        }

        return returnList;
    }
}
