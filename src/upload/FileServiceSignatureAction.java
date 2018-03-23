package upload;

import com.alibaba.fastjson.JSON;
import com.gionee.gnif.file.util.CalcUtil;
import com.gionee.gnif.file.util.DateUtil;
import com.gionee.oss.api.util.EncryptUtil;
import com.opensymphony.webwork.ServletActionContext;
import gnwf.ww.BasicAction;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static upload.OssConstant.key;

public class FileServiceSignatureAction extends BasicAction {
    String info;

    public void getSignatureAndFileInfo() {
        try {
            if (info == null || info.length() == 0) {
                return;
            }
            if(key.getValue() == null){
                OssConstant.init();
            }
            Map<String, String> map = new HashMap<>();
            String prefix = DateUtil.GMTCurrentTimeStamp() + "\n";
            map.put("signature", EncryptUtil.signature(key.getValue(), prefix + info));
            map.put("fileInfo", URLEncoder.encode(CalcUtil.getBase64(prefix + info), "UTF-8"));

            writeJson(ServletActionContext.getResponse(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void getSignatureAndPolicy() {
        try {
            if (info == null || info.length() == 0) {
                return;
            }
            if(key.getValue() == null){
                OssConstant.init();
            }
            int idxBr = info.indexOf("\\n");
            StringBuilder result = new StringBuilder();
            if (info.split("\n").length <= 1 && idxBr >= 0) {
                result.append(info.substring(0, idxBr)).append("\n");
                String tmp = info.substring(idxBr + 2);
                if ((idxBr = tmp.indexOf("\\n")) > 0) {
                    result.append(tmp.substring(0, idxBr)).append("\n").append(tmp.substring(idxBr + 2));
                } else {
                    result.append(tmp.substring(0, idxBr));
                }
                info = result.toString();
            }

            Map<String, String> map = new HashMap<>();
            map.put("signature", EncryptUtil.signature(key.getValue(), info));
            map.put("policy", URLEncoder.encode(CalcUtil.getBase64(info), "UTF-8"));

            writeJson(ServletActionContext.getResponse(), map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void writeJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(JSON.toJSONString(obj));
        out.flush();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
