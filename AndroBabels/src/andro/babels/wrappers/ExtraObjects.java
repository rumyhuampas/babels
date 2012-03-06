package andro.babels.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

public class ExtraObjects implements Parcelable{

    public Object[] Objects;
    public Object Obj;
    
    public ExtraObjects(Object[] objects){
        Objects = objects;
    }

    public int describeContents() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void writeToParcel(Parcel arg0, int arg1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
