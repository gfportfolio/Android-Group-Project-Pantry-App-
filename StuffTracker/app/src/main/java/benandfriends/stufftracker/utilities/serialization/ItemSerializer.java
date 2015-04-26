package benandfriends.stufftracker.utilities.serialization;

import android.content.Context;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;

import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.respositories.BitmapRepository;


public class ItemSerializer implements JsonDeserializer<Item>, JsonSerializer<Item> {


    private BitmapRepository bitmapRepository;


    public ItemSerializer(Context context) {
        bitmapRepository = new BitmapRepository(context);
    }


    @Override
    public JsonElement serialize(Item src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject itemJson = new JsonObject();
        itemJson.addProperty("id", src.getId().toString());
        itemJson.addProperty("name", src.getName());
        itemJson.addProperty("isOpened", src.getIsOpened());
        itemJson.addProperty("notifyWhenExpiring", src.getNotifyWhenExpiring());
        if (null != src.getDatePurchased()) {
            itemJson.addProperty("datePurchased", src.getDatePurchased().toString());
        }
        if (null != src.getDateExpires()) {
            itemJson.addProperty("dateExpires", src.getDateExpires().toString());
        }
        if (null != src.getImage()) {
            itemJson.addProperty("image", bitmapRepository.saveBitmap(src.getImage(), src.getId().toString()));
        }

        return itemJson;
    }


    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String name = json.getAsJsonObject().get("name").getAsString();
        UUID id = UUID.fromString(json.getAsJsonObject().get("id").getAsString());
        Item item = new Item(name, id);
        item.setIsOpened(json.getAsJsonObject().get("isOpened").getAsBoolean());
        item.setNotifyWhenExpiring(json.getAsJsonObject().get("notifyWhenExpiring").getAsBoolean());
        if (json.getAsJsonObject().has("datePurchased")) {
            item.setDatePurchased(new Date(json.getAsJsonObject().get("datePurchased").getAsString()));
        }
        if (json.getAsJsonObject().has("dateExpires")) {
            item.setDateExpires(new Date(json.getAsJsonObject().get("dateExpires").getAsString()));
        }
        if (json.getAsJsonObject().has("image")) {
            item.setImage(bitmapRepository.retrieveBitmapForKey(json.getAsJsonObject().get("image").getAsString()));
        }

        return item;
    }


}
