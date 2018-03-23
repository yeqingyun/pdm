package zrsy.ww;


public class UploadAction extends BasicAction {

    String windowNo;

    String callParam;

    String usrId;

    String config;

    String recentFileNo;

    public String getRecentFileNo() {
        return recentFileNo;
    }

    public void setRecentFileNo(String recentFileNo) {
        this.recentFileNo = recentFileNo;
    }

    public String getWindowNo() {
        return windowNo;
    }

    public void setWindowNo(String windowNo) {
        this.windowNo = windowNo;
    }

    public String getCallParam() {
        return callParam;
    }

    public void setCallParam(String callParam) {
        this.callParam = callParam;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String index() {
        return "index";
    }
}
