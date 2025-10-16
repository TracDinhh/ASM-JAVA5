package poly.edu.contronller.Function.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    HttpSession session;

    // Lấy giá trị attribute từ session
    @SuppressWarnings("unchecked")
    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    // Đặt giá trị attribute
    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    // Xóa attribute
    public void remove(String name) {
        session.removeAttribute(name);
    }
}
