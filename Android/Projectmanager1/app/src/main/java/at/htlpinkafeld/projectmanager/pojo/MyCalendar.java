package at.htlpinkafeld.projectmanager.pojo;

/**
 * Created by User on 04.04.2016.
 */
public class MyCalendar {
    private Long id;
    private String displayName;
    private String accountName;
    private String accountType;
    private String ownerName;

    public MyCalendar(Long id, String displayName, String accountName, String accountType, String ownerName) {
        this.id = id;
        this.displayName = displayName;
        this.accountName = accountName;
        this.accountType = accountType;
        this.ownerName = ownerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
