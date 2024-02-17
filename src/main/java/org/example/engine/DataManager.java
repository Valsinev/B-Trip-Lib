package org.example.engine;

import org.example.constants.TextCords;

import java.util.List;

public class DataManager {
    public final BusinessTripForm form;
    public List<ImgData> data;

    public DataManager(BusinessTripForm form, List<ImgData> data) {
        this.form = form;
        this.data = data;
    }

    public void dataAdder(List<TextCords> cords, String text) {
        if(text != null && !text.trim().isEmpty()) {
            cords.forEach(textCords -> data.add(new ImgData(text, textCords.xCord(), textCords.yCord())));
        }
    }

    public void dataAdder(TextCords cords, String text) {
        data.add(new ImgData(text, cords.xCord(), cords.yCord()));
    }
}
