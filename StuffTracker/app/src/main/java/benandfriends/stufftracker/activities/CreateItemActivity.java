package benandfriends.stufftracker.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;

import benandfriends.stufftracker.R;
import benandfriends.stufftracker.utilities.ItemsAdapter;


public class CreateItemActivity extends Activity {

    private final int SELECT_PHOTO = 1;

    private int parentContainerId = -1;
    private int currentItemId = -1;

    private EditText titleBox;

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
            if (-1 == parentContainerId || -1 == currentItemId) {
                return;
            }
            this.setUpViews();
        }
    }


    private void setUpViews() {
        instantiateViews();
        setUpImagePickerButton();
        setUpPickPurchasedOnDateButton();
        setUpPickExpirationDateButton();
        setUpDoneButton();
    }


    private void instantiateViews() {
        titleBox = (EditText) findViewById(R.id.title_box);
        doneButton = (Button)findViewById(R.id.done_button);

        notifyOfExpirationCheckBox = (CheckBox)findViewById(R.id.notify_of_expiration_checkbox);
        itemHasBeenOpenedCheckBox = (CheckBox)findViewById(R.id.item_has_been_opened_checkbox);

        imageView = (ImageView) findViewById(R.id.imageView);
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
