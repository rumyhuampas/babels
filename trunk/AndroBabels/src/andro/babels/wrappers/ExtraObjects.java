package andro.babels.wrappers;

import android.os.Parcel;
import android.os.Parcelable;

public class ExtraObjects implements Parcelable{

    public Object[] Objects;
    
    public int describeContents() {
        //throw new UnsupportedOperationException("Not supported yet.");
        return 0;
    }

    public void writeToParcel(Parcel arg0, int arg1) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
