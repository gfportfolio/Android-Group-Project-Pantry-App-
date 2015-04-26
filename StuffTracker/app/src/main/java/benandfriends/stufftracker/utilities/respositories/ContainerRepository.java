package benandfriends.stufftracker.utilities.respositories;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.serialization.ContainerSerializer;
import benandfriends.stufftracker.utilities.serialization.ItemSerializer;


public final class ContainerRepository {


    private static final String CONTAINER_JSON_FILE_NAME = "containerData.txt";

    private Context context;


    public ContainerRepository(Context c) {
        this.context = c;
    }


    public synchronized List<Container> loadContainerDataFromMemory() {
        List<Container> containers = new ArrayList<>();
        try {
            checkIfDataFileExists();
            FileInputStream fileInputStream = context.openFileInput(CONTAINER_JSON_FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            String json = stringBuilder.toString();
            JsonParser jsonParser = new JsonParser();
            if (!json.isEmpty()) {
                Gson gson = this.getGson();
                for (JsonElement j : jsonParser.parse(json).getAsJsonArray()) {
                    Container container = gson.fromJson(j, Container.class);
                    containers.add(container);
                }
            }
        } catch (Exception e) {
            Log.d(CONTAINER_JSON_FILE_NAME, e.getMessage());
        }
        return containers;
    }


    public synchronized void persistContainerData(List<Container> containers) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemSerializer(context))
                .registerTypeAdapter(Container.class, new ContainerSerializer(context));
        Gson gson = gsonBuilder.create();
        JsonArray containerJsonArray = new JsonArray();
        for (Container c : containers) {
            containerJsonArray.add(gson.toJsonTree(c));
        }

        FileOutputStream outputStream;
        BufferedWriter bufferedWriter = null;
        try {
            outputStream = context.openFileOutput(CONTAINER_JSON_FILE_NAME, Context.MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(containerJsonArray.toString());
            bufferedWriter.flush();
        } catch (Exception e) {
            Log.d("StuffTracker", e.getMessage());
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    Log.d("StuffTracker", "now you can't close the stream, you're so screwed.");
                }
            }
        }
    }


    private void checkIfDataFileExists() {
        File file = new File(context.getFilesDir() + File.separator + CONTAINER_JSON_FILE_NAME);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    Log.d(CONTAINER_JSON_FILE_NAME, "Error: Couldn't create data file.");
                }
            } catch (IOException e) {
                Log.d(CONTAINER_JSON_FILE_NAME, e.getMessage());
            }
        }
    }


    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Item.class, new ItemSerializer(context))
                .registerTypeAdapter(Container.class, new ContainerSerializer(context));
        return gsonBuilder.create();
    }


}
