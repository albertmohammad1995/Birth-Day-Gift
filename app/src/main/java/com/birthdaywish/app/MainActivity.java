
package com.birthdaywish.app;

import android.os.Bundle;
import android.widget.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.text.InputType;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText emailInput;
    private EditText messageInput;
    private EditText subjectInput;
    private TextView imageStatus;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createHomeScreen();
    }

    private GradientDrawable roundedBackground(
            int color, int radius) {

        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setCornerRadius(radius);
        return drawable;
    }

    private TextView createText(
            String value, int size, int color, boolean bold) {

        TextView text = new TextView(this);
        text.setText(value);
        text.setTextSize(size);
        text.setTextColor(color);

        if (bold) {
            text.setTypeface(null, Typeface.BOLD);
        }

        return text;
    }

    private EditText createInput(
            String hint, int inputType, int minLines) {

        EditText input = new EditText(this);
        input.setHint(hint);
        input.setTextSize(15);
        input.setTextColor(Color.BLACK);
        input.setHintTextColor(Color.GRAY);
        input.setInputType(inputType);
        input.setMinLines(minLines);
        input.setPadding(18, 14, 18, 14);
        input.setBackground(
                roundedBackground(Color.WHITE, 24)
        );

        return input;
    }

    private void createHomeScreen() {

        int purple = Color.rgb(109, 40, 217);
        int dark = Color.rgb(29, 24, 55);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(22, 25, 22, 25);
        root.setBackground(
                new GradientDrawable(
                        GradientDrawable.Orientation.TL_BR,
                        new int[]{
                                Color.rgb(37, 27, 75),
                                Color.rgb(109, 40, 217),
                                Color.rgb(236, 72, 153)
                        }
                )
        );

        ScrollView scroll = new ScrollView(this);

        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(5, 5, 5, 20);

        TextView logo = createText(
                "🎂 Birthday Wish",
                27, Color.WHITE, true
        );

        logo.setGravity(Gravity.CENTER);
        logo.setPadding(0, 12, 0, 8);

        TextView subtitle = createText(
                "Make someone feel special ❤️",
                14, Color.WHITE, false
        );

        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 0, 0, 25);

        content.addView(logo);
        content.addView(subtitle);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(18, 20, 18, 20);
        card.setBackground(
                roundedBackground(Color.rgb(248, 245, 255), 28)
        );

        TextView heading = createText(
                "Create Birthday Surprise",
                21, dark, true
        );

        heading.setPadding(0, 0, 0, 18);
        card.addView(heading);

        addLabel(card, "Recipient Email");

        emailInput = createInput(
                "Enter recipient's email",
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                1
        );

        card.addView(emailInput, matchParams());

        addLabel(card, "Email Subject");

        subjectInput = createInput(
                "Happy Birthday My Life ❤️",
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES,
                1
        );

        card.addView(subjectInput, matchParams());

        addLabel(card, "Your Birthday Message");

        messageInput = createInput(
                "Write your special message here...",
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_FLAG_MULTI_LINE |
                InputType.TYPE_TEXT_FLAG_CAP_SENTENCES,
                5
        );

        card.addView(messageInput, matchParams());

        addLabel(card, "Optional Image");

        Button imageButton = new Button(this);
        imageButton.setText("Choose Image");
        imageButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(purple)
        );
        imageButton.setTextColor(Color.WHITE);

        imageStatus = createText(
                "No image selected",
                13, Color.DKGRAY, false
        );

        imageButton.setOnClickListener(v ->
                Toast.makeText(
                        this,
                        "Image upload will be connected next.",
                        Toast.LENGTH_SHORT
                ).show()
        );

        card.addView(imageButton);
        card.addView(imageStatus);

        sendButton = new Button(this);
        sendButton.setText("Send Birthday Surprise ❤️");
        sendButton.setTextColor(Color.WHITE);
        sendButton.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(purple)
        );

        LinearLayout.LayoutParams buttonParams =
                matchParams();

        buttonParams.topMargin = 22;

        card.addView(sendButton, buttonParams);

        sendButton.setOnClickListener(v -> {

            String email = emailInput.getText().toString().trim();
            String subject = subjectInput.getText().toString().trim();
            String message = messageInput.getText().toString().trim();

            if (email.isEmpty()) {
                emailInput.setError("Enter recipient email");
                return;
            }

            if (subject.isEmpty()) {
                subjectInput.setError("Enter email subject");
                return;
            }

            if (message.isEmpty()) {
                messageInput.setError("Enter birthday message");
                return;
            }

            Toast.makeText(
                    this,
                    "Email service will be connected in the next step.",
                    Toast.LENGTH_LONG
            ).show();
        });

        content.addView(card);

        TextView footer = createText(
                "Made with love ❤️",
                13, Color.WHITE, false
        );

        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, 22, 0, 10);

        content.addView(footer);

        scroll.addView(content);
        root.addView(scroll);

        setContentView(root);
    }

    private void addLabel(
            LinearLayout parent, String label) {

        TextView text = createText(
                label, 14, Color.DKGRAY, true
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        params.topMargin = 15;
        params.bottomMargin = 7;

        parent.addView(text, params);
    }

    private LinearLayout.LayoutParams matchParams() {

        return new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
    }
}
