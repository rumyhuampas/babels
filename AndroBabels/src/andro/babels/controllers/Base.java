package andro.babels.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Base {
    public void RunActivity(Context context, Class className, Bundle extras) {
        Intent intent = new Intent(context, className);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
}
