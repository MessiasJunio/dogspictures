package br.com.dogs.utils;

import android.text.Editable;
import android.text.TextWatcher;

public class Mascara implements TextWatcher {


    private boolean isDigitando = false;
    private boolean isDeletando = false;
    private final String mask;

    public Mascara(String mask) {
        this.mask = mask;
    }

    public static Mascara buildCpf() {
        return new Mascara("###.###.###-##");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
        isDeletando = count > after;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (isDigitando || isDeletando) {
            return;
        }
        isDigitando = true;

        int editableLength = editable.length();
        if (editableLength < mask.length()) {
            if (mask.charAt(editableLength) != '#') {
                editable.append(mask.charAt(editableLength));
            } else if (mask.charAt(editableLength-1) != '#') {
                editable.insert(editableLength-1, mask, editableLength-1, editableLength);
            }
        }

        isDigitando = false;
    }

}
