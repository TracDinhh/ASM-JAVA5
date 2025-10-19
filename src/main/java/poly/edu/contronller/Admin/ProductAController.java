package poly.edu.contronller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.contronller.Function.Entity.Category;
import poly.edu.contronller.Function.Entity.Product;
import poly.edu.contronller.Function.Entity.User;
import poly.edu.contronller.Function.Jpa.CategoryDao;
import poly.edu.contronller.Function.Jpa.ProductDao;
import poly.edu.contronller.Function.Service.SessionService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/product")
public class ProductAController {

    @Autowired
    SessionService sessionService;

    @Autowired
    ProductDao productDao;

    @Autowired
    CategoryDao categoryDao;

    @GetMapping("/index")
    public String index(Model model) {
        User admin = sessionService.get("admin");
        if (admin == null){
            return "redirect:/admin/account/login";
        }
        model.addAttribute("products", productDao.findAll());
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryDao.findAll());
        return "Admin/Product";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("product") Product p,
                         @RequestParam("imageFile") MultipartFile file) throws IOException {

        Category cat = categoryDao.findById(p.getCategory().getCategoryID()).orElse(null);
        p.setCategory(cat);

        if (!file.isEmpty()) {
            String folder = cat.getCategoryName();

            // ✅ Dùng thư mục tĩnh thật mà Tomcat đang dùng khi chạy
            String uploadDir = new File("/uploads" + folder).getAbsolutePath();

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = file.getOriginalFilename();
            file.transferTo(new File(dir, fileName));

            // Lưu đường dẫn tương đối (để hiển thị ảnh)
            p.setImage(folder + "/" + fileName);
        }

        productDao.save(p);
        return "redirect:/admin/product/index";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product p = productDao.findById(id).orElse(new Product());
        model.addAttribute("product", p);
        model.addAttribute("products", productDao.findAll());
        model.addAttribute("categories", categoryDao.findAll());
        return "Admin/Product";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("product") Product p,
                         @RequestParam("imageFile") MultipartFile file) throws IOException {

        // Lấy category từ DB
        Category cat = categoryDao.findById(p.getCategory().getCategoryID()).orElse(null);
        p.setCategory(cat);

        // Lấy sản phẩm cũ trong DB để giữ lại ảnh nếu người dùng không upload mới
        Product oldProduct = productDao.findById(p.getProductID()).orElse(null);
        if (oldProduct != null) {
            p.setCreateAt(oldProduct.getCreateAt()); // ✅ Giữ ngày tạo cũ
        }

        if (!file.isEmpty()) {
            // Nếu có upload ảnh mới → lưu ảnh mới
            String folder = cat.getCategoryName();
            String uploadDir = new File("/uploads" + folder).getAbsolutePath();
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String fileName = file.getOriginalFilename();
            file.transferTo(new File(dir, fileName));

            p.setImage(folder + "/" + fileName);
        } else if (oldProduct != null) {
            // Nếu KHÔNG upload ảnh mới → giữ ảnh cũ
            p.setImage(oldProduct.getImage());
        }

        productDao.save(p);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        productDao.deleteById(id);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/reset")
    public String reset() {
        return "redirect:/admin/product/index";
    }
}
