package benandfriends.stufftracker.utilities.serialization;


import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.UUID;

import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.respositories.BitmapRepository;

public class ContainerSerializer implements JsonDeserializer<Container>, JsonSerializer<Container> {


    private BitmapRepository bitmapRepository;


    public ContainerSerializer(Context context) {
        bitmapRepository = new BitmapRepository(context);
    }


    @Override
    public JsonElement serialize(Container src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject containerJson = new JsonObject();
        containerJson.addProperty("id", src.getId().toString());
        containerJson.addProperty("name", src.getName());
        if (null != src.getImage()) {
            containerJson.addProperty("image", bitmapRepository.saveBitmap(src.getImage(), src.getId().toString()));
        }
        JsonArray itemArray = new JsonArray();
        for (Item i : src.getItems()) {
            itemArray.add(context.serialize(i));
        }
        containerJson.add("items", itemArray);
        return containerJson;
    }


    @Override
    public Container deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String name = json.getAsJsonObject().get("name").getAsString();
        UUID id = UUID.fromString(json.getAsJsonObject().get("id").getAsString());
        Container container = new Container(name, id);
        if (json.getAsJsonObject().has("image")) {
            container.setImage(bitmapRepository.retrieveBitmapForKey(id.toString()));
        }
        for (JsonElement itemJson : json.getAsJsonObject().get("items").getAsJsonArray()) {
            container.addItem((Item)(context.deserialize(itemJson, Item.class)));
        }
        return container;
    }


}
