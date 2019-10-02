package com.tregz.mvc.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tregz.mvc.R;
import com.tregz.mvc.base.BaseFragment;
import com.tregz.mvc.core.date.DateUtil;
import com.tregz.mvc.data.DataApple;
import com.tregz.mvc.list.ListApple;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY;

public class MainFragment extends BaseFragment {

    private TextView log;
    private TextView sum;
    private EditText editor;
    private String input;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        Log.d(TAG, State.VIEW_INFLATING.name());
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListApple.getInstance().clear();

        log = view.findViewById(R.id.log);
        log.setMovementMethod(new ScrollingMovementMethod());
        sum = view.findViewById(R.id.sum);
        DataApple apple1 = new DataApple(new Date());  // today
        onAppleCreated(ListApple.getInstance().add(apple1));
        apple1.setColor(R.color.colorAccent);
        DataApple apple2 = new DataApple(DateUtil.addMonth(new Date(), -1)); // last month
        onAppleCreated(ListApple.getInstance().add(apple2));
        apple2.setColor(android.R.color.white);
        DataApple apple3 = new DataApple(null);
        onAppleCreated(ListApple.getInstance().add(apple3));
        apple3.setColor(R.color.colorPrimary);
        apple();

        editor = view.findViewById(R.id.input_editor);
        editor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String sequence = charSequence.toString();
                if (!sequence.isEmpty()) input = sequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // do nothing
            }
        });

        view.findViewById(R.id.negative_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.setText("");
                sum.setText("");
            }
        });

        view.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (Integer.parseInt(input) == ListApple.getInstance().getListCount())
                        sum.setText(getString(R.string.answer_positive));
                    else sum.setText(getString(R.string.answer_negative));
                } catch (NumberFormatException e) {
                    toast("Error: " + e.getLocalizedMessage());
                }
            }
        });
    }

    private void apple() {
        DataApple apple = new DataApple(new Date());
        apple.setColor(R.color.colorPrimary);
        onAppleCreated(ListApple.getInstance().add(apple));
    }

    private void onAppleCreated(@NonNull DataApple apple) {
        log.append(HtmlCompat.fromHtml("<b>Pomme ajoutée</b>", FROM_HTML_MODE_LEGACY));
        String skeleton = "d MMMM yyyy";
        SimpleDateFormat formatter = new SimpleDateFormat(skeleton, Locale.getDefault());
        String day = apple.getRipe() != null ? formatter.format(apple.getRipe()) : null;
        String unknown = "Non cueillie ou date de cueillette inconnue.";
        String riped = day != null ? "Cueillie le " + day + "." : unknown;
        log.append("\n" + riped + "\n");
        String total = "Taille de la liste: " + ListApple.getInstance().getListCount();
        sum.setText(total);
    }

    private void toast(@NonNull String message) {
        if (getContext() != null) Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    static {
        TAG = MainFragment.class.getSimpleName();
    }
}
