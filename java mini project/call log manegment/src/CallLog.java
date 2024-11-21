import java.util.Date;

public class CallLog {
    private int callId;
    private String callerNumber;
    private int callDuration;
    private String callType;
    private Date callTimestamp;

    // Constructors
    public CallLog() {}

    public CallLog(String callerNumber, int callDuration, String callType, Date callTimestamp) {
        this.callerNumber = callerNumber;
        this.callDuration = callDuration;
        this.callType = callType;
        this.callTimestamp = callTimestamp;
    }

    // Getters and setters
    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public String getCallerNumber() {
        return callerNumber;
    }

    public void setCallerNumber(String callerNumber) {
        this.callerNumber = callerNumber;
    }

    public int getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(int callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Date getCallTimestamp() {
        return callTimestamp;
    }

    public void setCallTimestamp(Date callTimestamp) {
        this.callTimestamp = callTimestamp;
    }
}
