package poly.edu.contronller.Function.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    public String getString(String name, String defaultValue){
        String value = request.getParameter(name);
        // nếu giá trị là null thì trả về giá trị mặc định nghĩa là set như nào trả về như đó
        return (value != null) ? value : defaultValue;
    }

    public int getInt(String name, int defaultValue){
        try {
            return Integer.parseInt(request.getParameter(name));
        }catch (Exception e){
            return defaultValue;
        }
    }

    public double getDouble(String name, double defaultValue){
        try {
            return Double.parseDouble(request.getParameter(name));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean getBoolean(String name, boolean defaultValue){
        String value = request.getParameter(name);
        if (value == null) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    public Date getDate(String name, String pattern) {
        try {
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) return null;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(value);
        } catch (Exception e) {
            throw new RuntimeException("Sai định dạng ngày!", e);
        }
    }

    public File save(MultipartFile file, String path) {
        if (file.isEmpty()) return null;
        try {
            // Lấy đường dẫn thật trên server
            String realPath = request.getServletContext().getRealPath(path);
            File dir = new File(realPath);
            if (!dir.exists()) dir.mkdirs();

            File savedFile = new File(dir, file.getOriginalFilename());
            file.transferTo(savedFile);
            return savedFile;
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage(), e);
        }
    }
}
