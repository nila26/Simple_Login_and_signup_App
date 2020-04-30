package com.example.kolkatadev;

import com.google.android.gms.tasks.Task;

public class firebase_data {
    String name;
    String phn;
    String password;
    String email;
    String confirmPassword;

    public firebase_data(String name, String phn, String password, String email, String confirmPassword) {
        this.name = name;
        this.phn = phn;
        this.password = password;
        this.email = email;
        this.confirmPassword = confirmPassword;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
