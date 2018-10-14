package com.blockchain.exercise.data;

public enum MemberType {

    NORM("3c711855-e060-49b3-8611-40b256b7b143", "12Winter@12"),
    WRONGID("3c711855-e060-49b3-8611", "12Winter@12");

    private String walletID;
    private String password;

    MemberType(String walletID, String password) {
        this.walletID = walletID;
        this.password = password;
    }

    public String getWalletID() {
        return walletID;
    }

    public String getPassword() {
        return password;
    }
}
