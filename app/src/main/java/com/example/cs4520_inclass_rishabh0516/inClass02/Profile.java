// Rishabh Sahu
// Assignment #2
package com.example.cs4520_inclass_rishabh0516.inClass02;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Profile implements Parcelable {
    private String name;
    private String email;
    private String device;
    private String emotion;

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public void setAvatarId(int avatarId) {
        this.avatarId = avatarId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    private int avatarId;
    private int moodId;

    public Profile(String name, String email, String device, String emotion, int avatarId, int moodId) {
        this.name = name;
        this.email = email;
        this.device = device;
        this.emotion = emotion;
        this.avatarId = avatarId;
        this.moodId = moodId;
    }

    protected Profile(Parcel in) {
        name = in.readString();
        email = in.readString();
        device = in.readString();
        emotion = in.readString();
        avatarId = in.readInt();
        moodId = in.readInt();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDevice() {
        return device;
    }

    public String getEmotion() {
        return emotion;
    }

    public int getAvatarId() {
        return avatarId;
    }

    public int getMoodId() {
        return moodId;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", device='" + device + '\'' +
                ", emotion='" + emotion + '\'' +
                ", avatarId=" + avatarId +
                ", moodId=" + moodId +
                '}';
    }

    public Profile() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(device);
        parcel.writeString(emotion);
        parcel.writeInt(avatarId);
        parcel.writeInt(moodId);
    }
}
