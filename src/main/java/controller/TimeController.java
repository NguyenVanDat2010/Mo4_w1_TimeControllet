package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
    @GetMapping("/worldclock") //ánh xạ request lên phương thức xử lý.
    public String getTimeByTimeZone(ModelMap modelMap, @RequestParam(name = "city",  required = false, defaultValue = "Asia/Ho_Chi_Minh" ) String city){
        /** lấy thời gian hiện tại và múi giờ của địa phương cùng với múi giờ của thành phố được chỉ định.*/
        // Get current time at local
        Date date = new Date();
        // Get timezone by the local
        TimeZone local = TimeZone.getDefault();
        // Get timezone by the specified city
        TimeZone locale = TimeZone.getTimeZone(city);

        /**Xác định thời gian hiện tại của thành phố được chỉ định dựa trên sự chênh lệch về thời gian giữa các múi giờ và múi giờ chuẩn (UTC).
         Hàm getRawOffset()trả về lượng thời gian tính bằng mili giây để thêm vào UTC để có thời gian chuẩn trong múi giờ này.*/
        // Calculate the current time in the specified city
        long locale_time = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        // Reset the date by locale_time
        date.setTime(locale_time);

        // Set the data sent to the view
        modelMap.addAttribute("city", city);
        modelMap.addAttribute("date", date);
        return "index";
    }

}
