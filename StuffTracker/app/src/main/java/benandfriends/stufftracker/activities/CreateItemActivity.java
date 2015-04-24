package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import benandfriends.stufftracker.Application;
import benandfriends.stufftracker.R;
import benandfriends.stufftracker.models.Container;
import benandfriends.stufftracker.models.Item;
import benandfriends.stufftracker.utilities.ItemsAdapter;


public class CreateItemActivity extends Activity {

    private final int SELECT_PHOTO = 1;

    private int parentContainerId = -1;
    private int currentItemId = -1;

    private EditText titleBox;
    private Spinner containerSpinner;
    private Button doneButton;
    private CheckBox notifyOfExpirationCheckBox;
    private CheckBox itemHasBeenOpenedCheckBox;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);

        Intent i = getIntent();
        if (null != i) {
            parentContainerId = i.getIntExtra(ItemsAdapter.CONTAINER_PARENT_ID_KEY, -1);
            currentItemId = i.getIntExtra(FabListActivity.POSITION_KEY, -1);
            if (-1 == parentContainerId) {
                return;
            }
            this.setUpViews();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if (null == imageView) {
            return;
        }
        if (requestCode == SELECT_PHOTO && resultCode == RESULT_OK) {
            try {
                final Uri imageUri = imageReturnedIntent.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = resizeBitmap(BitmapFactory.decodeStream(imageStream), 800);
                imageView.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void setUpViews() {
        instantiateViews();
        setUpContainerSpinner();
        setUpImagePickerButton();
        setUpPickPurchasedOnDateButton();
        setUpPickExpirationDateButton();
        setUpDoneButton();
        if (this.currentItemId > -1) {
            populateFieldsWithItemData();
        }
    }


    private void instantiateViews() {
        titleBox = (EditText) findViewById(R.id.title_box);
        containerSpinner = (Spinner) findViewById(R.id.container_selection_spinner);
        doneButton = (Button)findViewById(R.id.done_button);
        notifyOfExpirationCheckBox = (CheckBox)findViewById(R.id.notify_of_expiration_checkbox);
        itemHasBeenOpenedCheckBox = (CheckBox)findViewById(R.id.item_has_been_opened_checkbox);
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    private void setUpContainerSpinner() {
        containerSpinner = (Spinner) findViewById(R.id.container_selection_spinner);
        List<Container> containers = Application.getApplicationInstance().getContainers();
        List<String> containerNames = new ArrayList<>();
        for (Container c : containers) {
            containerNames.add(c.getName());
        }
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, containerNames);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        containerSpinner.setAdapter(spinAdapter);
        containerSpinner.setSelection(parentContainerId);
    }


    private void setUpImagePickerButton() {
        Button pickImageButton = (Button)findViewById(R.id.pick_image_button);
        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }


    private void setUpPickPurchasedOnDateButton() {
        Button purchasedOnButton = (Button)findViewById(R.id.purchased_on_date_button);
        purchasedOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open date picker
            }
        });
    }


    private void setUpPickExpirationDateButton() {
        Button expiresOnButton = (Button)findViewById(R.id.expired_on_date_button);
        expiresOnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open date picker
            }
        });
    }


    private void setUpDoneButton() {
        doneButton = (Button) findViewById(R.id.done_button);
        doneButton.setEnabled(false);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneButton.setEnabled(false);
                // TODO: Create and add new Item to its parent collection. Finish activity.
            }
        });
    }


    private void populateFieldsWithItemData() {
        Container parentContainer = Application.getApplicationInstance().getContainers().get(parentContainerId);
        Item currentItem = parentContainer.getItems().get(currentItemId);
        if (null != currentItem) {
            titleBox.setText(currentItem.getName());
            notifyOfExpirationCheckBox.setChecked(currentItem.getNotifyWhenExpiring());
            itemHasBeenOpenedCheckBox.setChecked(currentItem.getIsOpened());
            imageView.setImageBitmap(currentItem.getImage());
        }
    }


    private Bitmap resizeBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 0) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        private Date toSet;

        public DatePickerFragment(Date toSet) {
            this.toSet = toSet;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

        }
    }
}
